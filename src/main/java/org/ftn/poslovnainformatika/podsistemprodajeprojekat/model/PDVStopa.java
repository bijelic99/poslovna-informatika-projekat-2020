package org.ftn.poslovnainformatika.podsistemprodajeprojekat.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.hibernate.annotations.Type;
import org.joda.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Id;

import javax.persistence.Entity;

@Data
@NoArgsConstructor
@Entity
public class PDVStopa {

    @NonNull
    @Id
    @Column(length = 36)
    private String id;

    @NonNull
    private Double procenat;

    @NonNull
    @Type(type="org.jadira.usertype.dateandtime.joda.PersistentLocalDate")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private LocalDate datumVazenja;

    @NonNull
    private Boolean obrisan = false;
}
