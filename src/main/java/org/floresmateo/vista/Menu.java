package org.floresmateo.vista;

public class Menu {

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

    public static void opcionesCatalogo( )
    {
        System.out.println("\n\t:: ¿Qué operación deseas realizar? ::");
        System.out.println("1. Dar de alta");
        System.out.println("2. Dar de baja");
        System.out.println("3. Consultar las existencias");
        System.out.println("4. Actualizar");
        System.out.println("5. Volver");
    }
}

