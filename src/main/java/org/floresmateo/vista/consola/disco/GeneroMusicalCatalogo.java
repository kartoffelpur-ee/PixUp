package org.floresmateo.vista.consola.disco;

import org.floresmateo.model.GeneroMusical;
import org.floresmateo.util.ReadUtil;
import org.floresmateo.vista.consola.GestorCatalogos;

import java.io.File;

public class GeneroMusicalCatalogo extends GestorCatalogos<GeneroMusical>
{
    private static GeneroMusicalCatalogo generoMusicalCatalogo;

    private GeneroMusicalCatalogo()
    {
        super();
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
    public GeneroMusical newT() {
        return new GeneroMusical();
    }

    @Override
    public boolean processNewT(GeneroMusical generoMusical) {
        System.out.print("> Ingrese el género musical: ");
        generoMusical.setGenero( ReadUtil.read() );
        return true;
    }

    @Override
    public void processEditT(GeneroMusical generoMusical) {
        System.out.println("\n> ID del género siendo editado: "+generoMusical.getId());
        System.out.println("> Nombre del género siendo editado: "+generoMusical.getGenero());
        System.out.print("> Ingrese el nuevo nombre del género: ");
        generoMusical.setGenero( ReadUtil.read() );
    }

    @Override
    public File getFile() {
        return new File("./src/main/fileStorage/Generos.list");
    }

    public GeneroMusical getGeneroById() {
        if (isListaEmpty()) {
            System.out.println("> No hay géneros registrados.");
            return null;
        }
        while (true) {
            System.out.print("> Ingrese el ID del género: ");
            int id = ReadUtil.readInt();
            GeneroMusical generoMusical = list.stream()
                    .filter(e -> e.getId().equals(id))
                    .findFirst()
                    .orElse(null);
            if (generoMusical != null) {
                return generoMusical;
            }
            System.out.println("> ID incorrecto, inténtelo nuevamente.");
        }
    }
}
