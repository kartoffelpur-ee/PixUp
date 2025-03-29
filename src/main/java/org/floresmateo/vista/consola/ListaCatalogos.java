package org.floresmateo.vista.consola;

import org.floresmateo.vista.Ejecutable;
import org.floresmateo.vista.LeerAcciones;
import org.floresmateo.vista.Menu;

public class ListaCatalogos extends LeerAcciones
{
    public static ListaCatalogos listaCatalogos;
    private ListaCatalogos()
    {
    }
    public static ListaCatalogos getInstance( )
    {
        if(listaCatalogos==null)
        {
            listaCatalogos = new ListaCatalogos();
        }
        return listaCatalogos;
    }

    @Override
    public void despliegaMenu()
    {
        System.out.println("\n\t::: Catálogos :::");
        System.out.println( "1.- Estado");
        System.out.println( "2.- Municipio");
        System.out.println( "3.- Colonia");
        System.out.println( "4.- Salir");
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
        return 4;
    }

    @Override
    public void procesaOpcion()
    {
        Ejecutable ejecutable = null;
        switch (opcion)
        {
            case 1:
                ejecutable = EstadoCatalogo.getInstance();
                break;
            case 2:
                ejecutable = MunicipioCatalogo.getInstance();
                break;
            case 3:
                ejecutable = ColoniaCatalogo.getInstance();
                break;
            case 4:
                flag=false;
            default:
                Menu.opcionInvalida();
        }
        ejecutable.setFlag( true );
        ejecutable.run( );

    }
}
