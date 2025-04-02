package org.floresmateo.vista.consola.disco;

import org.floresmateo.model.Artista;
import org.floresmateo.model.Disco;
import org.floresmateo.model.Disquera;
import org.floresmateo.model.GeneroMusical;
import org.floresmateo.util.ReadUtil;
import org.floresmateo.vista.Menu;
import org.floresmateo.vista.consola.GestorCatalogos;

import java.io.File;

public class DiscoCatalogo extends GestorCatalogos<Disco>
{
    private static DiscoCatalogo discoCatalogo;

    private DiscoCatalogo()
    {
        super();
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
        System.out.print("> Ingrese la fecha de lanzamiento: ");
        disco.setFechaLanzamiento( ReadUtil.read() );
        System.out.print("> Ingrese la imagen: ");
        disco.setImagen( ReadUtil.read() );

        Disquera disquera = DisqueraCatalogo.getInstance().getDisqueraById();
        if(disquera==null)
        {
            return false;
        }
        else
        {
            disco.setDisquera( disquera );
        }

        Artista artista = ArtistaCatalogo.getInstance().getArtistaById();
        if(artista==null)
        {
            return false;
        }
        else
        {
            disco.setArtista(artista);
        }

        GeneroMusical generoMusical = GeneroMusicalCatalogo.getInstance().getGeneroById();
        if(generoMusical==null)
        {
            return false;
        }
        else
        {
            disco.setGeneroMusical( generoMusical );
        }

        return true;
    }

    @Override
    public void processEditT(Disco disco) {
        Integer opcion;

        System.out.println("\n> ID del disco siendo editado: "+disco.getId());
        System.out.println("> Disco siendo editado: "+disco.getTituloDisco());
        System.out.println("> ¿Qué deseas editar de este disco?");
        Menu.edicionDisco();
        Menu.seleccionaOpcion();
        opcion = ReadUtil.readInt();

        switch(opcion)
        {
            case 1:
                System.out.print("> Ingrese el nuevo título del disco: ");
                disco.setTituloDisco( ReadUtil.read() );
                break;
            case 2:
                System.out.print("> Ingrese el nuevo precio de venta: ");
                disco.setPrecio( ReadUtil.readDouble() );
                break;
            case 3:
                System.out.print("> Ingrese el nuevo número de copias en inventario: ");
                disco.setExistencias( ReadUtil.readInt() );
                break;
            case 4:
                System.out.print("> Ingrese el nuevo descuento actual (si tiene): ");
                disco.setDescuento( ReadUtil.readDouble() );
                break;
            case 5:
                System.out.print("> Ingrese la nueva fecha de lanzamiento: ");
                disco.setFechaLanzamiento( ReadUtil.read() );
                break;
            case 6:
                System.out.print("> Ingrese la nueva imagen: ");
                disco.setImagen( ReadUtil.read() );
                break;
            case 7:
                Disquera disquera = DisqueraCatalogo.getInstance().getDisqueraById();
                if(disquera==null)
                {
                    System.out.println("> Disquera no encontrada. No se pudo actualizar; compruébelo e inténtelo de nuevo.");
                }
                else
                {
                    disco.setDisquera( disquera );
                }
                break;
            case 8:
                Artista artista = ArtistaCatalogo.getInstance().getArtistaById();
                if(artista==null)
                {
                    System.out.println("> Artista no encontrado. No se pudo actualizar; compruébelo e inténtelo de nuevo.");
                }
                else
                {
                    disco.setArtista(artista);
                }
                break;
            case 9:
                GeneroMusical generoMusical = GeneroMusicalCatalogo.getInstance().getGeneroById();
                if(generoMusical==null)
                {
                    System.out.println("> Género musical no encontrado. No se pudo actualizar; compruébelo e inténtelo de nuevo.");
                }
                else
                {
                    disco.setGeneroMusical( generoMusical );
                }
                break;
            default:
                Menu.opcionInvalida();
        }
    }

    @Override
    public File getFile() {
        return new File( "./src/main/fileStorage/Discos.list" );
    }

    public Disco getDiscoById() {
        if (isListaEmpty()) {
            System.out.println("> No hay discos registradas.");
            return null;
        }
        while (true) {
            System.out.print("> Ingrese el ID del disco: ");
            int id = ReadUtil.readInt();
            Disco disco = list.stream()
                    .filter(e -> e.getId().equals(id))
                    .findFirst()
                    .orElse(null);
            if (disco != null) {
                return disco;
            }
            System.out.println("> ID incorrecto, inténtelo nuevamente.");
        }
    }
}
