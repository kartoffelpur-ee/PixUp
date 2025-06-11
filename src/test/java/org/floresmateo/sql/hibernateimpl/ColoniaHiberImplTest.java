package org.floresmateo.sql.hibernateimpl;

import org.floresmateo.model.Colonia;
import org.floresmateo.model.Estado;
import org.floresmateo.model.Municipio;
import org.floresmateo.sql.GenericSql;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ColoniaHiberImplTest {

    @Test
    void getInstance()
    {
        GenericSql<Colonia> coloniaHiberImpl = ColoniaHiberImpl.getInstance();
        assertNotNull( coloniaHiberImpl );
    }

    @Test
    void findAll()
    {
        GenericSql<Colonia> coloniaHiberImpl = ColoniaHiberImpl.getInstance();
        List<Colonia> list = coloniaHiberImpl.findAll();

        assertNotNull(list);
        list.forEach(System.out::println);
    }

    @Test
    void save()
    {
        GenericSql<Colonia> coloniaHiberImpl = ColoniaHiberImpl.getInstance();
        GenericSql<Municipio> municipioHiber = MunicipioHiberImpl.getInstance();

        for(int i = 1; i<3; i++ )
        {
            Municipio municipio = municipioHiber.findById(i);

            Colonia colonia = new Colonia();
            colonia.setColonia( " Colonia de Prueba "+i);
            colonia.setCp("0000");
            colonia.setMunicipio( municipio );

            assertNotNull( coloniaHiberImpl );
            coloniaHiberImpl.save(colonia);
        }
    }

    @Test
    void update()
    {
        GenericSql<Colonia> coloniaHiberImpl = ColoniaHiberImpl.getInstance();
        GenericSql<Municipio> municipioHiber = MunicipioHiberImpl.getInstance();

        Municipio municipio = municipioHiber.findById(1);

        Colonia colonia = new Colonia();
        colonia.setId(1);
        colonia.setColonia( "Colonia " + colonia.getId() + " nueva");
        colonia.setCp("0000");
        colonia.setMunicipio(municipio);

        assertNotNull( colonia );
        coloniaHiberImpl.update( colonia );
    }

    @Test
    void delete()
    {
        GenericSql<Colonia> coloniaHiberImpl = ColoniaHiberImpl.getInstance();
        Colonia colonia = coloniaHiberImpl.findById(2);

        assertNotNull( colonia );
        coloniaHiberImpl.delete( colonia );
    }

    @Test
    void findById()
    {
        GenericSql<Colonia> coloniaHiberImpl = ColoniaHiberImpl.getInstance();
        Colonia colonia = coloniaHiberImpl.findById(2);

        assertNotNull( colonia );
        System.out.println( colonia );
    }
}