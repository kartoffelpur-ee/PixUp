package org.floresmateo.vista.consola.usuario;

import org.floresmateo.sql.GenericSql;
import org.floresmateo.sql.jdbcimpl.ColoniaSqlImpl;
import org.floresmateo.sql.jdbcimpl.MunicipioSqlImpl;
import org.floresmateo.model.Colonia;
import org.floresmateo.model.Municipio;
import org.floresmateo.util.ReadUtil;
import org.floresmateo.vista.consola.GestorCatalogos;

import java.util.List;

public class ColoniaCatalogo extends GestorCatalogos<Colonia>
{
    private static ColoniaCatalogo coloniaCatalogo;
    private static final GenericSql<Colonia> coloniaJdbc = ColoniaSqlImpl.getInstance();

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
        super(ColoniaSqlImpl.getInstance());
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

        MunicipioSqlImpl municipioJdbc = MunicipioSqlImpl.getInstance();
        List<Municipio> list = municipioJdbc.findAll();
        list.stream().forEach(System.out::println);
        System.out.print("> Teclee el ID del municipio al que pertenece: ");
        Municipio municipio = MunicipioSqlImpl.getInstance().findById(ReadUtil.readInt());
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
        List<Colonia> list = coloniaJdbc.findAll();
        list.stream().forEach(System.out::println);
        System.out.print("> Inserte el ID de la colonia a editar: ");
        colonia.setId( ReadUtil.readInt() );
        System.out.print("> Ingrese el nuevo nombre de la colonia: ");
        colonia.setNombre( ReadUtil.read() );
        System.out.print("> Ingrese el nuevo código postal de la colonia: ");
        colonia.setCp( ReadUtil.read() );
        coloniaJdbc.update(colonia);
    }
}

