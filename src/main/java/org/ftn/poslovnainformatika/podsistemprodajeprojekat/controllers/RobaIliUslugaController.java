package org.ftn.poslovnainformatika.podsistemprodajeprojekat.controllers;

import org.ftn.poslovnainformatika.podsistemprodajeprojekat.services.RobaIliUslugaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "api/robe-ili-usluge")
public class RobaIliUslugaController {

    @Autowired
    private RobaIliUslugaService robaIliUslugaService;

    @GetMapping
    public ResponseEntity getRobeIliUsluge() {
        return new ResponseEntity<>(robaIliUslugaService.getRobeIliUsluge(), HttpStatus.OK);
    }
}
