package org.floresmateo.model;

public class Cancion extends Catalogo
{
    private String tituloCancion;
    private double duracion;
    private Disco disco;

    public Cancion()
    {
    }

    public Cancion(String tituloCancion, double duracion, Disco disco)
    {
        this.tituloCancion = tituloCancion;
        this.duracion = duracion;
        this.disco = disco;
    }

    public String getTituloCancion()
    {
        return tituloCancion;
    }

    public void setTituloCancion(String tituloCancion)
    {
        this.tituloCancion = tituloCancion;
    }

    public double getDuracion()
    {
        return duracion;
    }

    public void setDuracion(double duracion)
    {
        this.duracion = duracion;
    }

    public Disco getDisco()
    {
        return disco;
    }

    public void setDisco(Disco disco)
    {
        this.disco = disco;
    }

    @Override
    public String toString()
    {
        return "Cancion{" +
                "id=" + id +
                ", tituloCancion='" + tituloCancion + '\'' +
                ", duracion=" + duracion +
                ", disco=" + disco.getTituloDisco() +
                '}';
    }
}

