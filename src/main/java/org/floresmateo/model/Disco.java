package org.floresmateo.model;

import java.sql.Date;

public class Disco extends Catalogo
{
    private String tituloDisco;
    private double precio;
    private int existencias;
    private double descuento;
    private String fechaLanzamiento;
    private String imagen;
    private Disquera disquera;
    private Artista artista;
    private Genero_Musical generoMusical;

    public Disco()
    {
    }

    public Disco(String tituloDisco,
                 double precio,
                 int existencias,
                 double descuento,
                 String fechaLanzamiento,
                 String imagen,
                 Disquera disquera,
                 Artista artista,
                 Genero_Musical generoMusical)
    {
        this.tituloDisco = tituloDisco;
        this.precio = precio;
        this.existencias = existencias;
        this.descuento = descuento;
        this.fechaLanzamiento = fechaLanzamiento;
        this.imagen = imagen;
        this.disquera = disquera;
        this.artista = artista;
        this.generoMusical = generoMusical;
    }

    public String getTituloDisco()
    {
        return tituloDisco;
    }

    public void setTituloDisco(String tituloDisco)
    {
        this.tituloDisco = tituloDisco;
    }

    public void setPrecio(double precio)
    {
        this.precio = precio;
    }

    public void setExistencias(int existencias)
    {
        this.existencias = existencias;
    }

    public void setDescuento(double descuento)
    {
        this.descuento = descuento;
    }

    public void setFechaLanzamiento(String fechaLanzamiento)
    {
        this.fechaLanzamiento = fechaLanzamiento;
    }

    public void setImagen(String imagen)
    {
        this.imagen = imagen;
    }

    public void setDisquera(Disquera disquera)
    {
        this.disquera = disquera;
    }

    public void setArtista(Artista artista)
    {
        this.artista = artista;
    }

    public void setGeneroMusical(Genero_Musical generoMusical)
    {
        this.generoMusical = generoMusical;
    }

    @Override
    public String toString()
    {
        return "Disco{" +
                "id =" + id +
                ", tituloDisco='" + tituloDisco + '\'' +
                ", precio=" + precio +
                ", existencias=" + existencias +
                ", descuento=" + descuento +
                ", fechaLanzamiento='" + fechaLanzamiento + '\'' +
                ", imagen='" + imagen + '\'' +
                ", disquera=" + disquera.getDisquera() +
                ", artista=" + artista.getArtista() +
                ", generoMusical=" + generoMusical.getGenero() +
                '}';
    }
}

