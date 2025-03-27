package org.gerdoc.vista;
import org.gerdoc.util.ReadUtil;

public class Consola implements Ejecutable {
    private static Consola consola;

    public Consola() {
    }

    public static Consola getInstance() {
        if(consola == null)
        {
            consola = new Consola();
        }
        return consola;
    }

    @Override
    public void run() {
        boolean flag = true;
        int opcion, subOpcion;

        while( flag ){
            Menu.menuPrincipal( );
            Menu.seleccionaOpcion( );
            opcion = ReadUtil.readInt( );

            switch (opcion) {
                case 1 -> Catálogo.menuCatalogo();
                case 2 -> System.out.println("Opción no implementada aún - En desarrollo.");
                case 3 -> flag = false;
                default -> Menu.opcionInvalida();
            }
        }
    }
}

