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
public class StavkaCenovnika {

    @NonNull
    @Id
    @Column(length = 36)
    private String id;

    @NonNull
    private Double cena;

    @ManyToOne
    private RobaIliUsluga robaIliUsluga;
}
