package org.floresmateo.jdbc.impl;

import org.floresmateo.jdbc.Conexion;
import org.floresmateo.jdbc.GenericJdbc;
import org.floresmateo.model.Genero_Musical;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Genero_MusicalJdbcImpl extends Conexion implements GenericJdbc<Genero_Musical>
{
    private static Genero_MusicalJdbcImpl generoMusicalJdbc;

    private Genero_MusicalJdbcImpl()
    {
    }

    public static Genero_MusicalJdbcImpl getInstance()
    {
        if(generoMusicalJdbc==null)
        {
            generoMusicalJdbc = new Genero_MusicalJdbcImpl();
        }
        return generoMusicalJdbc;
    }
    
    @Override
    public List<Genero_Musical> findAll()
    {
        Statement statement = null;
        ResultSet resultSet = null;
        List<Genero_Musical> list = null;
        Genero_Musical genero_Musical = null;
        String sql ="SELECT * FROM tbl_genero_Musical";

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
                genero_Musical = new Genero_Musical();
                genero_Musical.setId( resultSet.getInt( "ID" ) );
                genero_Musical.setGenero( resultSet.getString( "GENERO" ) );
                list.add( genero_Musical );
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
    public boolean save(Genero_Musical genero_Musical)
    {
        PreparedStatement preparedStatement = null;
        String query = "INSERT INTO tbl_genero_Musical (GENERO) VALUES ( ? )";
        int res = 0;

        try
        {
            if( !openConnection() )
            {
                System.out.println("> Error de conexión.");
                return false;
            }
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, genero_Musical.getGenero());

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
    public boolean update(Genero_Musical genero_Musical)
    {
        PreparedStatement preparedStatement = null;
        String query = "UPDATE tbl_genero_Musical SET GENERO = ? WHERE ID = ?";
        int res = 0;

        try
        {
            if( !openConnection() )
            {
                System.out.println("> Error de conexión.");
                return false;
            }
            preparedStatement = connection.prepareStatement(query);

            preparedStatement.setString(1, genero_Musical.getGenero());
            preparedStatement.setInt(2, genero_Musical.getId());

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
    public boolean delete(Genero_Musical genero_Musical)
    {
        PreparedStatement preparedStatement = null;
        String query = "DELETE FROM tbl_genero_Musical WHERE ID = ?";
        int res = 0;

        try
        {
            if( !openConnection() )
            {
                System.out.println("> Error de conexión.");
                return false;
            }
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, genero_Musical.getId());

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
    public Genero_Musical findById(Integer id)
    {
        Genero_Musical genero_Musical = null;
        String query = "SELECT * FROM tbl_genero_Musical WHERE ID = ?";
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
                genero_Musical = new Genero_Musical();
                genero_Musical.setId(resultSet.getInt( "ID" ));
                genero_Musical.setGenero(resultSet.getString( "GENERO" ));
            }

            preparedStatement.close();
            closeConnection();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            return null;
        }
        return genero_Musical;
    }
}
