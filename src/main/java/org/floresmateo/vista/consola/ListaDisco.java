package org.floresmateo.vista.consola;

import org.floresmateo.vista.*;
import org.floresmateo.vista.consola.disco.*;

public class ListaDisco extends LeerAcciones
{
    private static ListaDisco listaDisco;

    private ListaDisco()
    {
    }

    public static ListaDisco getInstance()
    {
        if(listaDisco==null)
        {
            listaDisco = new ListaDisco();
        }
        return listaDisco;
    }

    @Override
    public void despliegaMenu()
    {
        System.out.println("\n\t::: Catálogo de Discos :::");
        System.out.println( "1.- Artista");
        System.out.println( "2.- Disquera");
        System.out.println( "3.- Genero músical");
        System.out.println( "4.- Disco" );
        System.out.println( "5.- Canción" );
        System.out.println( "6.- Salir");
        Menu.seleccionaOpcion();
    }

    @Override
    public int valorMinMenu()
    {
        return 1;
    }

    @Override
    public int valorMaxMenu()
    {
        return 6;
    }

    @Override
    public void procesaOpcion()
    {
        Ejecutable ejecutable = null;
        switch(opcion)
        {
            case 1:
                ejecutable = ArtistaCatalogo.getInstance();
                break;
            case 2:
                ejecutable = DisqueraCatalogo.getInstance();
                break;
            case 3:
                ejecutable = GeneroMusicalCatalogo.getInstance();
                break;
            case 4:
                ejecutable = DiscoCatalogo.getInstance();
                break;
            case 5:
                ejecutable = CancionCatalogo.getInstance();
                break;
            case 6:
                flag = false;
                break;
            default:
                Menu.opcionInvalida();
                break;
        }
        if(ejecutable!=null)
        {
            ejecutable.setFlag(true);
            ejecutable.run();
        }
    }
}
