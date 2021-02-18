package org.ftn.poslovnainformatika.podsistemprodajeprojekat.services;

import org.ftn.poslovnainformatika.podsistemprodajeprojekat.model.RobaIliUsluga;
import org.ftn.poslovnainformatika.podsistemprodajeprojekat.repository.RobaIliUslugaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class RobaIliUslugaService {

    @Autowired
    private RobaIliUslugaRepository robaIliUslugaRepository;

    public List<RobaIliUsluga> getRobeIliUsluge() {
        return robaIliUslugaRepository.findAll();
    }
}
