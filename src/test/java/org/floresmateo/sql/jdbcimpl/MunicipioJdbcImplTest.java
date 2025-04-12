package org.floresmateo.sql.jdbcimpl;

import org.floresmateo.sql.GenericSql;
import org.floresmateo.model.Estado;
import org.floresmateo.model.Municipio;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MunicipioJdbcImplTest {

    @Test
    void getInstance()
    {
        assertNotNull(MunicipioSqlImpl.getInstance());
        //assertNull(EstadoSqlImpl.getInstance());
    }

    @Test
    void findAll()
    {
        GenericSql<Municipio> municipioJdbc = MunicipioSqlImpl.getInstance();
        List<Municipio> list = municipioJdbc.findAll();
        assertNotNull( list );
        assertFalse(list.isEmpty());
        list.stream().forEach(System.out::println);
    }

    @Test
    void save()
    {
        GenericSql<Municipio> municipioJdbc = MunicipioSqlImpl.getInstance();
        Municipio municipio = new Municipio();
        municipio.setNombre("Reynosa");

        Estado estado = new Estado();
        estado.setId(1);
        municipio.setEstado(estado);
        assertTrue(municipioJdbc.save(municipio));
    }

    @Test
    void update()
    {
        GenericSql<Municipio> municipioJdbc = MunicipioSqlImpl.getInstance();
        Municipio municipio = new Municipio();
        municipio.setNombre("Reinosa");
        municipio.setId(5);
        assertTrue(municipioJdbc.update(municipio));
    }

    @Test
    void delete()
    {
        GenericSql<Municipio> municipioJdbc = MunicipioSqlImpl.getInstance();
        Municipio municipio = new Municipio();
        municipio.setId(5);
        assertTrue(municipioJdbc.delete(municipio));
    }

    @Test
    void findById()
    {
        GenericSql<Municipio> municipioGenericSql = MunicipioSqlImpl.getInstance();

        Municipio municipio = municipioGenericSql.findById(4);

        assertNotNull(municipio);
        assertEquals(4, municipio.getId());

        System.out.println(municipio.getNombre());
    }
}