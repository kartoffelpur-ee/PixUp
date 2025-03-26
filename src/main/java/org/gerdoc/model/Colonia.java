package org.gerdoc.model;

public class Colonia extends Entidad
{
    private String cp;
    private Municipio municipio;

    public Colonia() {
    }

    public Colonia(Integer id, String nombre, String cp, Municipio municipio)
    {
        super(id, nombre);
        this.cp = cp;
        this.municipio = municipio;
    }

    public String getCp()
    {
        return cp;
    }

    public void setCp(String cp)
    {
        this.cp = cp;
    }

    public Municipio getMunicipio()
    {
        return municipio;
    }

    public void setMunicipio(Municipio municipio)
    {
        this.municipio = municipio;
    }

    @Override
    public String toString()
    {
        return "ID: "+getId()+"\nNombre: "+getNombre()+"\nCódigo postal: "+cp+"\nMunicipio: "+municipio.getNombre();
    }
}
