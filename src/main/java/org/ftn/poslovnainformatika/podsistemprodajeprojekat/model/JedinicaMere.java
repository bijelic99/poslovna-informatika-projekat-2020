package org.ftn.poslovnainformatika.podsistemprodajeprojekat.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.persistence.Column;
import javax.persistence.Id;

import javax.persistence.Entity;

@Data
@NoArgsConstructor
@Entity
public class JedinicaMere {

    @NonNull
    @Id
    @Column(length = 36)
    private String id;

    @NonNull
    private String naziv;

    private String skraceniNaziv;

    @NonNull
    private Boolean obrisan = false;
}
