package org.ftn.poslovnainformatika.podsistemprodajeprojekat.controllers;

import org.ftn.poslovnainformatika.podsistemprodajeprojekat.model.Faktura;
import org.ftn.poslovnainformatika.podsistemprodajeprojekat.repository.FakturaRepository;
import org.ftn.poslovnainformatika.podsistemprodajeprojekat.services.FakturaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("api/fakture")
public class FakturaController {

    @Autowired
    FakturaRepository fakturaRepository;

    @PostMapping
    public Faktura addFaktura(@RequestBody Faktura faktura){
        return fakturaRepository.save(faktura);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Faktura> getFaktura(@PathVariable("id") String id){
        return fakturaRepository.findById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

}
