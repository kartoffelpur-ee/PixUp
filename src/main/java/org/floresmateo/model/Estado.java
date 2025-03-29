package org.floresmateo.model;

import java.io.Serializable;

public class Estado extends Catalogo implements Serializable
{
    public Estado() {
    }

    @Override
    public String toString()
    {
        return "Estado {"+
                "nombre='" + nombre + '\'' +
                ", id=" + id +
                "}";
    }
}

