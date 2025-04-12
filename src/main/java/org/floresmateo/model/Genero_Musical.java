package org.floresmateo.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "TBL_GENERO_MUSICAL")

public class Genero_Musical extends Catalogo
{
    @Column(name = "GENERO", nullable = false)
    private String genero;
}

