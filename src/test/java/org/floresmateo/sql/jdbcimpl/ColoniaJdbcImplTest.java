package org.floresmateo.sql.jdbcimpl;

import org.floresmateo.sql.GenericSql;
import org.floresmateo.model.Colonia;
import org.floresmateo.model.Municipio;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ColoniaJdbcImplTest
{

    @Test
    void getInstance()
    {
        assertNotNull(ColoniaSqlImpl.getInstance());
    }

    @Test
    void findAll()
    {
        GenericSql<Colonia> coloniaJdbc = ColoniaSqlImpl.getInstance();
        List<Colonia> list = coloniaJdbc.findAll();
        assertNotNull(list);
        assertFalse(list.isEmpty());
        list.stream().forEach(System.out::println);
    }

    @Test
    void save()
    {
        GenericSql<Colonia> coloniaJdbc = ColoniaSqlImpl.getInstance();
        Colonia colonia = new Colonia();
        colonia.setNombre("Colonia 3");
        colonia.setCp("23124");

        Municipio municipio = new Municipio();
        municipio.setId(1);
        colonia.setMunicipio(municipio);

        assertTrue(coloniaJdbc.save(colonia));
    }

    @Test
    void update()
    {
        GenericSql<Colonia> coloniaJdbc = ColoniaSqlImpl.getInstance();
        Colonia colonia = new Colonia();
        colonia.setNombre("Colonia3");
        colonia.setId(3);

        assertTrue(coloniaJdbc.update(colonia));
    }

    @Test
    void delete()
    {
        GenericSql<Colonia> coloniaJdbc = ColoniaSqlImpl.getInstance();
        Colonia colonia = new Colonia();
        colonia.setId(1);

        assertTrue(coloniaJdbc.delete(colonia));
    }

    @Test
    void findById()
    {
        GenericSql<Colonia> coloniaJdbc = ColoniaSqlImpl.getInstance();
        Colonia colonia = coloniaJdbc.findById(2);

        assertNotNull(colonia);
        assertEquals(2, colonia.getId());

        System.out.println(colonia.getNombre());
    }
}