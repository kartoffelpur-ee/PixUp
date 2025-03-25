package org.gerdoc.inicio;

import org.gerdoc.model.TipoEjecutable;
import org.gerdoc.util.ReadUtil;
import org.gerdoc.vista.Ejecutable;
import org.gerdoc.vista.Menu;

public class Inicio {

    public static void main(String[] args){
        boolean flag = true;
        int opcion = 0;
        TipoEjecutable tipoEjecutable = null;
        Ejecutable ejecutable = null;

        while( flag )
        {
            Menu.menuEjecutable( );
            Menu.seleccionaOpcion( );
            opcion = ReadUtil.readInt( );
            tipoEjecutable = TipoEjecutable.getTipoEjecutableById( opcion );

            if( TipoEjecutable.SALIR.equals( tipoEjecutable ) )
            {
                flag = false;
            }
            if( TipoEjecutable.OPCION_ERRONEA.equals( tipoEjecutable ) )
            {
                Menu.opcionInvalida( );
            }
            if( tipoEjecutable.getEjecutable( ) != null )
            {
                ejecutable = tipoEjecutable.getEjecutable( );
                ejecutable.run();
            }
        }
    }
}
