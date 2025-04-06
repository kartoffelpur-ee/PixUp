package org.floresmateo.model;
import java.io.Serializable;

public class Municipio extends Catalogo implements Serializable
{
    private String nombre;
    private Estado estado;

    public Municipio()
    {
    }

    public Municipio(String nombre, Estado estado)
    {
        this.nombre = nombre;
        this.estado = estado;
    }

    public String getNombre()
    {
        return nombre;
    }

    public void setNombre(String nombre)
    {
        this.nombre = nombre;
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

