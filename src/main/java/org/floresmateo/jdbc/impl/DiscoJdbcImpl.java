package org.floresmateo.jdbc.impl;

import org.floresmateo.jdbc.Conexion;
import org.floresmateo.jdbc.GenericJdbc;
import org.floresmateo.model.Artista;
import org.floresmateo.model.Disco;
import org.floresmateo.model.Disquera;
import org.floresmateo.model.Genero_Musical;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class DiscoJdbcImpl extends Conexion implements GenericJdbc<Disco>
{
    @Override
    public List<Disco> findAll() {
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        Disco disco = null;
        List<Disco> list = null;
        String sql = "SELECT tbl_disco.*, tbl_artista.ARTISTA AS ARTISTA, tbl_disquera.DISQUERA AS DISQUERA, tbl_genero_musical.GENERO AS GENERO " +
                "FROM tbl_disco " +
                "INNER JOIN tbl_artista ON tbl_disco.tbl_artista_id = tbl_artista.id " +
                "INNER JOIN tbl_disquera ON tbl_disco.tbl_disquera_id = tbl_disquera.id " +
                "INNER JOIN tbl_genero_musical on tbl_disco.tbl_genero_musical_id = tbl_genero_musical.id" +
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
                disco = new Disco();
                disco.setId( resultSet.getInt(1) );
                disco.setTituloDisco( resultSet.getString(2) );
                disco.setPrecio( resultSet.getDouble(3) );
                disco.setExistencias( resultSet.getInt(4) );
                disco.setDescuento( resultSet.getDouble(5) );

                Date date = resultSet.getDate(6);
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd");
                String fechaFormateada = simpleDateFormat.format(date);

                disco.setFechaLanzamiento( fechaFormateada );
                disco.setImagen( resultSet.getString(7) );
                disco.setArtista( new Artista(resultSet.getString(11)) );
                disco.setDisquera( new Disquera(resultSet.getString(12)) );
                disco.setGeneroMusical( new Genero_Musical(resultSet.getString(13)) );

                list.add(disco);
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
