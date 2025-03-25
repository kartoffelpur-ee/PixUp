package org.gerdoc.model;

import org.gerdoc.vista.Consola;
import org.gerdoc.vista.Ejecutable;

public enum TipoEjecutable {
    CONSOLA(1, Consola.getInstance()),
    OPCION_ERRONEA(2, null),
    SALIR(3, null);

    private Integer id;
    private Ejecutable ejecutable;

    TipoEjecutable(Integer id, Ejecutable ejecutable) {
        this.id = id;
        this.ejecutable = ejecutable;
    }

    public Integer getId() {
        return id;
    }

    public static TipoEjecutable getTipoEjecutableById(int opcion) {
        return switch (opcion)
        {
            case 1 -> CONSOLA;
            case 2 -> SALIR;
            default -> OPCION_ERRONEA;
        };
    }

    public Ejecutable getEjecutable() {
        return ejecutable;
    }
}
