package org.ftn.poslovnainformatika.podsistemprodajeprojekat.services;

import org.ftn.poslovnainformatika.podsistemprodajeprojekat.model.RobaIliUsluga;
import org.ftn.poslovnainformatika.podsistemprodajeprojekat.repository.RobaIliUslugaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.UUID;

@Service
public class RobaIliUslugaService {

    @Autowired
    private RobaIliUslugaRepository robaIliUslugaRepository;

    public List<RobaIliUsluga> getRobeIliUsluge() {
        return robaIliUslugaRepository.findAll();
    }

    public RobaIliUsluga createRobaIliUsluga(RobaIliUsluga robaIliUsluga) {
        robaIliUsluga.setId(UUID.randomUUID().toString());
        return robaIliUslugaRepository.save(robaIliUsluga);
    }

    public RobaIliUsluga updateRobaIliUsluga(String id, RobaIliUsluga robaIliUsluga) {
        var updatedRobaIliUsluga = robaIliUslugaRepository.getOne(id);
        if (updatedRobaIliUsluga != null) {
            updatedRobaIliUsluga.setNaziv(robaIliUsluga.getNaziv());
            updatedRobaIliUsluga.setOpis(robaIliUsluga.getOpis());
            updatedRobaIliUsluga.setRoba(robaIliUsluga.getRoba());
            updatedRobaIliUsluga.setJedinicaMere(robaIliUsluga.getJedinicaMere());
            updatedRobaIliUsluga.setGrupaRobeIliUsluge(robaIliUsluga.getGrupaRobeIliUsluge());
            return robaIliUslugaRepository.save(updatedRobaIliUsluga);
        }
        return null;
    }

    public boolean removeRobaIliUsluga(String id) {
        var robaIliUsulga = robaIliUslugaRepository.getOne(id);
        if (robaIliUsulga != null) {
            robaIliUsulga.setObrisan(true);
            robaIliUslugaRepository.save(robaIliUsulga);
            return true;
        }
        return false;
    }
}
