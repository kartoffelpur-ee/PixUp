package org.floresmateo.sql.hibernateimpl;

import org.floresmateo.model.Estado;
import org.floresmateo.model.Municipio;
import org.floresmateo.sql.GenericSql;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MunicipioHiberImplTest {

    @Test
    void findAll()
    {
        MunicipioHiberImpl municipioHiber = MunicipioHiberImpl.getInstance();
        List<Municipio> list = municipioHiber.findAll();

        assertNotNull(list);
        list.forEach(System.out::println);
    }

    @Test
    void save()
    {
        GenericSql<Municipio> municipioHiber = MunicipioHiberImpl.getInstance();
        GenericSql<Estado> estadoHiber = EstadoHiberImpl.getInstance();

        for(int i = 1; i<=5; i++ )
        {
            Municipio municipio = new Municipio();
            municipio.setNombre(" Prueba ");

            Estado estado = estadoHiber.findById(i);

            assertNotNull( estado );
            municipio.setEstado(estado);

            assertNotNull( municipio );
            municipioHiber.save( municipio );
        }
    }
}