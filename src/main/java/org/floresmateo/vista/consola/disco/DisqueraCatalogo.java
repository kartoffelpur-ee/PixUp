package org.floresmateo.vista.consola.disco;
import org.floresmateo.sql.GenericJdbc;
import org.floresmateo.sql.jdbcimpl.DisqueraJdbcImpl;
import org.floresmateo.model.Disquera;
import org.floresmateo.util.ReadUtil;
import org.floresmateo.vista.consola.GestorCatalogos;

import java.util.List;

public class DisqueraCatalogo extends GestorCatalogos<Disquera>
{
    private static DisqueraCatalogo disqueraCatalogo;
    private static final GenericJdbc<Disquera> disqueraJdbc = DisqueraJdbcImpl.getInstance();

    private DisqueraCatalogo()
    {
        super(DisqueraJdbcImpl.getInstance());
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
    public boolean processNewT(Disquera disquera)
    {
        System.out.print("> Ingrese el nombre de la disquera: ");
        disquera.setDisquera( ReadUtil.read() );
        disqueraJdbc.save(disquera);
        return true;
    }

    @Override
    public void edit(Disquera disquera)
    {
        List<Disquera> list = disqueraJdbc.findAll();
        list.stream().forEach(System.out::println);
        System.out.print("> Ingrese el ID de la disquera a editar: ");
        disquera.setId( ReadUtil.readInt() );
        System.out.print("> Ingrese el nuevo nombre de la disquera: ");
        disquera.setDisquera( ReadUtil.read() );
        disqueraJdbc.update(disquera);
    }
}
