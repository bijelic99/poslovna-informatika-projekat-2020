package org.ftn.poslovnainformatika.podsistemprodajeprojekat.controllers;

import org.ftn.poslovnainformatika.podsistemprodajeprojekat.model.Cenovnik;
import org.ftn.poslovnainformatika.podsistemprodajeprojekat.services.CenovnikService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/cenovnici")
public class CenovnikController {

    @Autowired
    private CenovnikService cenovnikService;

    @PostMapping
    public ResponseEntity createCenovnik(@RequestBody Cenovnik cenovnik) {
        return new ResponseEntity(cenovnikService.createCenovnik(cenovnik), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity getCenovnici() {
        return new ResponseEntity(cenovnikService.getCenovnici(), HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity getCenovnik(@PathVariable String id) {
        return new ResponseEntity(cenovnikService.getCenovnik(id), HttpStatus.OK);
    }

    @PostMapping(value = "/{procenat}/{povecanje}")
    public ResponseEntity copyCenovnik(@RequestBody Cenovnik cenovnik, @PathVariable int procenat, @PathVariable Boolean povecanje) {
        return new ResponseEntity(cenovnikService.copyCenovnik(cenovnik, procenat, povecanje), HttpStatus.CREATED);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity removeCenovnik(@PathVariable String id) {
        if (cenovnikService.removeCenovnik(id)) {
            return new ResponseEntity(HttpStatus.OK);
        }
        return new ResponseEntity(HttpStatus.NOT_FOUND);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Cenovnik> updateCenovnik(@PathVariable String id, @RequestBody Cenovnik cenovnik) {
        if (id != null) {
            return new ResponseEntity<>(cenovnikService.updateCenovnik(id, cenovnik), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
