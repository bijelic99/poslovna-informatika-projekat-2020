package org.ftn.poslovnainformatika.podsistemprodajeprojekat.controllers;

import org.ftn.poslovnainformatika.podsistemprodajeprojekat.model.*;
import org.ftn.poslovnainformatika.podsistemprodajeprojekat.repository.NaseljenoMestoRepository;
import org.ftn.poslovnainformatika.podsistemprodajeprojekat.repository.PreduzeceRepository;
import org.joda.time.format.DateTimeFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@RestController
@RequestMapping("api/preduzeca")
public class PreduzeceController {

    @Autowired
    PreduzeceRepository preduzeceRepository;

    @Autowired
    NaseljenoMestoRepository naseljenoMestoRepository;

    @GetMapping(value = "/{id}")
    public ResponseEntity<Preduzece> getPreduzece(@PathVariable("id") String id) {
        return preduzeceRepository.findById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @GetMapping(value = "/{id}/partneri")
    public ResponseEntity<Set<PoslovniPartner>> getPoslovniPartneri(@PathVariable("id") String id) {
        return preduzeceRepository.findById(id).map(Preduzece::getPoslovniPartneri).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @GetMapping(value = "/{id}/proizvodi")
    public Set<RobaIliUsluga> getGrupaRobeIliUsluge(@PathVariable("id") String id) {
        return preduzeceRepository.getProizvodiPreduzeca(id);
    }

    @GetMapping(value = "/{id}/cenovnik/{datum}")
    public ResponseEntity<Cenovnik> getCenovnikForPreduzece(@PathVariable("id") String id, @PathVariable("datum") String datum) {
        try {
            var dateTimeFormat = DateTimeFormat.forPattern("dd-MM-yyyy");
            var ldatum = dateTimeFormat.parseLocalDate(datum);
            return preduzeceRepository
                    .findById(id)
                    .stream()
                    .flatMap(p -> p.getCenovniciPreduzeca().stream())
                    .filter(c -> c.getVaziOd().isBefore(ldatum) || c.getVaziOd().isBefore(ldatum))
                    .max(Comparator.comparing(Cenovnik::getVaziOd))
                    .map(ResponseEntity::ok)
                    .orElse(ResponseEntity.notFound().build());
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }

    }

    @PostMapping
    public ResponseEntity<Preduzece> addPreduzece(@RequestBody Preduzece novoPreduzece){
        novoPreduzece.setId(UUID.randomUUID().toString());
        try{
            return ResponseEntity.ok(preduzeceRepository.save(novoPreduzece));
        }
        catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping(value = "/mesta")
    public List<NaseljenoMesto> getMesta() {
        return naseljenoMestoRepository.findAll();
    }
}
