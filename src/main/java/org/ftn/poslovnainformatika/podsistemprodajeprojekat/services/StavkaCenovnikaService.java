package org.ftn.poslovnainformatika.podsistemprodajeprojekat.services;

import org.ftn.poslovnainformatika.podsistemprodajeprojekat.model.StavkaCenovnika;
import org.ftn.poslovnainformatika.podsistemprodajeprojekat.repository.StavkaCenovnikaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class StavkaCenovnikaService {

    @Autowired
    private StavkaCenovnikaRepository stavkaCenovnikaRepository;

    public StavkaCenovnika updateStavkaCenovnika(String idStavke, StavkaCenovnika stavkaCenovnika) {
        var updatedStavka = stavkaCenovnikaRepository.findById(idStavke).orElse(null);
        if (updatedStavka != null) {
            updatedStavka.setCena(stavkaCenovnika.getCena());
            updatedStavka.setRobaIliUsluga(stavkaCenovnika.getRobaIliUsluga());
            return stavkaCenovnikaRepository.save(updatedStavka);
        }
        return null;
    }

    public boolean removeStavkaCenovnika(String id) {
        var stavka = stavkaCenovnikaRepository.findById(id).orElse(null);
        if (stavka != null) {
            stavka.setObrisan(true);
            stavkaCenovnikaRepository.save(stavka);
            return true;
        }
        return false;
    }
}
