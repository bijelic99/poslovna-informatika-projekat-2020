package org.ftn.poslovnainformatika.podsistemprodajeprojekat.services;

import org.ftn.poslovnainformatika.podsistemprodajeprojekat.model.Faktura;
import org.ftn.poslovnainformatika.podsistemprodajeprojekat.model.PDVStopa;
import org.ftn.poslovnainformatika.podsistemprodajeprojekat.model.StavkaCenovnika;
import org.ftn.poslovnainformatika.podsistemprodajeprojekat.model.StavkaFakture;
import org.ftn.poslovnainformatika.podsistemprodajeprojekat.repository.FakturaRepository;
import org.ftn.poslovnainformatika.podsistemprodajeprojekat.repository.StavkaFaktureRepository;
import org.joda.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
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

    public List<Faktura> searchFakture(LocalDate datumOd, LocalDate datumDo) {
        return fakturaRepository.findAll().
                stream().filter(x -> x.getDatumFakture().isAfter(datumOd) && x.getDatumFakture().isBefore(datumDo))
                .collect(Collectors.toList());
    }
}
