package org.gerdoc.vista;

public class Menu {

    public static void menuEjecutable()
    {
        System.out.println("\n\t:::¡Bienvenido a PixUp! :::");
        System.out.println("\t> Selecciona tu método de acceso:");
        System.out.println("1. Consola");
        System.out.println("2. Salir");
    }

    public static void menuPrincipal()
    {
        System.out.println("\n\t::: Menú principal :::");
        System.out.println("\t> Selecciona una opción:");
        System.out.println("1. Catálogo");
        System.out.println("2. Pendiente");
        System.out.println("3. Salir");
    }

    public static void opcionInvalida( )
    {
        System.out.println("\t¡ERROR!¡La opción no es correcta!");
    }

    public static void seleccionaOpcion( )
    {
        System.out.print("> Dame una opción: ");
    }

    public static void errorDato( )
    {
        System.out.println("\t¡ERROR!¡Ese no es un dato válido!");
    }

    public static void menuCatalogo( )
    {
        System.out.println("\n\t:: Catálogo ::");
        System.out.println("1. Estados");
        System.out.println("2. Municipios");
        System.out.println("3. Colonias");
        System.out.println("4. Volver");
    }

    public static void opcionesCatalogo( )
    {
        System.out.println("\n\t:: ¿Qué operación deseas realizar? ::");
        System.out.println("1. Dar de alta");
        System.out.println("2. Dar de baja");
        System.out.println("3. Consultar las existencias");
        System.out.println("4. Actualizar");
        System.out.println("5. Volver");
    }

    public static void leeNombre( )
    {
        System.out.print("> Ingresa el nombre: ");
    }

    public static void leeNombre2( )
    {
        System.out.print("> Ingresa el nuevo nombre: ");
    }

    public static void leeId( )
    {
        System.out.print("> Ingresa el ID: ");
    }

    public static void leeIdEstado( )
    {
        System.out.print("> Ingresa el ID del estado al que pertenece: ");
    }

    public static void leeIdMunicipio( )
    {
        System.out.print("> Ingresa el ID del municipio al que pertenece: ");
    }

    public static void leeCp( )
    {
        System.out.print("> Ingresa el código postal de la colonia: ");
    }

    public static void idInvalido( )
    {
        System.out.println("> El ID ingresado no existe.");
    }
}

