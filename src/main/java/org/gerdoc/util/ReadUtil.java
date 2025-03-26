package org.gerdoc.util;

import org.gerdoc.vista.Menu;
import java.util.Scanner;

public class ReadUtil {
    private static Scanner scanner;
    private static ReadUtil readUtil;

    private ReadUtil() {
        scanner = new Scanner(System.in);
    }

    public Scanner getScanner() {
        return scanner;
    }

    public static ReadUtil getInstance() {
        if (readUtil == null) {
            readUtil = new ReadUtil();
        }
        return readUtil;
    }

    public static String read( ) {
        return getInstance().getScanner().nextLine();
    }

    public static Integer readInt() {
        String valor;
        Integer aux = null;

        while (true) {
            valor = read();
            if (valor != null && !valor.isEmpty()) {
                try {
                    aux = Integer.valueOf(valor);
                    if (aux >= 0) {
                        return aux;
                    } else {
                        System.out.println("\t¡ERROR! Ingresa un número entero positivo.");
                    }
                } catch (Exception e) {
                    Menu.errorDato();
                }
            }
        }
    }

    public static Double readDouble() {
        String valor;
        Double aux = null;

        while (true) {
            valor = read();
            if (valor != null && !valor.isEmpty()) {
                try {
                    aux = Double.valueOf(valor);
                    if (aux > 0) { // Solo permite valores positivos
                        return aux;
                    } else {
                        System.out.println("> Error: Ingresa un número positivo.");
                    }
                } catch (Exception e) {
                    Menu.errorDato();
                }
            }
        }
    }

    public static Integer string2Integer( String valor )
    {
        try
        {
            return Integer.valueOf(valor);
        }
        catch (Exception e)
        {
        }
        return null;
    }

    public static Double string2Double( String valor )
    {
        try
        {
            return Double.valueOf(valor);
        }
        catch (Exception e)
        {
        }
        return null;
    }
}
