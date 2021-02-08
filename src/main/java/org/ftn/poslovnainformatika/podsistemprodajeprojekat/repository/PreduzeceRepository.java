package org.ftn.poslovnainformatika.podsistemprodajeprojekat.repository;

import org.ftn.poslovnainformatika.podsistemprodajeprojekat.model.Cenovnik;
import org.ftn.poslovnainformatika.podsistemprodajeprojekat.model.Preduzece;
import org.ftn.poslovnainformatika.podsistemprodajeprojekat.model.RobaIliUsluga;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;
import java.util.Set;

public interface PreduzeceRepository extends JpaRepository<Preduzece, String> {

    @Query("Select r from Preduzece p join p.grupeRobaIliUsluga g join RobaIliUsluga r on r.grupaRobeIliUsluge.id = g.id where p.id = :id")
    Set<RobaIliUsluga> getProizvodiPreduzeca(@Param("id") String id);
}
