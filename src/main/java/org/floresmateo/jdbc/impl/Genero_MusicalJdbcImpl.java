package org.floresmateo.jdbc.impl;

import org.floresmateo.jdbc.Conexion;
import org.floresmateo.jdbc.GenericJdbc;
import org.floresmateo.model.Genero_Musical;
import org.floresmateo.model.Genero_Musical;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Genero_MusicalJdbcImpl extends Conexion implements GenericJdbc<Genero_Musical>
{
    @Override
    public List<Genero_Musical> findAll()
    {
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        Genero_Musical genero_musical = null;
        List<Genero_Musical> list = null;
        String sql = "SELECT * FROM tbl_genero_musical";

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
                genero_musical = new Genero_Musical();
                genero_musical.setId( resultSet.getInt(1) );
                genero_musical.setGenero( resultSet.getString(2) );
                list.add(genero_musical);
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
