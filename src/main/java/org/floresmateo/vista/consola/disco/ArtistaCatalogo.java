package org.floresmateo.vista.consola.disco;
import org.floresmateo.sql.GenericSql;
import org.floresmateo.sql.jdbcimpl.ArtistaSqlImpl;
import org.floresmateo.model.Artista;
import org.floresmateo.util.ReadUtil;
import org.floresmateo.vista.consola.GestorCatalogos;

import java.util.List;

public class ArtistaCatalogo extends GestorCatalogos<Artista>
{
    private static ArtistaCatalogo artistaCatalogo;
    private static final GenericSql<Artista> artistaJdbc = ArtistaSqlImpl.getInstance();

    private ArtistaCatalogo()
    {
        super(ArtistaSqlImpl.getInstance());
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
        artistaJdbc.save(artista);
        return true;
    }

    @Override
    public void edit(Artista artista)
    {
        List<Artista> list = artistaJdbc.findAll();
        list.stream().forEach(System.out::println);
        System.out.print("> Ingrese el ID del estado a editar: ");
        artista.setId( ReadUtil.readInt() );
        System.out.print("> Ingrese el nuevo nombre del estado: ");
        artista.setArtista( ReadUtil.read() );
        artistaJdbc.update(artista);
    }

}
