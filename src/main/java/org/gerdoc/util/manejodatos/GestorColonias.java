package org.gerdoc.util.manejodatos;

import org.gerdoc.model.Colonia;
import org.gerdoc.model.Municipio;
import org.gerdoc.util.Gestor;
import org.gerdoc.util.ReadUtil;
import org.gerdoc.vista.Menu;

import java.util.HashMap;
import java.util.Map;

public class GestorColonias implements Gestor
{
    private Map<Integer, Colonia> colonias = new HashMap<>();
    private GestorMunicipios gestorMunicipios;

    public GestorColonias(GestorMunicipios gestorMunicipios) {
        this.gestorMunicipios = gestorMunicipios;
    }


    @Override
    public void alta() {
        Menu.leeId();
        Integer id = ReadUtil.readInt();
        Menu.leeNombre();
        String nombre = ReadUtil.read();
        Menu.leeCp();
        String cp = ReadUtil.read();
        Menu.leeIdMunicipio();
        Integer municipioId = ReadUtil.readInt();

        Municipio municipio = gestorMunicipios.validarMunicipio( municipioId );
        if( municipio == null){
            System.out.println("> Municipio no encontrado.");
        }
        else{
            if(colonias.containsKey(id)){
                System.out.println("> Ya existe una colonia asociada a dicha ID. Colonia NO agregado.");
            }
            else{
                Colonia colonia = new Colonia(id, nombre, cp, municipio);
                colonias.put(id, colonia);
                System.out.println("> Colonia agregado exitosamente.");
                System.out.println(colonia);
            }
        }
    }

    @Override
    public void consulta() {
        if (colonias.isEmpty()) {
            System.out.println("> No hay colonias registrados.");
        } else {
            System.out.println("\n\t:: Lista de colonias registrados ::");
            for (Colonia e: colonias.values()){
                System.out.println(e);
            }
        }
    }

    @Override
    public void baja() {
        Menu.leeId();
        int id = ReadUtil.readInt();

        if (colonias.containsKey(id)){
            colonias.remove(id);
            System.out.println("> Colonia removida correctamente.");
        }
        else
            Menu.idInvalido();
    }

    @Override
    public void actualizar() {
        Menu.leeId();
        int id = ReadUtil.readInt();
        if (colonias.containsKey(id)){
            Menu.leeNombre2();
            String nuevoNombre = ReadUtil.read();
            Menu.leeCp();
            String nuevoCp = ReadUtil.read();

            colonias.get(id).setNombre(nuevoNombre);
            colonias.get(id).setCp(nuevoCp);
            System.out.println("> Colonia actualizada exitosamente.");
        }
        else
            Menu.idInvalido();
    }

}
