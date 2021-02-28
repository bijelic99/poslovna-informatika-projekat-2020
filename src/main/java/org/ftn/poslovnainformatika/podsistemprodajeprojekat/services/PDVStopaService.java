package org.ftn.poslovnainformatika.podsistemprodajeprojekat.services;

import org.ftn.poslovnainformatika.podsistemprodajeprojekat.model.PDVStopa;
import org.ftn.poslovnainformatika.podsistemprodajeprojekat.repository.PDVStopaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@Transactional
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
        var updatedPdvStopa = pdvStopaRepository.findById(id).orElse(null);
        if(updatedPdvStopa != null) {
            updatedPdvStopa.setDatumVazenja(pdvStopa.getDatumVazenja());
            updatedPdvStopa.setProcenat(pdvStopa.getProcenat());
            return pdvStopaRepository.save(updatedPdvStopa);
        }

        return null;
    }

    public boolean removePdvStopa(String id) {
        var pdvStopa = pdvStopaRepository.findById(id).orElse(null);
        if (pdvStopa != null) {
            pdvStopa.setObrisan(true);
            pdvStopaRepository.save(pdvStopa);
            return true;
        }
        return false;
    }
}
