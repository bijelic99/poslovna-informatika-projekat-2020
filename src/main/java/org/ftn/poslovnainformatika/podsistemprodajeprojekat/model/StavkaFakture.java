package org.ftn.poslovnainformatika.podsistemprodajeprojekat.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.persistence.Column;
import javax.persistence.Id;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Data
@NoArgsConstructor
@Entity
public class StavkaFakture {

    @NonNull
    @Id
    @Column(length = 36)
    private String id;

    @NonNull
    private Double kolicina;

    @NonNull
    private Double rabat;

    @NonNull
    private Double jedinicnaCena;

    @NonNull
    private Double PDVStopa;

    @NonNull
    private Double osnovicaZaPDV;

    @NonNull
    private Double iznosPDV;

    @NonNull
    private Double ukupanIznos;

    @NonNull
    @ManyToOne
    private RobaIliUsluga robaIliUsluga;
}
