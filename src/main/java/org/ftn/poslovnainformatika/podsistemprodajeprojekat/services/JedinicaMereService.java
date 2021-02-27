package org.ftn.poslovnainformatika.podsistemprodajeprojekat.services;

import org.ftn.poslovnainformatika.podsistemprodajeprojekat.model.JedinicaMere;
import org.ftn.poslovnainformatika.podsistemprodajeprojekat.repository.JedinicaMereRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class JedinicaMereService {

    @Autowired
    private JedinicaMereRepository jedinicaMereRepository;

    public List<JedinicaMere> getJediniceMere() {
        return jedinicaMereRepository.findAll();
    }

    public JedinicaMere createJedinicaMere(JedinicaMere jedinicaMere) {
        jedinicaMere.setId(UUID.randomUUID().toString());
        return jedinicaMereRepository.save(jedinicaMere);
    }

    public boolean removeJedinicaMere(String id) {
        var jedinicaMere = jedinicaMereRepository.getOne(id);
        if (jedinicaMere != null) {
            jedinicaMereRepository.delete(jedinicaMere);
            return true;
        }
        return false;
    }

    public JedinicaMere updateJedinicaMere(String id, JedinicaMere jedinicaMere) {
        var updatedJedinicaMere = jedinicaMereRepository.getOne(id);
        if (updatedJedinicaMere != null) {
            updatedJedinicaMere.setNaziv(jedinicaMere.getNaziv());
            updatedJedinicaMere.setSkraceniNaziv(jedinicaMere.getSkraceniNaziv());
            return jedinicaMereRepository.save(updatedJedinicaMere);
        }
        return null;
    }
}
