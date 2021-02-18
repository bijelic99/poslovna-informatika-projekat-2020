package org.ftn.poslovnainformatika.podsistemprodajeprojekat.controllers;

import org.ftn.poslovnainformatika.podsistemprodajeprojekat.model.Faktura;
import org.ftn.poslovnainformatika.podsistemprodajeprojekat.repository.FakturaRepository;
import org.ftn.poslovnainformatika.podsistemprodajeprojekat.services.CenovnikService;
import org.ftn.poslovnainformatika.podsistemprodajeprojekat.services.FakturaService;
import org.joda.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/fakture")
public class FakturaController {

    @Autowired
    FakturaRepository fakturaRepository;

    @Autowired
    FakturaService fakturaService;

    @Autowired
    CenovnikService cenovnikService;

    @PutMapping
    public Faktura putFaktura(@RequestBody Faktura faktura) {
        return fakturaService.saveFaktura(faktura);
    }

    @PostMapping
    public Faktura postFaktura(@RequestBody Faktura faktura) {
        return fakturaService.createFaktura(faktura);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Faktura> getFaktura(@PathVariable("id") String id) {
        return fakturaRepository.findById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @GetMapping(value = "/{datumOd}/{datumDo}")
    public ResponseEntity searchFakture(@PathVariable LocalDate datumOd, @PathVariable LocalDate datumDo) {
        return new ResponseEntity(fakturaService.searchFakture(datumOd, datumDo), HttpStatus.OK);
    }

}
