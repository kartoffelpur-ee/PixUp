package org.floresmateo.sql.jdbcimpl;

import org.floresmateo.sql.Conexion;
import org.floresmateo.sql.GenericJdbc;
import org.floresmateo.model.Estado;
import org.floresmateo.model.Municipio;
import org.floresmateo.util.ReadUtil;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EstadoJdbcImpl extends Conexion implements GenericJdbc<Estado>
{
    private static EstadoJdbcImpl estadoJdbc;

    private EstadoJdbcImpl()
    {
        super();
    }

    public static EstadoJdbcImpl getInstance()
    {
        if(estadoJdbc==null)
        {
            estadoJdbc = new EstadoJdbcImpl();
        }
        return estadoJdbc;
    }

    @Override
    public List<Estado> findAll()
    {
        Statement statement = null;
        ResultSet resultSet = null;
        List<Estado> list = null;
        Estado estado = null;
        String sql ="SELECT * FROM tbl_estado";

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
                estado = new Estado();
                estado.setId( resultSet.getInt( "ID" ) );
                estado.setNombre( resultSet.getString( "NOMBRE" ) );
                list.add( estado );
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
    public boolean save(Estado estado)
    {
        PreparedStatement preparedStatement = null;
        String query = "INSERT INTO tbl_estado (NOMBRE) VALUES ( ? )";
        int res = 0;

        try
        {
            if( !openConnection() )
            {
                System.out.println("> Error de conexión.");
                return false;
            }
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, estado.getNombre());

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
    public boolean update(Estado estado)
    {
        PreparedStatement preparedStatement = null;
        String query = "UPDATE tbl_estado SET NOMBRE = ? WHERE ID = ?";
        int res = 0;

        try
        {
            if( !openConnection() )
            {
                System.out.println("> Error de conexión.");
                return false;
            }
            preparedStatement = connection.prepareStatement(query);

            preparedStatement.setString(1, estado.getNombre());
            preparedStatement.setInt(2, estado.getId());

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
    public boolean delete(Estado estado)
    {
        PreparedStatement preparedStatement = null;
        String query = "DELETE FROM tbl_estado WHERE ID = ?";
        int res = 0;
        List<Municipio> list = MunicipioJdbcImpl.getInstance().findByEstadoId(estado.getId());

        if(!list.isEmpty())
        {
            System.out.println("\n> No se puede eliminar el estado porque tiene los siguientes municipios asociados: ");
            for(Municipio municipio: list)
            {
                System.out.println("- [ID: "+municipio.getId()+"], [NOMBRE: "+municipio.getNombre()+"]");
            }

            System.out.print("> Desea eliminar también estos municipios? (S/N): ");
            String respuesta = ReadUtil.read();

            if(!respuesta.equalsIgnoreCase("S"))
            {
                System.out.println("> Eliminación cancelada.");
                return false;
            }

            for(Municipio municipio: list)
            {
                if(MunicipioJdbcImpl.getInstance().delete(municipio))
                {
                    System.out.println("> Municipios eliminados.");
                }
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
            preparedStatement.setInt(1, estado.getId());

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
    public Estado findById(Integer id)
    {
        Estado estado = null;
        String query = "SELECT * FROM tbl_estado WHERE ID = ?";
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
                estado = new Estado();
                estado.setId(resultSet.getInt( "ID" ));
                estado.setNombre(resultSet.getString( "NOMBRE" ));
            }

            preparedStatement.close();
            closeConnection();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            return null;
        }
        return estado;
    }
}