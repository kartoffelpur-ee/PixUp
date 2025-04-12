package org.floresmateo.sql.jdbcimpl;

import org.floresmateo.sql.GenericJdbc;
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
        assertNotNull(ColoniaJdbcImpl.getInstance());
    }

    @Test
    void findAll()
    {
        GenericJdbc<Colonia> coloniaJdbc = ColoniaJdbcImpl.getInstance();
        List<Colonia> list = coloniaJdbc.findAll();
        assertNotNull(list);
        assertFalse(list.isEmpty());
        list.stream().forEach(System.out::println);
    }

    @Test
    void save()
    {
        GenericJdbc<Colonia> coloniaJdbc = ColoniaJdbcImpl.getInstance();
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
        GenericJdbc<Colonia> coloniaJdbc = ColoniaJdbcImpl.getInstance();
        Colonia colonia = new Colonia();
        colonia.setNombre("Colonia3");
        colonia.setId(3);

        assertTrue(coloniaJdbc.update(colonia));
    }

    @Test
    void delete()
    {
        GenericJdbc<Colonia> coloniaJdbc = ColoniaJdbcImpl.getInstance();
        Colonia colonia = new Colonia();
        colonia.setId(1);

        assertTrue(coloniaJdbc.delete(colonia));
    }

    @Test
    void findById()
    {
        GenericJdbc<Colonia> coloniaJdbc = ColoniaJdbcImpl.getInstance();
        Colonia colonia = coloniaJdbc.findById(2);

        assertNotNull(colonia);
        assertEquals(2, colonia.getId());

        System.out.println(colonia.getNombre());
    }
}