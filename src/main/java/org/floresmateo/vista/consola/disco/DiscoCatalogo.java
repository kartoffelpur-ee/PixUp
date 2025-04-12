package org.floresmateo.vista.consola.disco;

import org.floresmateo.sql.GenericSql;
import org.floresmateo.sql.hibernateimpl.ArtistaHiberImpl;
import org.floresmateo.sql.hibernateimpl.DiscoHiberImpl;
import org.floresmateo.model.*;
import org.floresmateo.sql.hibernateimpl.DisqueraHiberImpl;
import org.floresmateo.sql.hibernateimpl.Genero_MusicalHiberImpl;
import org.floresmateo.util.ReadUtil;
import org.floresmateo.vista.consola.GestorCatalogos;
import java.time.LocalDate;
import java.util.List;

public class DiscoCatalogo extends GestorCatalogos<Disco>
{
    private static DiscoCatalogo discoCatalogo;
    private static final GenericSql<Disco> discoSql = DiscoHiberImpl.getInstance();

    private DiscoCatalogo()
    {
        super(DiscoHiberImpl.getInstance());
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
        String fechaStr = ReadUtil.read();
        LocalDate fecha = LocalDate.parse(fechaStr);
        disco.setFechaLanzamiento( fecha );

        System.out.print("> Ingrese la imagen: ");
        disco.setImagen( ReadUtil.read() );

        DisqueraHiberImpl disqueraSql = DisqueraHiberImpl.getInstance();
        List<Disquera> disqueraList = disqueraSql.findAll();
        disqueraList.forEach(System.out::println);

        System.out.print("> Ingrese el ID de la disquera de su distribución: ");
        Disquera disquera = disqueraSql.findById( ReadUtil.readInt() );
        if(disquera==null)
        {
            System.out.println("> No encontrado.");
            return false;
        }
        else
        {
            disco.setDisquera( disquera );
        }

        ArtistaHiberImpl artistaSql = ArtistaHiberImpl.getInstance();
        List<Artista> artistaList = artistaSql.findAll();
        artistaList.forEach(System.out::println);

        System.out.print("> Ingrese el ID del artista al que pertenece: ");
        Artista artista = artistaSql.findById( ReadUtil.readInt() );
        if(artista==null)
        {
            System.out.println("> No encontrado.");
            return false;
        }
        else
        {
            disco.setArtista(artista);
        }

        Genero_MusicalHiberImpl generoMusicalSql = Genero_MusicalHiberImpl.getInstance();
        List<Genero_Musical> generoMusicalList = generoMusicalSql.findAll();
        generoMusicalList.forEach(System.out::println);

        System.out.print("> Ingrese el ID del género musical al que pertenece: ");
        Genero_Musical generoMusical = generoMusicalSql.findById( ReadUtil.readInt() );
        if(generoMusical==null)
        {
            System.out.println("> No encontrado.");
            return false;
        }
        else
        {
            disco.setGeneroMusical( generoMusical );
        }

        discoSql.save(disco);
        return true;
    }

    @Override
    public boolean processEditT(Disco disco)
    {
        System.out.print("> Ingrese el nuevo título del disco: ");
        disco.setTituloDisco( ReadUtil.read() );
        System.out.print("> Ingrese el nuevo precio de venta: ");
        disco.setPrecio( ReadUtil.readDouble() );
        System.out.print("> Ingrese el nuevo número de copias en inventario: ");
        disco.setExistencias( ReadUtil.readInt() );
        System.out.print("> Ingrese el nuevo descuento actual (si tiene): ");
        disco.setDescuento( ReadUtil.readDouble() );

        discoSql.update(disco);
        return true;
    }
}

