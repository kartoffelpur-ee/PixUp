package org.floresmateo.vista.consola.disco;

import org.floresmateo.jdbc.GenericJdbc;
import org.floresmateo.jdbc.impl.DisqueraJdbcImpl;
import org.floresmateo.model.Disquera;
import org.floresmateo.util.ReadUtil;
import org.floresmateo.vista.consola.GestorCatalogos;
import java.io.File;

public class DisqueraCatalogo extends GestorCatalogos<Disquera>
{
    private static DisqueraCatalogo disqueraCatalogo;

    private DisqueraCatalogo()
    {
        super();
    }

    public static DisqueraCatalogo getInstance()
    {
        if(disqueraCatalogo==null)
        {
            disqueraCatalogo = new DisqueraCatalogo();
        }
        return disqueraCatalogo;
    }

    @Override
    public Disquera newT() {
        return new Disquera();
    }

    @Override
    public boolean processNewT(Disquera disquera) {
        System.out.print("> Ingrese el nombre de la disquera: ");
        disquera.setDisquera( ReadUtil.read() );
        return true;
    }

    @Override
    public void processEditT(Disquera disquera) {
        System.out.println("\n> ID de la disquera siendo editada: "+disquera.getId());
        System.out.println("> Nombre de la disquera siendo editada: "+disquera.getDisquera());
        System.out.print("> Ingrese el nuevo nombre de la disquera: ");
        disquera.setDisquera( ReadUtil.read() );
    }

    @Override
    public File getFile() {
        return new File( "./src/main/fileStorage/Disqueras.list" );
    }

    @Override
    public void print()
    {
        GenericJdbc<Disquera> disqueraJdbc = new DisqueraJdbcImpl();
        disqueraJdbc.findAll().stream().forEach(System.out::println);
    }

    public Disquera getDisqueraById() {
        if (isListaEmpty()) {
            System.out.println("> No hay disqueras registradas.");
            return null;
        }
        while (true) {
            System.out.print("> Ingrese el ID de la disquera: ");
            int id = ReadUtil.readInt();
            Disquera disquera = list.stream()
                    .filter(e -> e.getId().equals(id))
                    .findFirst()
                    .orElse(null);
            if (disquera != null) {
                return disquera;
            }
            System.out.println("> ID incorrecto, inténtelo nuevamente.");
        }
    }
}
