package org.ftn.poslovnainformatika.podsistemprodajeprojekat.controllers;

import org.ftn.poslovnainformatika.podsistemprodajeprojekat.model.PDVStopa;
import org.ftn.poslovnainformatika.podsistemprodajeprojekat.repository.PDVStopaRepository;
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

    @Autowired
    private PDVStopaRepository pdvStopaRepository;

    @GetMapping
    public ResponseEntity<List<PDVStopa>> getPdvStope() {
        return new ResponseEntity<>(pdvStopaService.getPdvStope(), HttpStatus.OK);
    }

    @GetMapping("/slobodne")
    public ResponseEntity<List<PDVStopa>> getSlobodneStope() {
        return new ResponseEntity<>(pdvStopaRepository.getSlobodneStope(), HttpStatus.OK);
    }

    @PostMapping
    public PDVStopa createNewPdvStopa(@RequestBody PDVStopa pdvStopa) {
        return pdvStopaRepository.save(pdvStopa);
    }

    @PostMapping(value = "/{idKategorije}")
    public ResponseEntity<PDVStopa> createPdvStopa(@PathVariable String idKategorije, @RequestBody PDVStopa pdvStopa) {
        var stopa = pdvStopaService.createPdvStopa(idKategorije, pdvStopa);
        if (stopa != null) {
            return new ResponseEntity<>(stopa, HttpStatus.CREATED);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @PutMapping(value = "/u/{id}")
    public ResponseEntity<PDVStopa> updatePdvStopa(@PathVariable String id, @RequestBody PDVStopa pdvStopa) {
        var stopa = pdvStopaService.updatePdvStopa(id, pdvStopa);
        if (stopa != null) {
            return new ResponseEntity<>(stopa, HttpStatus.OK);
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
