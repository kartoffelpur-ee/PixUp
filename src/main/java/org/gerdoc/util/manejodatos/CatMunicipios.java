package org.gerdoc.util.manejodatos;
import org.gerdoc.model.Estado;
import org.gerdoc.model.Municipio;
import org.gerdoc.util.Gestor;
import org.gerdoc.util.ReadUtil;
import org.gerdoc.vista.Menu;
import java.util.HashMap;
import java.util.Map;

public class CatMunicipios implements Gestor
{
    private Map<Integer, Municipio> municipios = new HashMap<>();
    private CatEstados catEstados;

    public CatMunicipios(CatEstados catEstados) {
        this.catEstados = catEstados;
    }

    @Override
    public void alta() {
        Menu.leeId();
        Integer id = ReadUtil.readInt();
        Menu.leeNombre();
        String nombre = ReadUtil.read();
        Menu.leeIdEstado();
        Integer estadoId = ReadUtil.readInt();

        Estado estado = catEstados.validarEstado( estadoId );
        if( estado == null){
            System.out.println("> Estado no encontrado.");
        }
        else{
            if(municipios.containsKey(id)){
                System.out.println("> Ya existe un estado asociado a dicha ID. Estado NO agregado.");
            }
            else{
                Municipio municipio = new Municipio(id, nombre, estado);
                municipios.put(id, municipio);
                System.out.println("> Municipio agregado exitosamente.");
                System.out.println(municipio);
            }
        }
    }

    @Override
    public void baja() {
        Menu.leeId();
        int id = ReadUtil.readInt();

        if (municipios.containsKey(id)){
            municipios.remove(id);
            System.out.println("> Municipio removido correctamente.");
        }
        else
            Menu.idInvalido();
    }

    @Override
    public void consulta() {
        if (municipios.isEmpty()) {
            System.out.println("> No hay estados registrados.");
        } else {
            System.out.println("\n\t:: Lista de estados registrados ::");
            for (Municipio e: municipios.values()){
                System.out.println(e);
            }
        }
    }

    @Override
    public void actualizar() {
        Menu.leeId();
        int id = ReadUtil.readInt();
        if (municipios.containsKey(id)){
            Menu.leeNombre2();
            String nuevoNombre = ReadUtil.read();
            municipios.get(id).setNombre(nuevoNombre);

            Menu.leeIdEstado();
            Integer idEstado = ReadUtil.readInt();

            Estado estado = catEstados.validarEstado( idEstado );
            if( estado == null){
                System.out.println("> Estado no encontrado.");
                System.out.println("> Nombre del municipio actualizado exitosamente.");
            }
            else{
                municipios.get(id).setEstado( estado );
                System.out.println("> Municipio actualizado en su totalidad exitosamente.");
            }
        }
        else
            Menu.idInvalido();
    }

    public Municipio validarMunicipio(Integer id){
        return municipios.get(id);
    }
}

