package org.floresmateo.jdbc.impl;

import org.floresmateo.jdbc.Conexion;
import org.floresmateo.jdbc.GenericJdbc;
import org.floresmateo.model.Artista;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ArtistaJdbcImpl extends Conexion implements GenericJdbc<Artista>
{

    @Override
    public List<Artista> findAll() {
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        Artista artista = null;
        List<Artista> list = null;
        String sql = "SELECT * FROM tbl_artista";

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
                artista = new Artista();
                artista.setId( resultSet.getInt(1) );
                artista.setArtista( resultSet.getString(2) );
                list.add(artista);
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
