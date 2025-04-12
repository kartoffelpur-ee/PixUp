package org.floresmateo.model;

import jakarta.persistence.*;
import lombok.*;
import java.io.Serializable;

@Data //Creador de getters y setters
@AllArgsConstructor //Constructor con todos los argumentos
@NoArgsConstructor //Constructor vacío (default)
@EqualsAndHashCode(callSuper = true)
/*
Sirven para comparar objetos de una clase de manera lógica
y para que funcionen bien en estructuras de datos como HashSet, HashMap, etc.
 */
@ToString //Creador de ToString
@Entity //Le dice a Hibernate que esto es una entidad
@Table( name="TBL_ESTADO" ) // Le dice a Hibernate a qué tabla de la BD refiere

public class Estado extends Catalogo implements Serializable
{
    @Column(name = "ESTADO", nullable = false )
    private String nombre;
}
