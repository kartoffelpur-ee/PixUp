package org.floresmateo.model;

import org.floresmateo.sql.jdbcimpl.EstadoSqlImpl;
import org.floresmateo.util.ReadUtil;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MunicipioTest {

    @Test
    void municipio()
    {
        Estado estado = new Estado();
        Municipio municipio = new Municipio();

        municipio.setNombre( "Prueba" );
        estado = EstadoSqlImpl.getInstance().findById( 15 );
        if(estado!=null)
        {
            municipio.setEstado(estado);
        }

        assertNotNull(municipio);
    }

    @Test
    void getNombre() {
    }

    @Test
    void getEstado() {
    }

    @Test
    void setNombre() {
    }

    @Test
    void setEstado() {
    }

    @Test
    void testEquals() {
    }

    @Test
    void canEqual() {
    }

    @Test
    void testHashCode() {
    }

    @Test
    void testToString() {
    }
}