package org.ftn.poslovnainformatika.podsistemprodajeprojekat.controllers;

import org.ftn.poslovnainformatika.podsistemprodajeprojekat.model.JedinicaMere;
import org.ftn.poslovnainformatika.podsistemprodajeprojekat.services.JedinicaMereService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "api/jedinice-mere")
public class JedinicaMereController {

    @Autowired
    private JedinicaMereService jedinicaMereService;

    @GetMapping
    public ResponseEntity<List<JedinicaMere>> getJediniceMere() {
        return new ResponseEntity<>(jedinicaMereService.getJediniceMere(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<JedinicaMere> createJedinicaMere(@RequestBody JedinicaMere jedinicaMere) {
        return new ResponseEntity<>(jedinicaMereService.createJedinicaMere(jedinicaMere), HttpStatus.CREATED);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<JedinicaMere> updateJedinicaMere(@PathVariable String id, @RequestBody JedinicaMere jedinicaMere) {
        if (id != null) {
            return new ResponseEntity<>(jedinicaMereService.updateJedinicaMere(id,jedinicaMere), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity removeJedinicaMere(@PathVariable String id) {
        if (jedinicaMereService.removeJedinicaMere(id)) {
            return new ResponseEntity(HttpStatus.OK);
        }
        return new ResponseEntity(HttpStatus.NOT_FOUND);
    }
}
