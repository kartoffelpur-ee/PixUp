package org.floresmateo.vista.consola;
import org.floresmateo.jdbc.Conexion;
import org.floresmateo.jdbc.GenericJdbc;
import org.floresmateo.model.Catalogo;
import org.floresmateo.util.ReadUtil;
import org.floresmateo.vista.LeerAcciones;
import org.floresmateo.vista.Menu;

import java.io.*;
import java.sql.*;
import java.util.List;

public abstract class GestorCatalogos<T extends Catalogo> extends LeerAcciones
{
    protected List<T> list;
    protected T t;
    protected boolean flag2;
    protected GenericJdbc<T> genericJdbc;

    public GestorCatalogos(GenericJdbc<T> genericJdbc)
    {
        this.genericJdbc = genericJdbc;
        Conexion conexion = new Conexion() {
        };
        Connection connection = conexion.getConnection();
        //list = new ArrayList<>();
    }

    /*
    public boolean isListaEmpty()
    {
        return list.isEmpty();
    }
     */

    public abstract T newT();
    public abstract boolean processNewT(T t);
    public abstract void edit(T t);

    public void print()
    {
        List<T> list = genericJdbc.findAll();
        if(list.isEmpty())
        {
            System.out.println("> No hay elementos registrados.");
        }
        list.stream().forEach(System.out::println);
    }

    public void add( )
    {
        t = newT( );
        if(processNewT( t ))
        {
            System.out.println("> Elemento añadido con éxito.");
        }
    }

    public void remove( )
    {

        List<T> list = genericJdbc.findAll();
        if( list.isEmpty( ) )
        {
            System.out.println( ">  No hay elementos para eliminar." );
            return;
        }
        flag2 = true;
        while ( flag2 )
        {
            System.out.print( "> Ingrese el ID del elemento a eliminar: " );
            t = list.stream().filter( e -> e.getId().equals( ReadUtil.readInt( ) ) ).findFirst().orElse( null );
            if( t==null )
            {
                System.out.println( "> ID incorrecto, inténtelo nuevamente." );
            }
            else
            {
                if(genericJdbc.delete(t))
                {
                    System.out.println( "> Elemento eliminado con éxito." );
                }
                flag2 = false;

            }
        }
    }

    public void findById()
    {
        System.out.print("> Ingresa un ID para buscar: ");
        t = genericJdbc.findById( ReadUtil.readInt() );

        if(t!=null)
        {
            System.out.println(t);
        }
        else
        {
            System.out.println("> No existe un elemento con dicho ID.");
        }
    }

        /*
        if( isListaEmpty( ) )
        {
            System.out.println( "> No hay elementos para editar." );
            return;
        }
        flag2 = true;
        while ( flag2 )
        {
            print( );
            System.out.print( "> Ingrese el ID del elemento a editar: " );
            t = list.stream().filter( e -> e.getId().equals( ReadUtil.readInt( ) ) ).findFirst().orElse( null );
            if( t == null )
            {
                System.out.println( "> ID incorrecto, inténtelo nuevamente" );
            }
            else
            {
                edit( t );
                flag2 = false;
                System.out.println( "> Elemento modificado con éxito." );
            }
        }

         */

    /*
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

     */

    @Override
    public void despliegaMenu()
    {
        System.out.println("\n\t:: Gestión de catálogos ::");
        System.out.println("Seleccione una opción:");
        System.out.println("1.- Agregar");
        System.out.println("2.- Eliminar");
        System.out.println("3.- Editar");
        System.out.println("4.- Imprimir elementos en lista");
        System.out.println("5.- Obtener por su ID");
        System.out.println("6.- Salir");
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
        switch (opcion)
        {
            case 1:
                add( );
                break;
            case 2:
                remove( );
                break;
            case 3:
                edit(t);
                break;
            case 4:
                print( );
                break;
            case 5:
                findById( );
                break;
                /*
            case 5:
                saveOnFile();
                break;
            case 6:
                readFromFile();
                break;
                 */
            default:
                Menu.opcionInvalida();
        }
    }

}
