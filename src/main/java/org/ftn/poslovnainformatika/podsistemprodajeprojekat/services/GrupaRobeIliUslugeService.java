package org.ftn.poslovnainformatika.podsistemprodajeprojekat.services;

import org.ftn.poslovnainformatika.podsistemprodajeprojekat.model.GrupaRobeIliUsluge;
import org.ftn.poslovnainformatika.podsistemprodajeprojekat.repository.GrupaRobeIliUslugeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class GrupaRobeIliUslugeService {

    @Autowired
    private GrupaRobeIliUslugeRepository grupaRobeIliUslugeRepository;

    public List<GrupaRobeIliUsluge> getGrupeRobeIliUsluge() {
        return grupaRobeIliUslugeRepository.findAll();
    }

    public GrupaRobeIliUsluge createGrupaRobeIliUsluge(GrupaRobeIliUsluge grupaRobeIliUsluge) {
        grupaRobeIliUsluge.setId(UUID.randomUUID().toString());
        return grupaRobeIliUslugeRepository.save(grupaRobeIliUsluge);
    }

    public GrupaRobeIliUsluge updateGrupaRobeIliUsluge(String id, GrupaRobeIliUsluge grupaRobeIliUsluge) {
        var updatedGrupa = grupaRobeIliUslugeRepository.getOne(id);
        if (updatedGrupa != null) {
            updatedGrupa.setNaziv(grupaRobeIliUsluge.getNaziv());
            updatedGrupa.setPdvKategorija(grupaRobeIliUsluge.getPdvKategorija());
            return grupaRobeIliUslugeRepository.save(updatedGrupa);
        }
        return null;
    }

    public boolean removeGrupaRobeIliUsluge(String id) {
        var grupa = grupaRobeIliUslugeRepository.getOne(id);
        if (grupa != null) {
            grupaRobeIliUslugeRepository.delete(grupa);
            return true;
        }
        return false;
    }
}
