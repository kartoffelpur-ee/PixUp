package org.floresmateo.vista.consola.disco;

import org.floresmateo.sql.GenericSql;
import org.floresmateo.model.Artista;
import org.floresmateo.sql.hibernateimpl.ArtistaHiberImpl;
import org.floresmateo.util.ReadUtil;
import org.floresmateo.vista.consola.GestorCatalogos;

import java.util.List;

public class ArtistaCatalogo extends GestorCatalogos<Artista>
{
    private static ArtistaCatalogo artistaCatalogo;
    private static final GenericSql<Artista> artistaSql = ArtistaHiberImpl.getInstance();

    private ArtistaCatalogo()
    {
        super(ArtistaHiberImpl.getInstance());
    }

    public static ArtistaCatalogo getInstance()
    {
        if(artistaCatalogo==null)
        {
            artistaCatalogo = new ArtistaCatalogo();
        }
        return artistaCatalogo;
    }

    @Override
    public Artista newT()
    {
        return new Artista();
    }

    @Override
    public boolean processNewT(Artista artista)
    {
        System.out.print("> Ingrese el nombre del artista: ");
        artista.setArtista( ReadUtil.read() );
        artistaSql.save(artista);
        return true;
    }

    @Override
    public boolean processEditT(Artista artista)
    {
        System.out.print("> Ingrese el nuevo nombre del artista: ");
        artista.setArtista( ReadUtil.read() );

        artistaSql.update(artista);
        return true;
    }

}
