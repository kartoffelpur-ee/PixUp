package org.floresmateo.sql.hibernateimpl;

import org.floresmateo.model.Estado;
import org.floresmateo.sql.GenericSql;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class EstadoHiberImplTest {

    @Test
    void getInstance()
    {
        GenericSql<Estado> estadoHiber = EstadoHiberImpl.getInstance();
        assertNotNull( estadoHiber );
    }

    @Test
    void findAll()
    {
        EstadoHiberImpl estadoHiber = EstadoHiberImpl.getInstance();
        List<Estado> list = estadoHiber.findAll();

        assertNotNull(list);
        list.forEach(System.out::println);
    }

    @Test
    void save()
    {
        GenericSql<Estado> estadoHiber = EstadoHiberImpl.getInstance();

        for(int i = 1; i<5; i++ )
        {
            Estado estado = new Estado();
            estado.setEstado(" Prueba ");

            assertNotNull( estado );
            estadoHiber.save(estado);
        }

        estadoHiber.findAll();
    }

    @Test
    void update()
    {
        GenericSql<Estado> estadoHiber = EstadoHiberImpl.getInstance();

        Estado estado = new Estado();
        estado.setId(1);
        estado.setEstado( "NuevoPrueba" );

        assertNotNull( estado );
        estadoHiber.update( estado );
    }

    @Test
    void delete()
    {
        GenericSql<Estado> estadoHiber = EstadoHiberImpl.getInstance();
        Estado estado = estadoHiber.findById(4);

        assertNotNull( estado );
        estadoHiber.delete( estado );
    }

    @Test
    void findById()
    {
        EstadoHiberImpl estadoHiber = EstadoHiberImpl.getInstance();
        Estado estado = estadoHiber.findById(1);

        assertNotNull( estado );
        System.out.println(estado);
    }
}