package org.floresmateo.vista.consola.usuario;

import org.floresmateo.jdbc.GenericJdbc;
import org.floresmateo.jdbc.impl.EstadoJdbcImpl;
import org.floresmateo.model.Estado;
import org.floresmateo.util.ReadUtil;
import org.floresmateo.vista.consola.GestorCatalogos;

import java.io.File;
import java.util.List;

public class EstadoCatalogo extends GestorCatalogos<Estado>
{

    private static EstadoCatalogo estadoCatalogo;
    private static final GenericJdbc<Estado> estadoJdbc = EstadoJdbcImpl.getInstance();

    public static EstadoCatalogo getInstance( )
    {
        if(estadoCatalogo==null)
        {
            estadoCatalogo = new EstadoCatalogo();
        }
        return estadoCatalogo;
    }

    private EstadoCatalogo( ){ super(); }

    @Override
    public Estado newT()
    {
        return new Estado();
    }

    @Override
    public boolean processNewT(Estado estado)
    {
        estado = new Estado();
        System.out.print("> Teclee el nombre del estado: ");
        estado.setNombre( ReadUtil.read() );
        estadoJdbc.save(estado);
        return true;
    }

    @Override
    public void processEditT(Estado estado)
    {
        GenericJdbc<Estado> estadoJdbc = EstadoJdbcImpl.getInstance();
        estado = new Estado();
        System.out.println("\n> ID del estado siendo editado: "+estado.getId());
        System.out.println("> Estado siendo editado: "+estado.getNombre());
        System.out.print("> Ingrese el nuevo nombre del estado: ");
        estado.setNombre( ReadUtil.read() );
        estado.setId(1);
        estadoJdbc.update(estado);
    }

    @Override
    public void print()
    {
        GenericJdbc<Estado> estadoJdbc = EstadoJdbcImpl.getInstance();
        List<Estado> list = estadoJdbc.findAll();

        list.stream().forEach(System.out::println);
    }

    @Override
    public File getFile()
    {
        return new File("./src/main/fileStorage/Estados.object" );
    }

    public Estado getEstadoById() {
        if (isListaEmpty()) {
            System.out.println("> No hay estados registrados.");
            return null;
        }
        while (true) {
            System.out.print("> Ingrese el ID del estado: ");
            int id = ReadUtil.readInt();
            Estado estado = list.stream()
                    .filter(e -> e.getId().equals(id))
                    .findFirst()
                    .orElse(null);
            if (estado != null) {
                return estado;
            }
            System.out.println("> ID incorrecto, inténtelo nuevamente.");
        }
    }

}


