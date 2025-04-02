package org.floresmateo.vista.consola;

import org.floresmateo.vista.Ejecutable;
import org.floresmateo.vista.LeerAcciones;
import org.floresmateo.vista.Menu;
import org.floresmateo.vista.consola.*;

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
        System.out.println("\n\t::: Lista de Catálogos Disponibles :::");
        System.out.println( "1.- Usuarios");
        System.out.println( "2.- Discos");
        System.out.println( "3.- Salir");
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
        return 3;
    }

    @Override
    public void procesaOpcion()
    {
        Ejecutable ejecutable = null;
        switch(opcion)
        {
            case 1:
                ejecutable = ListaUsuario.getInstance();
                break;
            case 2:
                ejecutable = ListaDisco.getInstance();
                break;
            case 3:
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
