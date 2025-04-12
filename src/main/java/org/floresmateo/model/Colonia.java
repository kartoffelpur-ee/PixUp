package org.floresmateo.model;

import jakarta.persistence.*;
import lombok.*;

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "TBL_COLONIA")

public class Colonia extends Catalogo
{
    @Column(name="COLONIA", nullable = false)
    private String colonia;

    @Column(name="CP", nullable = false)
    private String cp;

    @ManyToOne
    @JoinColumn(name = "TBL_MUNICIPIO_ID")
    private Municipio municipio;
}
