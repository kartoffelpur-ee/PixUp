package org.floresmateo.vista.consola.usuario;
import org.floresmateo.jdbc.GenericJdbc;
import org.floresmateo.jdbc.impl.EstadoJdbcImpl;
import org.floresmateo.jdbc.impl.MunicipioJdbcImpl;
import org.floresmateo.model.Estado;
import org.floresmateo.model.Municipio;
import org.floresmateo.util.ReadUtil;
import org.floresmateo.vista.consola.GestorCatalogos;

import java.io.File;
import java.util.List;

public class MunicipioCatalogo extends GestorCatalogos<Municipio>
{
    private static MunicipioCatalogo municipioCatalogo;
    private static final GenericJdbc<Municipio> municipioJdbc = MunicipioJdbcImpl.getInstance();

    public static MunicipioCatalogo getInstance( )
    {
        if(municipioCatalogo==null)
        {
            municipioCatalogo = new MunicipioCatalogo();
        }
        return municipioCatalogo;
    }

    private MunicipioCatalogo( )
    {
        super(MunicipioJdbcImpl.getInstance());
    }

    @Override
    public Municipio newT()
    {
        return new Municipio();
    }

    @Override
    public boolean processNewT(Municipio municipio)
    {
        System.out.print("> Teclee el nombre del municipio: ");
        municipio.setNombre( ReadUtil.read() );

        EstadoJdbcImpl estadoJdbc = EstadoJdbcImpl.getInstance();
        List<Estado> list = estadoJdbc.findAll();
        list.stream().forEach(System.out::println);
        System.out.print("> Teclee el ID del estado al que pertenece: ");

        Estado estado = EstadoJdbcImpl.getInstance().findById(ReadUtil.readInt());
        if(estado==null)
        {
            return false;
        }
        municipio.setEstado(estado);
        municipioJdbc.save(municipio);
        return true;
    }

    @Override
    public void edit(Municipio municipio)
    {
        List<Municipio> list = municipioJdbc.findAll();
        list.stream().forEach(System.out::println);
        System.out.print("> Ingrese el ID del municipio a editar: ");
        municipio.setId( ReadUtil.readInt() );
        System.out.print("> Ingrese el nuevo nombre del municipio: ");
        municipio.setNombre( ReadUtil.read() );
        municipioJdbc.update(municipio);
    }
}

