package org.floresmateo.jdbc.impl;

import org.floresmateo.jdbc.Conexion;
import org.floresmateo.jdbc.GenericJdbc;
import org.floresmateo.model.Colonia;
import org.floresmateo.model.Estado;
import org.floresmateo.model.Municipio;
import org.floresmateo.util.ReadUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MunicipioJdbcImpl extends Conexion implements GenericJdbc<Municipio>
{
    private static MunicipioJdbcImpl municipioJdbc;

    private MunicipioJdbcImpl()
    {
        super();
    }

    public static MunicipioJdbcImpl getInstance()
    {
        if(municipioJdbc==null)
        {
            municipioJdbc = new MunicipioJdbcImpl();
        }
        return municipioJdbc;
    }

    @Override
    public List<Municipio> findAll()
    {
        Statement statement = null;
        ResultSet resultSet = null;
        List<Municipio> list = null;
        Municipio municipio = null;
        String sql ="SELECT tbl_municipio.*, tbl_estado.NOMBRE AS ESTADO " +
                "FROM tbl_municipio " +
                "INNER JOIN tbl_estado ON tbl_estado.id = tbl_municipio.tbl_estado_id;";

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
                municipio = new Municipio();
                municipio.setId( resultSet.getInt( "ID" ) );
                municipio.setNombre( resultSet.getString( "NOMBRE" ) );

                Estado estado = new Estado();
                estado.setId( resultSet.getInt( "TBL_ESTADO_ID" ));
                estado.setNombre( resultSet.getString("ESTADO") );

                municipio.setEstado( estado );

                list.add( municipio );
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
    public boolean save(Municipio municipio)
    {
        PreparedStatement preparedStatement = null;
        String query = "INSERT INTO tbl_municipio (NOMBRE, TBL_ESTADO_ID) VALUES (?,?)";
        int res = 0;

        try
        {
            if( !openConnection() )
            {
                System.out.println("> Error de conexión.");
                return false;
            }
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, municipio.getNombre());
            preparedStatement.setInt(2, municipio.getEstado().getId());

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
    public boolean update(Municipio municipio)
    {
        PreparedStatement preparedStatement = null;
        String query = "UPDATE tbl_municipio SET NOMBRE = ? WHERE ID = ?";
        int res = 0;

        try
        {
            if( !openConnection() )
            {
                System.out.println("> Error de conexión.");
                return false;
            }
            preparedStatement = connection.prepareStatement(query);

            preparedStatement.setString(1, municipio.getNombre());
            preparedStatement.setInt(2, municipio.getId());

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
    public boolean delete(Municipio municipio)
    {
        PreparedStatement preparedStatement = null;
        String query = "DELETE FROM tbl_municipio WHERE ID = ?";
        int res = 0;
        List<Colonia> list = ColoniaJdbcImpl.getInstance().findByMunicipioId(municipio.getId());

        if(!list.isEmpty())
        {
            System.out.println("\n> No se puede eliminar el/los municipio(s) porque tiene(n) las siguientes colonias asociadas: ");
            for(Colonia colonia: list)
            {
                System.out.println("- [ID: "+colonia.getId()+"], [NOMBRE: "+colonia.getNombre()+"]");
            }

            System.out.print("> Desea eliminar también estas colonias? (S/N): ");
            String respuesta = ReadUtil.read();

            if(!respuesta.equalsIgnoreCase("S"))
            {
                System.out.println("> Eliminación cancelada.");
                return false;
            }

            for(Colonia colonia: list)
            {
                ColoniaJdbcImpl.getInstance().delete(colonia);
                System.out.println("> Colonias eliminadas.");
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
            preparedStatement.setInt(1, municipio.getId());

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
    public Municipio findById(Integer id)
    {
        Municipio municipio = null;
        String query = "SELECT * FROM tbl_municipio WHERE ID = ?";
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
                municipio = new Municipio();
                municipio.setId(resultSet.getInt( "ID" ));
                municipio.setNombre(resultSet.getString( "NOMBRE" ));
            }

            preparedStatement.close();
            closeConnection();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            return null;
        }
        return municipio;
    }

    public List<Municipio> findByEstadoId(int estadoId)
    {
        String query = "SELECT ID, NOMBRE FROM tbl_municipio WHERE TBL_ESTADO_ID = ?";
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<Municipio> list = new ArrayList<>();
        try
        {
            if( !openConnection() )
            {
                return null;
            }
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, estadoId);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Municipio municipio = new Municipio();
                municipio.setId(resultSet.getInt("ID"));
                municipio.setNombre(resultSet.getString("NOMBRE")); // O el campo que uses para mostrar el nombre
                list.add(municipio);
            }

            preparedStatement.close();
            closeConnection();
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return list;
    }
}
