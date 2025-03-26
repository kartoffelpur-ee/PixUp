package org.gerdoc.model;

public class Municipio extends Entidad
{
    private Estado estado;

    public Municipio() {
    }

    public Municipio(Integer id, String nombre, Estado estado)
    {
        super(id, nombre);
        this.estado = estado;
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
        return "ID: "+ getId() +"\nNombre: "+ getNombre() +"\nEstado: "+ estado.getNombre();
    }
}
