package org.floresmateo.jdbc.impl;

import org.floresmateo.jdbc.GenericJdbc;
import org.floresmateo.model.Estado;
import org.floresmateo.model.Municipio;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MunicipioJdbcImplTest {

    @Test
    void getInstance()
    {
        assertNotNull(MunicipioJdbcImpl.getInstance());
        //assertNull(EstadoJdbcImpl.getInstance());
    }

    @Test
    void findAll()
    {
        GenericJdbc<Municipio> municipioJdbc = MunicipioJdbcImpl.getInstance();
        List<Municipio> list = municipioJdbc.findAll();
        assertNotNull( list );
        assertFalse(list.isEmpty());
        list.stream().forEach(System.out::println);
    }

    @Test
    void save()
    {
        GenericJdbc<Municipio> municipioJdbc = MunicipioJdbcImpl.getInstance();
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
        GenericJdbc<Municipio> municipioJdbc = MunicipioJdbcImpl.getInstance();
        Municipio municipio = new Municipio();
        municipio.setNombre("Reinosa");
        municipio.setId(5);
        assertTrue(municipioJdbc.update(municipio));
    }

    @Test
    void delete()
    {
        GenericJdbc<Municipio> municipioJdbc = MunicipioJdbcImpl.getInstance();
        Municipio municipio = new Municipio();
        municipio.setId(5);
        assertTrue(municipioJdbc.delete(municipio));
    }

    @Test
    void findById()
    {
        GenericJdbc<Municipio> municipioGenericJdbc = MunicipioJdbcImpl.getInstance();

        Municipio municipio = municipioGenericJdbc.findById(4);

        assertNotNull(municipio);
        assertEquals(4, municipio.getId());

        System.out.println(municipio.getNombre());
    }
}