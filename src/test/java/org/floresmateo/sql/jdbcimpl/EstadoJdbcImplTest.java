package org.floresmateo.sql.jdbcimpl;

import org.floresmateo.sql.GenericJdbc;
import org.floresmateo.model.Estado;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class EstadoJdbcImplTest
{

    @Test
    void getInstance()
    {
        assertNotNull(EstadoJdbcImpl.getInstance());
        //assertNull(EstadoJdbcImpl.getInstance());
    }

    @Test
    void findAll()
    {
        GenericJdbc<Estado> estadoJdbc= EstadoJdbcImpl.getInstance();
        List<Estado> list = estadoJdbc.findAll();
        assertNotNull( list );
        assertFalse(list.isEmpty());
        list.stream().forEach(System.out::println);
    }

    @Test
    void save()
    {
        GenericJdbc<Estado> estadoJdbc = EstadoJdbcImpl.getInstance();
        Estado estado = new Estado();
        estado.setNombre("CDMX");
        assertTrue(estadoJdbc.save(estado));
    }

    @Test
    void update()
    {
        GenericJdbc<Estado> estadoJdbc = EstadoJdbcImpl.getInstance();
        Estado estado = new Estado();
        estado.setNombre("Ciudad de mejico");
        estado.setId(1);
        assertTrue(estadoJdbc.update(estado));
    }

    @Test
    void delete()
    {
        GenericJdbc<Estado> estadoJdbc = EstadoJdbcImpl.getInstance();
        Estado estado = new Estado();
        estado.setId(1);
        assertTrue(estadoJdbc.delete(estado));
    }

    @Test
    void findById()
    {
        GenericJdbc<Estado> estadoJdbc = EstadoJdbcImpl.getInstance();

        Estado estado = estadoJdbc.findById(4);

        assertNotNull(estado);
        assertEquals(4, estado.getId());

        System.out.println(estado);
    }
}