package org.gerdoc.vista;

import org.gerdoc.util.ReadUtil;
import org.gerdoc.util.manejodatos.GestorColonias;
import org.gerdoc.util.manejodatos.GestorEstados;
import org.gerdoc.util.manejodatos.GestorMunicipios;

public class Catálogo {

    public static void menuCatalogo() {
        boolean flag = true;
        int opcion;

        GestorEstados gestorEstados = new GestorEstados();
        GestorMunicipios gestorMunicipios = new GestorMunicipios(gestorEstados);
        GestorColonias gestorColonias = new GestorColonias(gestorMunicipios);

        while( flag ){
            Menu.menuCatalogo( );
            Menu.seleccionaOpcion( );
            opcion = ReadUtil.readInt( );

            switch (opcion) {
                case 1 -> gestorEstados. menu();
                case 2 -> gestorMunicipios.menu();
                case 3 -> gestorColonias.menu();
                case 4 -> flag = false;
                default -> Menu.opcionInvalida();
            }
        }
    }
}
