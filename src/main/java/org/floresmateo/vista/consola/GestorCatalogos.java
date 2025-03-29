package org.floresmateo.vista.consola;

import org.floresmateo.model.Catalogo;
import org.floresmateo.util.ReadUtil;
import org.floresmateo.vista.LeerAcciones;
import org.floresmateo.vista.Menu;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public abstract class GestorCatalogos<T extends Catalogo> extends LeerAcciones
{
    protected List<T> list;
    protected T t;
    protected boolean flag2;
    protected File file;

    public GestorCatalogos()
    {
        list = new ArrayList<>();
    }

    public boolean isListaEmpty()
    {
        return list.isEmpty();
    }

    public abstract T newT();
    public abstract boolean processNewT(T t);
    public abstract void processEditT(T t);

    public void add( )
    {
        t = newT( );
        if(processNewT( t ))
        {
            t.setId( list.size()+1 );
            list.add( t );
            System.out.println("> Elemento añadido con éxito.");
        }
    }

    public void remove( )
    {
        getTById();
        if(t!=null)
        {
            list.remove( t );
            flag2 = false;
            System.out.println("> Elemento eliminado con éxito.");
        }
    }

    public void edit( )
    {
        getTById();
        if(t!=null)
        {
            processEditT( t );
            flag2 = false;
            System.out.println("> Elemento modificado con éxito.");
        }
    }

    public void print( )
    {
        if(isListaEmpty())
        {
            System.out.println("> No hay elementos en el catálogo.");
        }
        list.forEach(System.out::println);
    }

    public abstract File getFile( );

    private void saveOnFile()
    {
        ObjectOutputStream oos = null;
        FileOutputStream fos = null;

        try
        {
            if(isListaEmpty())
            {
                System.out.println("> No hay elementos para guardar.");
                return;
            }
            file = getFile( );
            fos = new FileOutputStream( file );
            oos = new ObjectOutputStream( fos );

            oos.writeObject( list );

            oos.close();
            fos.close();

            System.out.println("> Datos guardados con éxito.");
        }
        catch (IOException e) {
            System.err.println("> Error al guardar: "+e.getMessage());
        }
    }

    private void readFromFile()
    {
        ObjectInputStream ois = null;
        FileInputStream fis = null;

        try
        {
            file = getFile( );

            fis = new FileInputStream( file );
            ois = new ObjectInputStream( fis );

            list = (List<T>) ois.readObject();

            ois.close();
            fis.close();

            System.out.println("> Datos cargados con éxito.");
        }
        catch (IOException e) {
            System.err.println("> Error al cargar: "+e.getMessage());
        }
        catch (ClassNotFoundException e)
        {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void despliegaMenu()
    {
        System.out.println("\n\t:: Gestión de catálogos ::");
        System.out.println("Seleccione una opción:");
        System.out.println("1.- Agregar");
        System.out.println("2.- Eliminar");
        System.out.println("3.- Editar");
        System.out.println("4.- Imprimir elementos en lista");
        System.out.println("5.- Guardar en archivo");
        System.out.println("6.- Leer en archivo");
        System.out.println("7.- Salir");
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
        return 7;
    }

    @Override
    public void procesaOpcion()
    {
        switch (opcion)
        {
            case 1:
                add( );
                break;
            case 2:
                remove( );
                break;
            case 3:
                edit( );
                break;
            case 4:
                print( );
                break;
            case 5:
                saveOnFile();
                break;
            case 6:
                readFromFile();
                break;
            default:
                Menu.opcionInvalida();
        }
    }

    public T getTById() {
        if (isListaEmpty()) {
            System.out.println("> No hay elementos registrados.");
            return null;
        }
        while (true) {
            System.out.print("> Ingrese el ID del elemento: ");
            int id = ReadUtil.readInt();
            T t = list.stream()
                    .filter(e -> e.getId().equals(id))
                    .findFirst()
                    .orElse(null);
            if (t != null) {
                return t;
            }
            System.out.println("> ID incorrecto, inténtelo nuevamente.");
        }
    }


}
