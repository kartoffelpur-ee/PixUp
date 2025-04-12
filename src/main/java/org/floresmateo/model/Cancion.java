package org.floresmateo.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "TBL_CANCION")

public class Cancion extends Catalogo
{
    @Column(name = "TITULOCANCION")
    private String tituloCancion;

    @Column(name = "DURACION")
    private LocalTime duracion;

    @ManyToOne
    @JoinColumn(name = "TBL_DISCO_ID")
    private Disco disco;
}

