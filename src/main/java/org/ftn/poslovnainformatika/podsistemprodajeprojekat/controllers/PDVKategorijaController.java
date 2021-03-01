package org.ftn.poslovnainformatika.podsistemprodajeprojekat.controllers;

import org.ftn.poslovnainformatika.podsistemprodajeprojekat.model.PDVKategorija;
import org.ftn.poslovnainformatika.podsistemprodajeprojekat.model.PDVStopa;
import org.ftn.poslovnainformatika.podsistemprodajeprojekat.services.PDVKategorijaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/pdv-kategorije")
public class PDVKategorijaController {

    @Autowired
    private PDVKategorijaService pdvKategorijaService;

    @GetMapping
    public ResponseEntity<List<PDVKategorija>> getPdvKategorije() {
        return new ResponseEntity<>(pdvKategorijaService.getPdvKategorije(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<PDVKategorija> createPdvKategorija(@RequestBody PDVKategorija pdvKategorija) {
        return new ResponseEntity<>(pdvKategorijaService.createPdvKategorija(pdvKategorija), HttpStatus.CREATED);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<PDVKategorija> updatePdvKategorija(@PathVariable String id, @RequestBody PDVKategorija pdvKategorija) {
        var kategorija = pdvKategorijaService.updatePdvKategorija(id, pdvKategorija);
        if (kategorija != null) {
            return new ResponseEntity<>(kategorija, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity removePdvKategorija(@PathVariable String id) {
        if (pdvKategorijaService.removePdvKategorija(id)) {
            return new ResponseEntity(HttpStatus.OK);
        }
        return new ResponseEntity(HttpStatus.NOT_FOUND);
    }
}
