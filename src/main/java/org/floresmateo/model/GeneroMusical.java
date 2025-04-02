package org.floresmateo.model;

public class GeneroMusical extends Catalogo
{
    private String genero;

    public GeneroMusical()
    {
    }

    public GeneroMusical(String genero)
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
        return "GeneroMusical{" +
                "genero='" + genero + '\'' +
                ", id=" + id +
                '}';
    }
}
