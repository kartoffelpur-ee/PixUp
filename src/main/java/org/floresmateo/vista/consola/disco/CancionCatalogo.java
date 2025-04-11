package org.floresmateo.vista.consola.disco;
import org.floresmateo.jdbc.GenericJdbc;
import org.floresmateo.jdbc.impl.CancionJdbcImpl;
import org.floresmateo.jdbc.impl.DiscoJdbcImpl;
import org.floresmateo.model.*;
import org.floresmateo.util.ReadUtil;
import org.floresmateo.vista.consola.GestorCatalogos;
import java.io.File;
import java.util.List;

public class CancionCatalogo extends GestorCatalogos<Cancion>
{
    private static CancionCatalogo cancionCatalogo;
    private static final GenericJdbc<Cancion> cancionJdbc = CancionJdbcImpl.getInstance();

    private CancionCatalogo()
    {
        super(CancionJdbcImpl.getInstance());
    }

    public static CancionCatalogo getInstance()
    {
        if(cancionCatalogo==null)
        {
            cancionCatalogo = new CancionCatalogo();
        }
        return cancionCatalogo;
    }

    @Override
    public Cancion newT() {
        return new Cancion();
    }

    @Override
    public boolean processNewT(Cancion cancion)
    {
        System.out.print("> Ingrese el título de la canción: ");
        cancion.setTituloCancion( ReadUtil.read() );
        System.out.print("> Ingrese la duración de la canción en minutos: ");
        cancion.setDuracion( ReadUtil.readDouble() );

        System.out.print("> Ingrese el ID del disco al que pertenece: ");
        Disco disco = DiscoJdbcImpl.getInstance().findById( ReadUtil.readInt() );
        if(disco==null){ return false; }
        else { cancion.setDisco( disco ); }

        cancionJdbc.save(cancion);
        return true;
    }

    @Override
    public void edit(Cancion cancion)
    {
        List<Cancion> list = cancionJdbc.findAll();
        list.stream().forEach(System.out::println);
        System.out.print("> Ingrese el ID de la canción a editar: ");
        cancion.setId( ReadUtil.readInt() );
        System.out.print("> Ingrese el nuevo título de la canción: ");
        cancion.setTituloCancion( ReadUtil.read() );

        cancionJdbc.update(cancion);
    }
}

