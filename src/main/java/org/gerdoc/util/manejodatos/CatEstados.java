package org.gerdoc.util.manejodatos;
import org.gerdoc.model.Estado;
import org.gerdoc.util.Gestor;
import org.gerdoc.util.ReadUtil;
import org.gerdoc.vista.Menu;
import java.util.HashMap;
import java.util.Map;

public class CatEstados implements Gestor
{
    private Map<Integer, Estado> estados = new HashMap<>();

    @Override
    public void alta() {
        Menu.leeId();
        Integer id = ReadUtil.readInt();
        Menu.leeNombre();
        String nombre = ReadUtil.read();

        if(estados.containsKey(id)){
            System.out.println("> Ya existe un estado asociado a dicha ID. Estado NO agregado.");
        }
        else{
            Estado estado = new Estado(id, nombre);
            estados.put(id, estado);
            System.out.println("> Estado agregado exitosamente.");
            System.out.println(estado);
        }
    }

    @Override
    public void baja() {
        Menu.leeId();
        int id = ReadUtil.readInt();

        if (estados.containsKey(id)){
            estados.remove(id);
            System.out.println("> Estado removido correctamente.");
        }
        else
            Menu.idInvalido();
    }

    @Override
    public void consulta() {
        if (estados.isEmpty()) {
            System.out.println("> No hay estados registrados.");
        } else {
            System.out.println("\n\t:: Lista de estados registrados ::");
            for (Estado e: estados.values()){
                System.out.println(e);
            }
        }
    }

    @Override
    public void actualizar() {
        Menu.leeId();
        int id = ReadUtil.readInt();
        if (estados.containsKey(id)){
            Menu.leeNombre2();
            String nuevoNombre = ReadUtil.read();

            estados.get(id).setNombre(nuevoNombre);
            System.out.println("> Estado actualizado exitosamente.");
        }
        else
            Menu.idInvalido();
    }

    public Estado validarEstado(Integer id){
        return estados.get(id);
    }
}


