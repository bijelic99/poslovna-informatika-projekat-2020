package org.ftn.poslovnainformatika.podsistemprodajeprojekat.services;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.ftn.poslovnainformatika.podsistemprodajeprojekat.model.Faktura;
import org.ftn.poslovnainformatika.podsistemprodajeprojekat.model.PDVStopa;
import org.ftn.poslovnainformatika.podsistemprodajeprojekat.model.StavkaCenovnika;
import org.ftn.poslovnainformatika.podsistemprodajeprojekat.model.StavkaFakture;
import org.ftn.poslovnainformatika.podsistemprodajeprojekat.repository.FakturaRepository;
import org.ftn.poslovnainformatika.podsistemprodajeprojekat.repository.StavkaFaktureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;
import org.xhtmlrenderer.pdf.ITextRenderer;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.Comparator;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class FakturaService {
    @Autowired
    FakturaRepository fakturaRepository;

    @Autowired
    CenovnikService cenovnikService;

    @Autowired
    StavkaFaktureRepository stavkaFaktureRepository;

    public Faktura saveFaktura(Faktura faktura) {
        var azuriranaFaktura = faktura;
        var vazeciCenovnik = cenovnikService.getCenovnikPreduzecaForDate(faktura.getPreduzece().getId(), faktura.getDatumFakture()).orElseThrow();
        var stavke = azuriranaFaktura.getStavke().stream().map(stavka -> {
            var procenatPdv = stavka
                    .getRobaIliUsluga()
                    .getGrupaRobeIliUsluge()
                    .getPdvKategorija()
                    .getStopePDV()
                    .stream()
                    .filter(pdvStopa -> pdvStopa.getDatumVazenja().isBefore(faktura.getDatumFakture()) || pdvStopa.getDatumVazenja().isEqual(faktura.getDatumFakture()))
                    .max(Comparator.comparing(PDVStopa::getDatumVazenja))
                    .map(PDVStopa::getProcenat)
                    .orElse(0d);
            stavka.setPDVStopa(procenatPdv);
            var robaIliUsluga = stavka.getRobaIliUsluga();
            var jedinicnaCena = vazeciCenovnik.getStavkeCenovnika().stream()
                    .filter(stavkaCenovnika -> stavkaCenovnika.getRobaIliUsluga().getId().equals(robaIliUsluga.getId()))
                    .map(StavkaCenovnika::getCena)
                    .findFirst()
                    .orElseThrow();
            stavka.setJedinicnaCena(jedinicnaCena);
            stavka.setOsnovicaZaPDV((stavka.getKolicina() * jedinicnaCena) - stavka.getRabat());
            stavka.setIznosPDV((stavka.getOsnovicaZaPDV() * stavka.getPDVStopa()) / 100);
            stavka.setUkupanIznos(stavka.getOsnovicaZaPDV() - stavka.getRabat() + stavka.getIznosPDV());
            return stavka;
        }).collect(Collectors.toSet());
        stavkaFaktureRepository.saveAll(stavke);
        azuriranaFaktura.setStavke(stavke);
        azuriranaFaktura.setUkupnaOsnovica(stavke.stream().map(StavkaFakture::getOsnovicaZaPDV).reduce(0d, Double::sum));
        azuriranaFaktura.setUkupanPDV(stavke.stream().map(StavkaFakture::getIznosPDV).reduce(0d, Double::sum));
        azuriranaFaktura.setUkupanIznos(stavke.stream().map(StavkaFakture::getUkupanIznos).reduce(0d, Double::sum));
        return fakturaRepository.save(azuriranaFaktura);
    }

    public Faktura createFaktura(Faktura faktura) {
        var novaFaktura = faktura;
        novaFaktura.setBrojFakture(fakturaRepository.getPoslednjiBrojFaktureZaGodinu(faktura.getPoslovnaGodina().getGodina()).orElse(0) + 1);
        faktura.setFakturaGotova(false);
        return saveFaktura(faktura);
    }

    public Optional<InputStreamResource> getFakturaPdf(String id) {
        return fakturaRepository.findById(id).map(faktura -> {
            ClassLoaderTemplateResolver templateResolver = new ClassLoaderTemplateResolver();
            templateResolver.setSuffix(".html");
            templateResolver.setTemplateMode(TemplateMode.HTML);

            TemplateEngine templateEngine = new TemplateEngine();
            templateEngine.setTemplateResolver(templateResolver);

            Context context = new Context();

            var pdvSuma = faktura.getStavke().stream().map(StavkaFakture::getPDVStopa).distinct().map(stopa -> {
                var stavkeSaStopom = faktura.getStavke().stream().filter(stavkaFakture -> stavkaFakture.getPDVStopa().equals(stopa)).collect(Collectors.toList());
                var osnovica = stavkeSaStopom.stream().map(StavkaFakture::getOsnovicaZaPDV).reduce(0d, Double::sum);
                var pdvIznos = stavkeSaStopom.stream().map(StavkaFakture::getIznosPDV).reduce(0d, Double::sum);
                var vrednostSaPdv = stavkeSaStopom.stream().map(StavkaFakture::getUkupanIznos).reduce(0d, Double::sum);
                return new PdvSuma(stopa, osnovica, pdvIznos, vrednostSaPdv);
            }).collect(Collectors.toList());

            var ukupanRabat = faktura.getStavke().stream().map(StavkaFakture::getRabat).reduce(0d, Double::sum);

            context.setVariable("faktura", faktura);
            context.setVariable("pdvSuma", pdvSuma);
            context.setVariable("ukupanRabat", ukupanRabat);

            var fakturaHtml = templateEngine.process("faktura-template", context);

            var renderer = new ITextRenderer();
            renderer.setDocumentFromString(fakturaHtml);
            renderer.layout();
            try (var baos = new ByteArrayOutputStream()) {
                renderer.createPDF(baos);
                return new InputStreamResource(new ByteArrayInputStream(baos.toByteArray()));
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
    }

    @AllArgsConstructor
    @Getter
    public static class PdvSuma {
        private Double tarifa;
        private Double osnovica;
        private Double pdvIznos;
        private Double vrednostSaPdv;
    }
}
