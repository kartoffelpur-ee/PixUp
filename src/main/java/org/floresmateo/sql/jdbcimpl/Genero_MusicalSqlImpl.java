package org.floresmateo.sql.jdbcimpl;

import org.floresmateo.sql.Conexion;
import org.floresmateo.sql.GenericSql;
import org.floresmateo.model.Disco;
import org.floresmateo.model.Genero_Musical;
import org.floresmateo.util.ReadUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Genero_MusicalSqlImpl extends Conexion implements GenericSql<Genero_Musical>
{
    private static Genero_MusicalSqlImpl generoMusicalJdbc;

    private Genero_MusicalSqlImpl()
    {
    }

    public static Genero_MusicalSqlImpl getInstance()
    {
        if(generoMusicalJdbc==null)
        {
            generoMusicalJdbc = new Genero_MusicalSqlImpl();
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
        List<Disco> list = DiscoSqlImpl.getInstance().findByGenero_MusicalId(genero_Musical.getId());

        if(!list.isEmpty())
        {
            System.out.println("\n> No se puede eliminar el genero porque tiene los siguientes discos asociados: ");
            for(Disco disco: list)
            {
                System.out.println("- [ID: "+disco.getId()+"], [TITULO: "+disco.getTituloDisco()+"]");
            }

            System.out.print("> Desea eliminar también estos discos? (S/N): ");
            String respuesta = ReadUtil.read();

            if(!respuesta.equalsIgnoreCase("S"))
            {
                System.out.println("> Eliminación cancelada.");
                return false;
            }

            for(Disco disco: list)
            {
                DiscoSqlImpl.getInstance().delete(disco);
                System.out.println("> Discos eliminados.");
            }
        }

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
