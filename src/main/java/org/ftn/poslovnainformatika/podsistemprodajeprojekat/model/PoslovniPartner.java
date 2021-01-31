package org.ftn.poslovnainformatika.podsistemprodajeprojekat.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.persistence.*;

@Data
@NoArgsConstructor
@Entity
public class PoslovniPartner {

    @NonNull
    @Id
    @Column(length = 36)
    private String id;

    @NonNull
    private String naziv;

    @NonNull
    private String adresa;

    @NonNull
    private String telefon;

    private String fax;

    @NonNull
    private String email;

    @NonNull
    @Enumerated(EnumType.STRING)
    private VrstaPartnera vrstaPartnera;
}
