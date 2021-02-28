package org.ftn.poslovnainformatika.podsistemprodajeprojekat.services;

import org.ftn.poslovnainformatika.podsistemprodajeprojekat.model.PDVKategorija;
import org.ftn.poslovnainformatika.podsistemprodajeprojekat.repository.PDVKategorijaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class PDVKategorijaService {

    @Autowired
    private PDVKategorijaRepository pdvKategorijaRepository;

    public List<PDVKategorija> getPdvKategorije() {
        return pdvKategorijaRepository.findAll();
    }

    public PDVKategorija createPdvKategorija(PDVKategorija pdvKategorija) {
        pdvKategorija.setId(UUID.randomUUID().toString());
        return pdvKategorijaRepository.save(pdvKategorija);
    }

    public PDVKategorija updatePdvKategorija(String id, PDVKategorija pdvKategorija) {
        var updatedPdvKategorija = pdvKategorijaRepository.findById(id).orElse(null);
        if (updatedPdvKategorija != null) {
            updatedPdvKategorija.setNazivKategorije(pdvKategorija.getNazivKategorije());
            updatedPdvKategorija.setStopePDV(pdvKategorija.getStopePDV());
            return pdvKategorijaRepository.save(updatedPdvKategorija);
        }
        return null;
    }

    public boolean removePdvKategorija(String id) {
        var pdvKategorija = pdvKategorijaRepository.getOne(id);
        if (pdvKategorija != null) {
            pdvKategorijaRepository.delete(pdvKategorija);
            return true;
        }
        return false;
    }
}
