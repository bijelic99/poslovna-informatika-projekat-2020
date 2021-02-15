package org.ftn.poslovnainformatika.podsistemprodajeprojekat.controllers;

import org.ftn.poslovnainformatika.podsistemprodajeprojekat.model.Faktura;
import org.ftn.poslovnainformatika.podsistemprodajeprojekat.repository.FakturaRepository;
import org.ftn.poslovnainformatika.podsistemprodajeprojekat.services.CenovnikService;
import org.ftn.poslovnainformatika.podsistemprodajeprojekat.services.FakturaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.MediaType;
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

    @GetMapping(value = "/{id}/pdf")
    public ResponseEntity<InputStreamResource> getFakturaPdf(@PathVariable("id") String id) {
        return fakturaService.getFakturaPdf(id).map(inputStreamResource -> ResponseEntity.ok().contentType(MediaType.APPLICATION_PDF).body(inputStreamResource))
                .orElse(ResponseEntity.notFound().build());
    }

}
