package org.gerdoc.vista;

public class Menu {

    public static void menuEjecutable(){
        System.out.println("\n\t:::¡Bienvenido a PixUp! :::");
        System.out.println("\t> Selecciona tu método de acceso:");
        System.out.println("1. Consola");
        System.out.println("2. Salir");
    }

    public static void principal(){
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

    public static void catalogo() {
        System.out.println("1. Estados");
        System.out.println("2. Municipios");
        System.out.println("3. Colonias");
        System.out.println("4. Volver");
    }

    public static void registro() {
        System.out.println("1. Altas");
        System.out.println("2. Bajas");
        System.out.println("3. Ver");
        System.out.println("4. Actualizar");
        System.out.println("5. Volver");
    }
}
