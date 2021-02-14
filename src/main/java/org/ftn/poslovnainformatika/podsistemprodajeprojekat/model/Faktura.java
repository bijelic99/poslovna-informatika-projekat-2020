package org.ftn.poslovnainformatika.podsistemprodajeprojekat.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.hibernate.annotations.Type;
import org.joda.time.LocalDate;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@Entity
public class Faktura {

    @NonNull
    @Id
    @Column(length = 36)
    private String id;

    @NonNull
    private Integer brojFakture;

    @NonNull
    @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentLocalDate")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private LocalDate datumFakture;

    @NonNull
    @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentLocalDate")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private LocalDate datumValute;

    @NonNull
    private Double ukupnaOsnovica;

    @NonNull
    private Double ukupanPDV;

    @NonNull
    private Double ukupanIznos;

    @NonNull
    @ManyToOne
    private PoslovnaGodina poslovnaGodina;

    @NonNull
    private Boolean fakturaGotova = false;

    @NonNull
    @ManyToOne
    private Preduzece preduzece;

    @NonNull
    @ManyToOne
    private PoslovniPartner kupac;

    @NonNull
    @OneToMany(orphanRemoval = true)
    private Set<StavkaFakture> stavke = new HashSet<>();
}
