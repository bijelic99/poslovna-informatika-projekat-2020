package org.ftn.poslovnainformatika.podsistemprodajeprojekat.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.persistence.Column;
import javax.persistence.Id;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@Entity
public class Preduzece {

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

    @OneToMany(orphanRemoval = true)
    private Set<PoslovniPartner> poslovniPartneri = new HashSet<>();

    @NonNull
    @OneToMany(orphanRemoval = true)
    private Set<Cenovnik> cenovniciPreduzeca = new HashSet<>();

    @NonNull
    @OneToMany(orphanRemoval = true)
    private Set<PoslovnaGodina> poslovneGodine = new HashSet<>();

    @NonNull
    @OneToMany(orphanRemoval = true)
    private Set<GrupaRobeIliUsluge> grupeRobaIliUsluga = new HashSet<>();
}
