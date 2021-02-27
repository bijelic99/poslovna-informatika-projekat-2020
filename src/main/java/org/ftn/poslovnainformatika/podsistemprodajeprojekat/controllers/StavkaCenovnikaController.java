package org.ftn.poslovnainformatika.podsistemprodajeprojekat.controllers;

import org.ftn.poslovnainformatika.podsistemprodajeprojekat.model.StavkaCenovnika;
import org.ftn.poslovnainformatika.podsistemprodajeprojekat.services.StavkaCenovnikaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/stavke-cenovnika")
public class StavkaCenovnikaController {

    @Autowired
    private StavkaCenovnikaService stavkaCenovnikaService;

    @PutMapping(value = "/{id}")
    public ResponseEntity<StavkaCenovnika> updateStavkaCenovnika(@PathVariable String id, @RequestBody StavkaCenovnika stavkaCenovnika) {
        if (id != null) {
            return new ResponseEntity<>(stavkaCenovnikaService.updateStavkaCenovnika(id, stavkaCenovnika), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity removeStavkeCenovnika(@PathVariable String id) {
        if (stavkaCenovnikaService.removeStavkaCenovnika(id)) {
            return new ResponseEntity(HttpStatus.OK);
        }
        return new ResponseEntity(HttpStatus.NOT_FOUND);
    }
}
