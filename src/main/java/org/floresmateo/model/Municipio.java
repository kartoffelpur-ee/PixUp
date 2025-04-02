package org.floresmateo.model;

import java.io.Serializable;

public class Municipio extends Catalogo implements Serializable
{
    private Estado estado;

    public Municipio()
    {
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    @Override
    public String toString()
    {
        return "Municipio {"+
                "nombre='" + nombre + '\'' +
                ", id=" + id +
                ", estado="+estado.getNombre()+
                "}";
    }
}

