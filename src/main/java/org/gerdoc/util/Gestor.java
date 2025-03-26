package org.gerdoc.util;

import org.gerdoc.vista.Menu;

public interface Gestor {

    default void menu(){
        int opcion;

        Menu.opcionesCatalogo();
        Menu.seleccionaOpcion();
        opcion = ReadUtil.readInt();

        switch ( opcion ) {
            case 1 -> alta();
            case 2 -> baja();
            case 3 -> consulta();
            case 4 -> actualizar();
            default -> Menu.opcionInvalida();
        }
    };

    void alta();
    void baja();
    void consulta();
    void actualizar();

}
