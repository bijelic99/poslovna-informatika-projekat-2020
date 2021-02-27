package org.ftn.poslovnainformatika.podsistemprodajeprojekat.controllers;

import org.ftn.poslovnainformatika.podsistemprodajeprojekat.model.GrupaRobeIliUsluge;
import org.ftn.poslovnainformatika.podsistemprodajeprojekat.services.GrupaRobeIliUslugeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "api/grupe-robe-ili-usluge")
public class GrupaRobeIliUslugeController {

    @Autowired
    private GrupaRobeIliUslugeService grupaRobeIliUslugeService;

    @GetMapping
    public ResponseEntity<List<GrupaRobeIliUsluge>> getGrupeRobeIliUsluge() {
        return new ResponseEntity<>(grupaRobeIliUslugeService.getGrupeRobeIliUsluge(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<GrupaRobeIliUsluge> createGrupaRobeIliUsluge(@RequestBody GrupaRobeIliUsluge grupaRobeIliUsluge) {
        return new ResponseEntity<>(grupaRobeIliUslugeService.createGrupaRobeIliUsluge(grupaRobeIliUsluge), HttpStatus.CREATED);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<GrupaRobeIliUsluge> updateGrupaRobeIliUsluge(@PathVariable String id, @RequestBody GrupaRobeIliUsluge grupaRobeIliUsluge) {
        if (id != null) {
            return new ResponseEntity<>(grupaRobeIliUslugeService.updateGrupaRobeIliUsluge(id, grupaRobeIliUsluge), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity removeGrupaRobeIliUsluge(@PathVariable String id) {
        if (grupaRobeIliUslugeService.removeGrupaRobeIliUsluge(id)) {
            return new ResponseEntity(HttpStatus.OK);
        }
        return new ResponseEntity(HttpStatus.NOT_FOUND);
    }
}
