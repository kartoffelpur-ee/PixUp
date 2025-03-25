package org.gerdoc.vista;

import org.gerdoc.util.ReadUtil;

public class Consola implements Ejecutable{
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
        int opcion;

        while( flag ){
            Menu.principal( );
            Menu.seleccionaOpcion( );
            opcion = ReadUtil.readInt( );

            switch (opcion) {
                case 1:
                    Menu.catalogo();
                    Menu.seleccionaOpcion();
                    break;
                case 2:
                    System.out.println("!! EN CONSTRUCCIÓN !!");
                    break;
                case 3:
                    flag = false;
                    break;
                default:
                    Menu.opcionInvalida();
                    break;
            }
        }
    }
}
