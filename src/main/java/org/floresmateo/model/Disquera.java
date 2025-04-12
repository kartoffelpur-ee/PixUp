package org.floresmateo.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Entity
@Table( name = "TBL_DISQUERA" )

public class Disquera extends Catalogo
{
    @Column( name = "DISQUERA", nullable = false )
    private String disquera;
}

