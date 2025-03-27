package org.gerdoc.vista;

import org.gerdoc.util.ReadUtil;
import org.gerdoc.util.manejodatos.CatColonias;
import org.gerdoc.util.manejodatos.CatEstados;
import org.gerdoc.util.manejodatos.CatMunicipios;

public class Catálogo {

    public static void menuCatalogo() {
        boolean flag = true;
        int opcion;

        CatEstados catEstados = new CatEstados();
        CatMunicipios catMunicipios = new CatMunicipios(catEstados);
        CatColonias catColonias = new CatColonias(catMunicipios);

        while( flag ){
            Menu.menuCatalogo( );
            Menu.seleccionaOpcion( );
            opcion = ReadUtil.readInt( );

            switch (opcion) {
                case 1 -> catEstados. menu();
                case 2 -> catMunicipios.menu();
                case 3 -> catColonias.menu();
                case 4 -> flag = false;
                default -> Menu.opcionInvalida();
            }
        }
    }
}

