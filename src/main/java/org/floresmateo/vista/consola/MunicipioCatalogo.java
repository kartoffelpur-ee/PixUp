package org.floresmateo.vista.consola;

import org.floresmateo.model.Estado;
import org.floresmateo.model.Municipio;
import org.floresmateo.util.ReadUtil;

import java.io.File;

public class MunicipioCatalogo extends GestorCatalogos<Municipio>
{
    public static MunicipioCatalogo municipioCatalogo;
    private EstadoCatalogo estadoCatalogo;

    public static MunicipioCatalogo getInstance( )
    {
        if(municipioCatalogo==null)
        {
            municipioCatalogo = new MunicipioCatalogo();
        }
        return municipioCatalogo;
    }

    private MunicipioCatalogo( )
    {
        super();
        estadoCatalogo = EstadoCatalogo.getInstance();
    }

    @Override
    public Municipio newT()
    {
        return new Municipio();
    }

    @Override
    public boolean processNewT(Municipio municipio)
    {
        System.out.print("> Teclee el nombre del municipio: ");
        municipio.setNombre( ReadUtil.read() );

        Estado estado = estadoCatalogo.getEstadoById();

        if(estado==null)
        {
            return false;
        }
        municipio.setEstado(estado);
        return true;
    }

    @Override
    public void processEditT(Municipio municipio)
    {
        System.out.println("> ID del municipio siendo editado: "+municipio.getId());
        System.out.println("> Municipio siendo editado: "+municipio.getNombre());
        System.out.print("> Ingrese el nuevo nombre del municipio: ");
        municipio.setNombre( ReadUtil.read() );

        System.out.println("> Ingrese el ID del nuevo estado para este municipio: ");
        Estado estado = estadoCatalogo.getEstadoById();

        if(estado==null)
        {
            System.out.println("> Estado no encontrado. No se pudo actualizar el estado del municipio, compruébelo e inténtelo de nuevo.");
        }
        else
        {
            municipio.setEstado(estado);
        }
    }

    public Municipio getMunicipioById() {
        if (isListaEmpty()) {
            System.out.println("> No hay municipios registrados.");
            return null;
        }
        while (true) {
            System.out.print("> Ingrese el ID del municipio: ");
            int id = ReadUtil.readInt();
            Municipio municipio = list.stream()
                    .filter(e -> e.getId().equals(id))
                    .findFirst()
                    .orElse(null);
            if (municipio != null) {
                return municipio;
            }
            System.out.println("> ID incorrecto, inténtelo nuevamente.");
        }
    }

    @Override
    public File getFile() {
        return new File("./src/main/resources/Municipios.object" );
    }
}

