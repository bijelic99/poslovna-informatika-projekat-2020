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
public class Cenovnik {

    @NonNull
    @Id
    @Column(length = 36)
    private String id;

    @Type(type="org.jadira.usertype.dateandtime.joda.PersistentLocalDate")
    private LocalDate vaziOd;

    @NonNull
    @OneToMany(cascade = CascadeType.ALL)
    private Set<StavkaCenovnika> stavkeCenovnika = new HashSet<>();

    @NonNull
    private Boolean obrisan = false;
}
