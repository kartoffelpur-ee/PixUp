package org.floresmateo.sql.hibernateimpl;

import org.floresmateo.model.Estado;
import org.floresmateo.model.Municipio;
import org.floresmateo.sql.GenericSql;
import org.floresmateo.vista.consola.usuario.MunicipioCatalogo;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
class MunicipioHiberImplTest
{

    @Test
    void getInstance()
    {
        GenericSql<Municipio> municipioHiber = MunicipioHiberImpl.getInstance();
        assertNotNull( municipioHiber );
    }

    @Test
    void findAll()
    {
        GenericSql<Municipio> municipioHiber = MunicipioHiberImpl.getInstance();
        List<Municipio> list = municipioHiber.findAll();

        assertNotNull(list);
        list.forEach(System.out::println);
    }

    @Test
    void save()
    {
        GenericSql<Municipio> municipioHiber = MunicipioHiberImpl.getInstance();
        GenericSql<Estado> estadoHiber = EstadoHiberImpl.getInstance();

        for(int i = 1; i<4; i++ )
        {
            Estado estado = estadoHiber.findById(i);

            Municipio municipio = new Municipio();
            municipio.setMunicipio( " Municipio de Prueba "+i);
            municipio.setEstado( estado );

            assertNotNull( municipioHiber );
            municipioHiber.save(municipio);
        }
    }

    @Test
    void update()
    {
        GenericSql<Municipio> municipioHiber = MunicipioHiberImpl.getInstance();
        GenericSql<Estado> estadoHiber = EstadoHiberImpl.getInstance();

        Estado estado = estadoHiber.findById(1);

        Municipio municipio = new Municipio();
        municipio.setId(1);
        municipio.setMunicipio( "Municipio " + municipio.getId() + " nuevo");
        municipio.setEstado( estado );

        assertNotNull( municipio );
        municipioHiber.update( municipio );
    }

    @Test
    void delete()
    {
        GenericSql<Municipio> municipioHiber = MunicipioHiberImpl.getInstance();

        Municipio municipio = municipioHiber.findById(3);

        assertNotNull( municipio );
        municipioHiber.delete( municipio );
    }

    @Test
    void findById()
    {
        GenericSql<Municipio> municipioHiber = MunicipioHiberImpl.getInstance();
        Municipio municipio = municipioHiber.findById(1);

        assertNotNull( municipio );
        System.out.println(municipio);
    }
}