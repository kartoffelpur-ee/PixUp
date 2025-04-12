package org.floresmateo.vista.consola.disco;
import org.floresmateo.sql.GenericSql;
import org.floresmateo.model.Genero_Musical;
import org.floresmateo.sql.hibernateimpl.Genero_MusicalHiberImpl;
import org.floresmateo.util.ReadUtil;
import org.floresmateo.vista.consola.GestorCatalogos;

import java.util.List;

public class GeneroMusicalCatalogo extends GestorCatalogos<Genero_Musical>
{
    private static GeneroMusicalCatalogo generoMusicalCatalogo;
    private static final GenericSql<Genero_Musical> genero_MusicalSql = Genero_MusicalHiberImpl.getInstance();

    private GeneroMusicalCatalogo()
    {
        super(Genero_MusicalHiberImpl.getInstance());
    }

    public static GeneroMusicalCatalogo getInstance()
    {
        if(generoMusicalCatalogo==null)
        {
            generoMusicalCatalogo = new GeneroMusicalCatalogo();
        }
        return generoMusicalCatalogo;
    }

    @Override
    public Genero_Musical newT() {
        return new Genero_Musical();
    }

    @Override
    public boolean processNewT(Genero_Musical generoMusical)
    {
        System.out.print("> Ingrese el género musical: ");
        generoMusical.setGenero( ReadUtil.read() );
        genero_MusicalSql.save(generoMusical);
        return true;
    }

    @Override
    public boolean processEditT(Genero_Musical generoMusical)
    {
        System.out.print("> Ingrese el nuevo nombre del género musical: ");
        generoMusical.setGenero( ReadUtil.read() );

        genero_MusicalSql.update(generoMusical);
        return true;
    }
}
