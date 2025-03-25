package org.gerdoc.vista;

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

        }
    }
}
