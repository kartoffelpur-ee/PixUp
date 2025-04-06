package org.floresmateo.jdbc.impl;

import org.floresmateo.jdbc.Conexion;
import org.floresmateo.jdbc.GenericJdbc;
import org.floresmateo.model.Disquera;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DisqueraJdbcImpl extends Conexion implements GenericJdbc<Disquera>
{

    @Override
    public List<Disquera> findAll()
    {
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        Disquera disquera = null;
        List<Disquera> list = null;
        String sql = "SELECT * FROM tbl_disquera";

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
                disquera = new Disquera();
                disquera.setId( resultSet.getInt(1) );
                disquera.setDisquera( resultSet.getString(2) );
                list.add(disquera);
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
