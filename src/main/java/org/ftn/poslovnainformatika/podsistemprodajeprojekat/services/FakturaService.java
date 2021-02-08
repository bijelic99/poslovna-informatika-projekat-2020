package org.ftn.poslovnainformatika.podsistemprodajeprojekat.services;

import org.ftn.poslovnainformatika.podsistemprodajeprojekat.model.Faktura;
import org.ftn.poslovnainformatika.podsistemprodajeprojekat.repository.FakturaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FakturaService {
    @Autowired
    FakturaRepository fakturaRepository;
}
