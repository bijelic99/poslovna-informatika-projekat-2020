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
public class PDVKategorija {

    @NonNull
    @Id
    @Column(length = 36)
    private String id;

    @NonNull
    private String nazivKategorije;

    @NonNull
    @OneToMany(orphanRemoval = true)
    private Set<PDVStopa> stopePDV = new HashSet<>();
}
