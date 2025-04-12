package org.floresmateo.model;

import jakarta.persistence.*;
import lombok.*;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Entity
@Table( name="TBL_MUNICIPIO" )

public class Municipio extends Catalogo implements Serializable
{
    @Column( name = "MUNICIPIO", nullable = false )
    private String nombre;

    @ManyToOne
    @JoinColumn( name = "TBL_ESTADO_ID" )
    private Estado estado;
}

