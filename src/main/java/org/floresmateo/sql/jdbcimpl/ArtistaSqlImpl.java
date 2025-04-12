package org.floresmateo.sql.jdbcimpl;

import org.floresmateo.sql.Conexion;
import org.floresmateo.sql.GenericSql;
import org.floresmateo.model.Artista;
import org.floresmateo.model.Disco;
import org.floresmateo.util.ReadUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ArtistaSqlImpl extends Conexion implements GenericSql<Artista>
{
    private static ArtistaSqlImpl artistaJdbc;

    private ArtistaSqlImpl()
    {
        super();
    }

    public static ArtistaSqlImpl getInstance()
    {
        if(artistaJdbc==null)
        {
            artistaJdbc = new ArtistaSqlImpl();
        }
        return artistaJdbc;
    }

    @Override
    public List<Artista> findAll()
    {
        Statement statement = null;
        ResultSet resultSet = null;
        List<Artista> list = null;
        Artista artista = null;
        String sql ="SELECT * FROM tbl_artista";

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
                artista = new Artista();
                artista.setId( resultSet.getInt( "ID" ) );
                artista.setArtista( resultSet.getString( "ARTISTA" ) );
                list.add( artista );
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
    public boolean save(Artista artista)
    {
        PreparedStatement preparedStatement = null;
        String query = "INSERT INTO tbl_artista (ARTISTA) VALUES ( ? )";
        int res = 0;

        try
        {
            if( !openConnection() )
            {
                System.out.println("> Error de conexión.");
                return false;
            }
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, artista.getArtista());

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
    public boolean update(Artista artista)
    {
        PreparedStatement preparedStatement = null;
        String query = "UPDATE tbl_artista SET ARTISTA = ? WHERE ID = ?";
        int res = 0;

        try
        {
            if( !openConnection() )
            {
                System.out.println("> Error de conexión.");
                return false;
            }
            preparedStatement = connection.prepareStatement(query);

            preparedStatement.setString(1, artista.getArtista());
            preparedStatement.setInt(2, artista.getId());

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
    public boolean delete(Artista artista)
    {
        PreparedStatement preparedStatement = null;
        String query = "DELETE FROM tbl_artista WHERE ID = ?";
        int res = 0;
        List<Disco> list = DiscoSqlImpl.getInstance().findByArtistaId(artista.getId());

        if(!list.isEmpty())
        {
            System.out.println("\n> No se puede eliminar el artista porque tiene los siguientes discos asociados: ");
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
            preparedStatement.setInt(1, artista.getId());

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
    public Artista findById(Integer id)
    {
        Artista artista = null;
        String query = "SELECT * FROM tbl_artista WHERE ID = ?";
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
                artista = new Artista();
                artista.setId(resultSet.getInt( "ID" ));
                artista.setArtista(resultSet.getString( "ARTISTA" ));
            }

            preparedStatement.close();
            closeConnection();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            return null;
        }
        return artista;
    }
}
