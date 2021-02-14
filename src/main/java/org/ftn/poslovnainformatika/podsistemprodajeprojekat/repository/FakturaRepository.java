package org.ftn.poslovnainformatika.podsistemprodajeprojekat.repository;

import org.ftn.poslovnainformatika.podsistemprodajeprojekat.model.Faktura;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface FakturaRepository extends JpaRepository<Faktura, String> {

    @Query("select max(f.brojFakture) from Faktura f where f.poslovnaGodina.godina = :godina")
    Optional<Integer> getPoslednjiBrojFaktureZaGodinu(@Param("godina") Integer godina);
}
