package org.floresmateo.vista.consola.disco;

import org.floresmateo.jdbc.GenericJdbc;
import org.floresmateo.jdbc.impl.ArtistaJdbcImpl;
import org.floresmateo.jdbc.impl.DiscoJdbcImpl;
import org.floresmateo.jdbc.impl.DisqueraJdbcImpl;
import org.floresmateo.jdbc.impl.Genero_MusicalJdbcImpl;
import org.floresmateo.model.*;
import org.floresmateo.util.ReadUtil;
import org.floresmateo.vista.Menu;
import org.floresmateo.vista.consola.GestorCatalogos;
import java.io.File;

public class DiscoCatalogo extends GestorCatalogos<Disco>
{
    private static DiscoCatalogo discoCatalogo;
    private static final GenericJdbc<Disco> discoJdbc = DiscoJdbcImpl.getInstance();

    private DiscoCatalogo()
    {
        super(DiscoJdbcImpl.getInstance());
    }

    public static DiscoCatalogo getInstance()
    {
        if(discoCatalogo==null)
        {
            discoCatalogo = new DiscoCatalogo();
        }
        return discoCatalogo;
    }

    @Override
    public Disco newT() {
        return new Disco();
    }

    @Override
    public boolean processNewT(Disco disco) {
        System.out.print("> Ingrese el título del disco: ");
        disco.setTituloDisco( ReadUtil.read() );
        System.out.print("> Ingrese el precio de venta: ");
        disco.setPrecio( ReadUtil.readDouble() );
        System.out.print("> Ingrese el número de copias en inventario: ");
        disco.setExistencias( ReadUtil.readInt() );
        System.out.print("> Ingrese el descuento actual (si tiene): ");
        disco.setDescuento( ReadUtil.readDouble() );
        System.out.print("> Ingrese la fecha de lanzamiento, en formato 'YYYY-MM-DD': ");
        disco.setFechaLanzamiento( ReadUtil.read() );
        System.out.print("> Ingrese la imagen: ");
        disco.setImagen( ReadUtil.read() );

        System.out.print("> Ingrese el ID de la disquera de su distribución: ");
        Disquera disquera = DisqueraJdbcImpl.getInstance().findById( ReadUtil.readInt() );
        if(disquera==null) { return false; }
        else { disco.setDisquera( disquera ); }

        System.out.print("> Ingrese el ID del artista al que pertenece: ");
        Artista artista = ArtistaJdbcImpl.getInstance().findById( ReadUtil.readInt() );
        if(artista==null) { return false; }
        else { disco.setArtista(artista); }

        System.out.print("> Ingrese el ID del género musical al que pertenece: ");
        Genero_Musical generoMusical = Genero_MusicalJdbcImpl.getInstance().findById( ReadUtil.readInt() );
        if(generoMusical==null) { return false; }
        else { disco.setGeneroMusical( generoMusical ); }

        discoJdbc.save(disco);
        return true;
    }

    @Override
    public void edit(Disco disco) {
        System.out.print("> Ingrese el ID del disco a editar: ");
        disco.setId( ReadUtil.readInt() );
        System.out.print("> Ingrese el nuevo título del disco: ");
        disco.setTituloDisco( ReadUtil.read() );
        System.out.print("> Ingrese el nuevo precio de venta: ");
        disco.setPrecio( ReadUtil.readDouble() );
        System.out.print("> Ingrese el nuevo número de copias en inventario: ");
        disco.setExistencias( ReadUtil.readInt() );
        System.out.print("> Ingrese el nuevo descuento actual (si tiene): ");
        disco.setDescuento( ReadUtil.readDouble() );

        discoJdbc.update(disco);
    }
}
