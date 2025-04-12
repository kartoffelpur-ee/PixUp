package org.floresmateo.sql.jdbcimpl;

import org.floresmateo.sql.GenericSql;
import org.floresmateo.model.Estado;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class EstadoJdbcImplTest
{

    @Test
    void getInstance()
    {
        assertNotNull(EstadoSqlImpl.getInstance());
        //assertNull(EstadoSqlImpl.getInstance());
    }

    @Test
    void findAll()
    {
        GenericSql<Estado> estadoJdbc= EstadoSqlImpl.getInstance();
        List<Estado> list = estadoJdbc.findAll();
        assertNotNull( list );
        assertFalse(list.isEmpty());
        list.stream().forEach(System.out::println);
    }

    @Test
    void save()
    {
        GenericSql<Estado> estadoJdbc = EstadoSqlImpl.getInstance();
        Estado estado = new Estado();
        estado.setEstado("CDMX");
        assertTrue(estadoJdbc.save(estado));
    }

    @Test
    void update()
    {
        GenericSql<Estado> estadoJdbc = EstadoSqlImpl.getInstance();
        Estado estado = new Estado();
        estado.setEstado("Ciudad de mejico");
        estado.setId(1);
        assertTrue(estadoJdbc.update(estado));
    }

    @Test
    void delete()
    {
        GenericSql<Estado> estadoJdbc = EstadoSqlImpl.getInstance();
        Estado estado = new Estado();
        estado.setId(1);
        assertTrue(estadoJdbc.delete(estado));
    }

    @Test
    void findById()
    {
        GenericSql<Estado> estadoJdbc = EstadoSqlImpl.getInstance();

        Estado estado = estadoJdbc.findById(4);

        assertNotNull(estado);
        assertEquals(4, estado.getId());

        System.out.println(estado);
    }
}