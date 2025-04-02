package org.floresmateo.model;

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
    private GeneroMusical generoMusical;

    public Disco()
    {
    }

    public Disco(String tituloDisco, double precio, int existencias, double descuento, String fechaLanzamiento, String imagen, Disquera disquera, Artista artista, GeneroMusical generoMusical)
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

    public double getPrecio()
    {
        return precio;
    }

    public void setPrecio(double precio)
    {
        this.precio = precio;
    }

    public int getExistencias()
    {
        return existencias;
    }

    public void setExistencias(int existencias)
    {
        this.existencias = existencias;
    }

    public double getDescuento()
    {
        return descuento;
    }

    public void setDescuento(double descuento)
    {
        this.descuento = descuento;
    }

    public String getFechaLanzamiento()
    {
        return fechaLanzamiento;
    }

    public void setFechaLanzamiento(String fechaLanzamiento)
    {
        this.fechaLanzamiento = fechaLanzamiento;
    }

    public String getImagen()
    {
        return imagen;
    }

    public void setImagen(String imagen)
    {
        this.imagen = imagen;
    }

    public Disquera getDisquera()
    {
        return disquera;
    }

    public void setDisquera(Disquera disquera)
    {
        this.disquera = disquera;
    }

    public Artista getArtista()
    {
        return artista;
    }

    public void setArtista(Artista artista)
    {
        this.artista = artista;
    }

    public GeneroMusical getGeneroMusical()
    {
        return generoMusical;
    }

    public void setGeneroMusical(GeneroMusical generoMusical)
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