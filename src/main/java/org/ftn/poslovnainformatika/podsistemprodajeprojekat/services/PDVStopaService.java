package org.ftn.poslovnainformatika.podsistemprodajeprojekat.services;

import org.ftn.poslovnainformatika.podsistemprodajeprojekat.model.PDVStopa;
import org.ftn.poslovnainformatika.podsistemprodajeprojekat.repository.PDVStopaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class PDVStopaService {

    @Autowired
    private PDVStopaRepository pdvStopaRepository;

    public List<PDVStopa> getPdvStope() {
        return pdvStopaRepository.findAll();
    }

    public PDVStopa createPdvStopa(PDVStopa pdvStopa) {
        pdvStopa.setId(UUID.randomUUID().toString());
        return pdvStopaRepository.save(pdvStopa);
    }

    public PDVStopa updatePdvStopa(String id, PDVStopa pdvStopa) {
        var updatedPdvStopa = pdvStopaRepository.getOne(id);
        if (updatedPdvStopa != null) {
            updatedPdvStopa.setId(id);
            updatedPdvStopa.setDatumVazenja(pdvStopa.getDatumVazenja());
            updatedPdvStopa.setProcenat(pdvStopa.getProcenat());
            return pdvStopaRepository.save(pdvStopa);
        }
        return null;
    }

    public boolean removePdvStopa(String id) {
        var pdvStopa = pdvStopaRepository.getOne(id);
        if (pdvStopa != null) {
            pdvStopaRepository.delete(pdvStopa);
            return true;
        }
        return false;
    }
}
