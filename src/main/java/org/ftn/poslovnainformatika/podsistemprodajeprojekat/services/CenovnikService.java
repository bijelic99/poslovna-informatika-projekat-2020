package org.ftn.poslovnainformatika.podsistemprodajeprojekat.services;

import org.ftn.poslovnainformatika.podsistemprodajeprojekat.model.Cenovnik;
import org.ftn.poslovnainformatika.podsistemprodajeprojekat.model.Preduzece;
import org.ftn.poslovnainformatika.podsistemprodajeprojekat.model.StavkaCenovnika;
import org.ftn.poslovnainformatika.podsistemprodajeprojekat.repository.CenovnikRepository;
import org.ftn.poslovnainformatika.podsistemprodajeprojekat.repository.PreduzeceRepository;
import org.ftn.poslovnainformatika.podsistemprodajeprojekat.repository.StavkaCenovnikaRepository;
import org.joda.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@Transactional
public class CenovnikService {
    @Autowired
    PreduzeceRepository preduzeceRepository;

    @Autowired
    private CenovnikRepository cenovnikRepository;

    @Autowired
    private StavkaCenovnikaRepository stavkaCenovnikaRepository;

    public Optional<Cenovnik> getCenovnikPreduzecaForDate(String preduzeceId, LocalDate datum) {
        return preduzeceRepository
                .findById(preduzeceId)
                .stream()
                .map(Preduzece::getCenovniciPreduzeca)
                .flatMap(Collection::stream)
                .filter(cenovnik -> cenovnik.getVaziOd().isEqual(datum) || cenovnik.getVaziOd().isBefore(datum))
                .max(Comparator.comparing(Cenovnik::getVaziOd));
    }

    public Cenovnik createCenovnik(Cenovnik cenovnik) {
        cenovnik.setId(UUID.randomUUID().toString());
        var stavkeCenovnika = cenovnik.getStavkeCenovnika();
        stavkeCenovnika.forEach(x -> x.setId(UUID.randomUUID().toString()));
        stavkaCenovnikaRepository.saveAll(stavkeCenovnika);
        return cenovnikRepository.save(cenovnik);
    }

    public List<Cenovnik> getCenovnici() {
        return cenovnikRepository.findAll();
    }

    public Cenovnik getCenovnik(String id) {
        return cenovnikRepository.getOne(id);
    }

    public Cenovnik copyCenovnik(Cenovnik cenovnik, int procenat, boolean povecanje) {
        Cenovnik kopiraniCenovnik = new Cenovnik();
        kopiraniCenovnik.setId(UUID.randomUUID().toString());
        kopiraniCenovnik.setVaziOd(LocalDate.now());
        double novaCena = 0;
        var noveStavkeCenovnika = new HashSet<StavkaCenovnika>();

        for (StavkaCenovnika sc : cenovnik.getStavkeCenovnika()) {
            StavkaCenovnika stavkaCenovnika = new StavkaCenovnika();
            stavkaCenovnika.setId(UUID.randomUUID().toString());
            stavkaCenovnika.setCena(sc.getCena());
            stavkaCenovnika.setRobaIliUsluga(sc.getRobaIliUsluga());
            noveStavkeCenovnika.add(stavkaCenovnika);
        }

        if (povecanje) {
            for (StavkaCenovnika sc : noveStavkeCenovnika) {
                novaCena = (sc.getCena() * (100 + procenat)) / 100;
                sc.setCena(novaCena);
            }
        } else {
            for (StavkaCenovnika sc : noveStavkeCenovnika) {
                novaCena = (sc.getCena() * (100 - procenat)) / 100;
                sc.setCena(novaCena);
            }
        }
        kopiraniCenovnik.setStavkeCenovnika(noveStavkeCenovnika);
        stavkaCenovnikaRepository.saveAll(noveStavkeCenovnika);
        return cenovnikRepository.save(kopiraniCenovnik);
    }

    public Cenovnik updateCenovnik(String id, Cenovnik cenovnik) {
        var updatedCenovnik = cenovnikRepository.findById(id).orElse(null);
        if (updatedCenovnik != null) {
            updatedCenovnik.setVaziOd(cenovnik.getVaziOd());
            return cenovnikRepository.save(updatedCenovnik);
        }
        return null;
    }

    public boolean removeCenovnik(String id) {
        var cenovnik = cenovnikRepository.findById(id).orElse(null);
        if (cenovnik != null) {
            cenovnik.setObrisan(true);
            cenovnikRepository.save(cenovnik);
            return true;
        }
        return false;
    }
}
