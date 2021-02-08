package org.ftn.poslovnainformatika.podsistemprodajeprojekat.controllers;

import org.ftn.poslovnainformatika.podsistemprodajeprojekat.model.*;
import org.ftn.poslovnainformatika.podsistemprodajeprojekat.repository.PreduzeceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Comparator;
import java.util.Set;

@RestController
@RequestMapping("api/preduzeca")
public class PreduzeceController {

    @Autowired
    PreduzeceRepository preduzeceRepository;

    @GetMapping(value = "/{id}/partneri")
    public ResponseEntity<Set<PoslovniPartner>> getPoslovniPartneri(@PathVariable("id") String id) {
        return preduzeceRepository.findById(id).map(Preduzece::getPoslovniPartneri).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @GetMapping(value = "/{id}/cenovnici/trenutni")
    public ResponseEntity<Cenovnik> getTrenutniCenovnik(@PathVariable("id") String id) {
        return preduzeceRepository.findById(id).flatMap(p -> p.getCenovniciPreduzeca().stream().max(Comparator.comparing(Cenovnik::getVaziOd))).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @GetMapping(value = "/{id}/proizvodi")
    public Set<RobaIliUsluga> getGrupaRobeIliUsluge(@PathVariable("id") String id) {
        return preduzeceRepository.getProizvodiPreduzeca(id);
    }
}
