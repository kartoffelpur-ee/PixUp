package org.floresmateo.model;

public class Genero_Musical extends Catalogo
{
    private String genero;

    public Genero_Musical()
    {
    }

    public Genero_Musical(String genero)
    {
        this.genero = genero;
    }

    public String getGenero()
    {
        return genero;
    }

    public void setGenero(String genero)
    {
        this.genero = genero;
    }

    @Override
    public String toString()
    {
        return "Genero_Musical{" +
                "genero='" + genero + '\'' +
                ", id=" + id +
                '}';
    }
}

