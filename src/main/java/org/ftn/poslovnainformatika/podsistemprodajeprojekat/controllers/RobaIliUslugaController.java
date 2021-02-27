package org.ftn.poslovnainformatika.podsistemprodajeprojekat.controllers;

import org.ftn.poslovnainformatika.podsistemprodajeprojekat.model.RobaIliUsluga;
import org.ftn.poslovnainformatika.podsistemprodajeprojekat.services.RobaIliUslugaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "api/robe-ili-usluge")
public class RobaIliUslugaController {

    @Autowired
    private RobaIliUslugaService robaIliUslugaService;

    @GetMapping
    public ResponseEntity<List<RobaIliUsluga>> getRobeIliUsluge() {
        return new ResponseEntity<>(robaIliUslugaService.getRobeIliUsluge(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<RobaIliUsluga> createRobaIliUsluga(@RequestBody RobaIliUsluga robaIliUsluga) {
        return new ResponseEntity<>(robaIliUslugaService.createRobaIliUsluga(robaIliUsluga), HttpStatus.CREATED);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<RobaIliUsluga> updateRObaIliUsluga(@PathVariable String id, @RequestBody RobaIliUsluga robaIliUsluga) {
        if (id != null) {
            return new ResponseEntity<>(robaIliUslugaService.updateRobaIliUsluga(id, robaIliUsluga), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity removeRobaIliUsluga(@PathVariable String id) {
        if (robaIliUslugaService.removeRobaIliUsluga(id)) {
            return new ResponseEntity(HttpStatus.OK);
        }
        return new ResponseEntity(HttpStatus.NOT_FOUND);
    }
}
