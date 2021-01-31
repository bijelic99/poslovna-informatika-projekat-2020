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
public class PoslovnaGodina {

    @NonNull
    @Id
    @Column(length = 36)
    private String id;

    @NonNull
    private Integer godina;

    @NonNull
    private Boolean zakljucena = false;
}
