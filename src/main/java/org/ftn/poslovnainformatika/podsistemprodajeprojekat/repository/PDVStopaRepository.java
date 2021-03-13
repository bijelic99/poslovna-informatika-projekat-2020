package org.ftn.poslovnainformatika.podsistemprodajeprojekat.repository;

import org.ftn.poslovnainformatika.podsistemprodajeprojekat.model.PDVStopa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PDVStopaRepository extends JpaRepository<PDVStopa, String> {

    @Query("select pst from PDVStopa pst where pst.id not in (select stope.id from PDVKategorija pkt join pkt.stopePDV stope)")
    List<PDVStopa> getSlobodneStope();
}
