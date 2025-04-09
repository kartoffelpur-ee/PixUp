package org.floresmateo.jdbc.impl;

import org.floresmateo.jdbc.Conexion;
import org.floresmateo.jdbc.GenericJdbc;
import org.floresmateo.model.Disquera;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DisqueraJdbcImpl extends Conexion implements GenericJdbc<Disquera>
{
    private static DisqueraJdbcImpl disqueraJdbc;

    private DisqueraJdbcImpl()
    {
    }

    public static DisqueraJdbcImpl getInstance()
    {
        if(disqueraJdbc==null)
        {
            disqueraJdbc = new DisqueraJdbcImpl();
        }
        return disqueraJdbc;
    }

    @Override
    public List<Disquera> findAll()
    {
        Statement statement = null;
        ResultSet resultSet = null;
        List<Disquera> list = null;
        Disquera disquera = null;
        String sql ="SELECT * FROM tbl_disquera";

        try
        {
            if( !openConnection() )
            {
                return null;
            }

            statement = connection.createStatement();
            resultSet = statement.executeQuery( sql );

            if( resultSet == null )
            {
                return null;
            }

            list =  new ArrayList<>( );

            while( resultSet.next( ) )
            {
                disquera = new Disquera();
                disquera.setId( resultSet.getInt( "ID" ) );
                disquera.setDisquera( resultSet.getString( "DISQUERA" ) );
                list.add( disquera );
            }

            resultSet.close( );
            closeConnection( );

            return list;
        }
        catch (SQLException e)
        {
            return null;
        }
    }

    @Override
    public boolean save(Disquera disquera)
    {
        PreparedStatement preparedStatement = null;
        String query = "INSERT INTO tbl_disquera (DISQUERA) VALUES ( ? )";
        int res = 0;

        try
        {
            if( !openConnection() )
            {
                System.out.println("> Error de conexión.");
                return false;
            }
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, disquera.getDisquera());

            res = preparedStatement.executeUpdate();

            preparedStatement.close();
            closeConnection();

            return res==1;
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean update(Disquera disquera)
    {
        PreparedStatement preparedStatement = null;
        String query = "UPDATE tbl_disquera SET DISQUERA = ? WHERE ID = ?";
        int res = 0;

        try
        {
            if( !openConnection() )
            {
                System.out.println("> Error de conexión.");
                return false;
            }
            preparedStatement = connection.prepareStatement(query);

            preparedStatement.setString(1, disquera.getDisquera());
            preparedStatement.setInt(2, disquera.getId());

            res = preparedStatement.executeUpdate();

            preparedStatement.close();
            closeConnection();

            return res==1;

        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean delete(Disquera disquera)
    {
        PreparedStatement preparedStatement = null;
        String query = "DELETE FROM tbl_disquera WHERE ID = ?";
        int res = 0;

        try
        {
            if( !openConnection() )
            {
                System.out.println("> Error de conexión.");
                return false;
            }
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, disquera.getId());

            res = preparedStatement.executeUpdate();
            preparedStatement.close();
            closeConnection();

            return res==1;

        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Disquera findById(Integer id)
    {
        Disquera disquera = null;
        String query = "SELECT * FROM tbl_disquera WHERE ID = ?";
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try
        {
            if( !openConnection() )
            {
                return null;
            }
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);

            resultSet = preparedStatement.executeQuery();

            if(resultSet.next())
            {
                disquera = new Disquera();
                disquera.setId(resultSet.getInt( "ID" ));
                disquera.setDisquera(resultSet.getString( "DISQUERA" ));
            }

            preparedStatement.close();
            closeConnection();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            return null;
        }
        return disquera;
    }
}
