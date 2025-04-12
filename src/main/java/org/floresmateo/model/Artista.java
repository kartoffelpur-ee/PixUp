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
@Table(name = "TBL_ARTISTA")

public class Artista extends Catalogo
{
    @Column(name = "ARTISTA", nullable = false)
    private String artista;
}

