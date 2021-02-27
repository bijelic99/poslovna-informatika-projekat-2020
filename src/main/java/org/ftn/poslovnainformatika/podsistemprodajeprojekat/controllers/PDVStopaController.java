package org.ftn.poslovnainformatika.podsistemprodajeprojekat.controllers;

import org.ftn.poslovnainformatika.podsistemprodajeprojekat.model.PDVStopa;
import org.ftn.poslovnainformatika.podsistemprodajeprojekat.services.PDVStopaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/pdv-stope")
public class PDVStopaController {

    @Autowired
    private PDVStopaService pdvStopaService;

    @GetMapping
    public ResponseEntity<List<PDVStopa>> getPdvStope() {
        return new ResponseEntity<>(pdvStopaService.getPdvStope(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<PDVStopa> createPdvStopa(@RequestBody PDVStopa pdvStopa) {
        return new ResponseEntity<>(pdvStopaService.createPdvStopa(pdvStopa), HttpStatus.CREATED);
    }

    @PutMapping(value = "/u/{id}")
    public ResponseEntity<PDVStopa> updatePdvStopa(@PathVariable String id, @RequestBody PDVStopa pdvStopa) {
        if (id != null) {
            return new ResponseEntity<>(pdvStopaService.updatePdvStopa(id, pdvStopa), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity removePdvStopa(@PathVariable String id) {
        if (pdvStopaService.removePdvStopa(id)) {
            return new ResponseEntity(HttpStatus.OK);
        }
        return new ResponseEntity(HttpStatus.NOT_FOUND);
    }
}
