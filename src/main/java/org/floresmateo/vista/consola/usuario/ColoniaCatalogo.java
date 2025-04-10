package org.floresmateo.vista.consola.usuario;

import org.floresmateo.jdbc.GenericJdbc;
import org.floresmateo.jdbc.impl.ColoniaJdbcImpl;
import org.floresmateo.jdbc.impl.EstadoJdbcImpl;
import org.floresmateo.jdbc.impl.MunicipioJdbcImpl;
import org.floresmateo.model.Colonia;
import org.floresmateo.model.Estado;
import org.floresmateo.model.Municipio;
import org.floresmateo.util.ReadUtil;
import org.floresmateo.vista.consola.GestorCatalogos;

import java.io.File;
import java.util.List;

public class ColoniaCatalogo extends GestorCatalogos<Colonia>
{
    private static ColoniaCatalogo coloniaCatalogo;
    private static final GenericJdbc<Colonia> coloniaJdbc = ColoniaJdbcImpl.getInstance();

    public static ColoniaCatalogo getInstance( )
    {
        if(coloniaCatalogo==null)
        {
            coloniaCatalogo = new ColoniaCatalogo();
        }
        return coloniaCatalogo;
    }

    private ColoniaCatalogo( )
    {
        super(ColoniaJdbcImpl.getInstance());
    }

    @Override
    public Colonia newT()
    {
        return new Colonia();
    }

    @Override
    public boolean processNewT(Colonia colonia)
    {
        System.out.print("> Teclee el nombre de la colonia: ");
        colonia.setNombre( ReadUtil.read() );
        System.out.print("> Teclee el código postal de la colonia: ");
        colonia.setCp( ReadUtil.read() );

        System.out.print("> Teclee el ID del municipio al que pertenece: ");
        Municipio municipio = MunicipioJdbcImpl.getInstance().findById(ReadUtil.readInt());

        if(municipio==null)
        {
            return false;
        }
        colonia.setMunicipio(municipio);

        coloniaJdbc.save(colonia);
        return true;
    }

    @Override
    public void edit(Colonia colonia)
    {
        System.out.print("> Inserte el ID de la colonia a editar: ");
        colonia.setId( ReadUtil.readInt() );
        System.out.print("> Ingrese el nuevo nombre de la colonia: ");
        colonia.setNombre( ReadUtil.read() );
        System.out.print("> Ingrese el nuevo código postal de la colonia: ");
        colonia.setCp( ReadUtil.read() );

        coloniaJdbc.update(colonia);
    }

}

