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

    public static void edicionDisco( )
    {
        System.out.println("\n1. Título");
        System.out.println("2. Precio");
        System.out.println("3. Número de copias en existencia");
        System.out.println("4. Descuento");
        System.out.println("5. Fecha de lanzamiento");
        System.out.println("6. Imagen");
        System.out.println("7. Disquera");
        System.out.println("8. Artista");
        System.out.println("9. Género musical");
    }
}

