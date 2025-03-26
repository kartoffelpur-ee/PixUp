package org.gerdoc.model;

public abstract class Entidad
{
    protected Integer id;
    protected String nombre;

    public Entidad() {
    }

    public Entidad(Integer id, String nombre)
    {
        this.id = id;
        this.nombre = nombre;
    }

    public Integer getId()
    {
        return id;
    }

    public void setId(Integer id)
    {
        this.id = id;
    }

    public String getNombre()
    {
        return nombre;
    }

    public void setNombre(String nombre)
    {
        this.nombre = nombre;
    }

    @Override
    public String toString()
    {
        return "ID: "+id+"\nNombre: "+nombre;
    }
}
