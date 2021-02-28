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
public class NaseljenoMesto {

    @NonNull
    @Id
    @Column(length = 36)
    private String id;

    @NonNull
    private Integer pttBroj;

    @NonNull
    private String nazivMesta;

    @NonNull
    private Boolean obrisan = false;
}
