package org.ftn.poslovnainformatika.podsistemprodajeprojekat.services;

import org.ftn.poslovnainformatika.podsistemprodajeprojekat.model.Cenovnik;
import org.ftn.poslovnainformatika.podsistemprodajeprojekat.model.Preduzece;
import org.ftn.poslovnainformatika.podsistemprodajeprojekat.repository.PreduzeceRepository;
import org.joda.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Comparator;
import java.util.Optional;

@Service
public class CenovnikService {
    @Autowired
    PreduzeceRepository preduzeceRepository;

    public Optional<Cenovnik> getCenovnikPreduzecaForDate(String preduzeceId, LocalDate datum) {
        return preduzeceRepository
                .findById(preduzeceId)
                .stream()
                .map(Preduzece::getCenovniciPreduzeca)
                .flatMap(Collection::stream)
                .filter(cenovnik -> cenovnik.getVaziOd().isEqual(datum) || cenovnik.getVaziOd().isBefore(datum))
                .max(Comparator.comparing(Cenovnik::getVaziOd));
    }
}
