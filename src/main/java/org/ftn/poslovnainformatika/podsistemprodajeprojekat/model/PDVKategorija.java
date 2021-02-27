package org.ftn.poslovnainformatika.podsistemprodajeprojekat.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.hibernate.engine.internal.Cascade;

import javax.persistence.*;

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
    @OneToMany(cascade = CascadeType.ALL)
    private Set<PDVStopa> stopePDV = new HashSet<>();
}
