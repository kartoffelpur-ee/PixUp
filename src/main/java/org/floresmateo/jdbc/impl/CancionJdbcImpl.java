package org.floresmateo.jdbc.impl;

import org.floresmateo.jdbc.Conexion;
import org.floresmateo.jdbc.GenericJdbc;
import org.floresmateo.model.*;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class CancionJdbcImpl extends Conexion implements GenericJdbc<Cancion>
{
    @Override
    public List<Cancion> findAll() {
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        Cancion cancion = null;
        List<Cancion> list = null;
        String sql = "SELECT tbl_cancion.*, tbl_disco.*, tbl_artista.ARTISTA, tbl_disquera.DISQUERA, tbl_genero_musical.GENERO FROM tbl_cancion " +
                "INNER JOIN tbl_disco ON tbl_cancion.tbl_disco_id = tbl_disco.id " +
                "INNER JOIN tbl_artista ON tbl_disco.tbl_artista_id = tbl_artista.id " +
                "INNER JOIN tbl_disquera ON tbl_disco.tbl_disquera_id = tbl_disquera.id " +
                "INNER JOIN tbl_genero_musical ON tbl_disco.tbl_genero_musical_id = tbl_genero_musical.id" +
                ";";

        try
        {
            connection = getConnection();
            if (connection==null)
            {
                return null;
            }
            statement = connection.createStatement( );
            resultSet = statement.executeQuery( sql );
            if (resultSet==null)
            {
                return null;
            }

            list = new ArrayList<>( );

            while( resultSet.next() )
            {
                cancion = new Cancion();
                cancion.setId( resultSet.getInt(1) );
                cancion.setTituloCancion( resultSet.getString(2) );

                Time duracion = resultSet.getTime(3);
                long milisegundos = duracion.getTime();
                double minutos = (double) milisegundos/60000;

                cancion.setDuracion(minutos);

                Date date = resultSet.getDate(10);
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd");
                String fechaFormateada = simpleDateFormat.format(date);

                Disco disco = new Disco(
                        resultSet.getString(6),
                        resultSet.getDouble(7),
                        resultSet.getInt(8),
                        resultSet.getDouble(9),
                        fechaFormateada,
                        resultSet.getString(11),
                        new Disquera(resultSet.getString(16)),
                        new Artista(resultSet.getString(15)),
                        new Genero_Musical(resultSet.getString(17))
                        );

                cancion.setDisco( disco );

                list.add(cancion);
            }

            resultSet.close();
            statement.close();
            connection.close();

            return list;
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        return null;
    }
}
