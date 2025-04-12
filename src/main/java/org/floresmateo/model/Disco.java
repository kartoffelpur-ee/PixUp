package org.floresmateo.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString
@Entity
@Table(name = "TBL_DISCO")

public class Disco extends Catalogo
{
    @Column(name = "TITULODISCO", nullable = false)
    private String tituloDisco;

    @Column(name = "PRECIO", nullable = false)
    private double precio;

    @Column(name = "EXISTENCIA", nullable = false)
    private int existencias;

    @Column(name = "DESCUENTO", nullable = false)
    private double descuento;

    @Column(name = "FECHA_LANZAMIENTO", nullable = false)
    private LocalDate fechaLanzamiento;

    @Column(name = "IMAGEN", nullable = false)
    private String imagen;

    @ManyToOne
    @JoinColumn(name = "TBL_ARTISTA_ID")
    private Artista artista;

    @ManyToOne
    @JoinColumn(name = "TBL_DISQUERA_ID")
    private Disquera disquera;

    @ManyToOne
    @JoinColumn(name = "TBL_GENERO_MUSICAL_ID")
    private Genero_Musical generoMusical;
}

