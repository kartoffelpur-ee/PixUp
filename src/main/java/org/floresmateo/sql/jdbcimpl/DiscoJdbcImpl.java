package org.floresmateo.sql.jdbcimpl;

import org.floresmateo.sql.Conexion;
import org.floresmateo.sql.GenericJdbc;
import org.floresmateo.model.*;
import org.floresmateo.model.Disco;
import org.floresmateo.util.ReadUtil;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class DiscoJdbcImpl extends Conexion implements GenericJdbc<Disco>
{
    private static DiscoJdbcImpl discoJdbc;

    private DiscoJdbcImpl()
    {
        super();
    }

    public static DiscoJdbcImpl getInstance()
    {
        if(discoJdbc==null)
        {
            discoJdbc = new DiscoJdbcImpl();
        }
        return discoJdbc;
    }

    @Override
    public List<Disco> findAll()
    {
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
                disco = new Disco();
                disco.setId( resultSet.getInt("ID") );
                disco.setTituloDisco( resultSet.getString("TITULO") );
                disco.setPrecio( resultSet.getDouble("PRECIO") );
                disco.setExistencias( resultSet.getInt("EXISTENCIA") );
                disco.setDescuento( resultSet.getDouble("DESCUENTO") );

                Date date = resultSet.getDate("FECHA_LANZAMIENTO");
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd");
                String fechaFormateada = simpleDateFormat.format(date);

                disco.setFechaLanzamiento( fechaFormateada );
                disco.setImagen( resultSet.getString("IMAGEN") );
                disco.setArtista( new Artista(resultSet.getString("ARTISTA")) );
                disco.setDisquera( new Disquera(resultSet.getString("DISQUERA")) );
                disco.setGeneroMusical( new Genero_Musical(resultSet.getString("GENERO")));

                list.add(disco);
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
    public boolean save(Disco disco)
    {
        PreparedStatement preparedStatement = null;
        String query = "INSERT INTO tbl_disco (TITULO, PRECIO, " +
                "EXISTENCIA, DESCUENTO, FECHA_LANZAMIENTO, IMAGEN, TBL_ARTISTA_ID, TBL_DISQUERA_ID, TBL_GENERO_MUSICAL_ID) " +
                "VALUES ( ?,?,?,?,?,?,?,?,? )";
        int res = 0;

        try
        {
            if( !openConnection() )
            {
                System.out.println("> Error de conexión.");
                return false;
            }
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, disco.getTituloDisco());
            preparedStatement.setDouble(2, disco.getPrecio());
            preparedStatement.setInt(3, disco.getExistencias());
            preparedStatement.setDouble(4, disco.getDescuento());

            java.sql.Date fecha = java.sql.Date.valueOf(disco.getFechaLanzamiento());

            preparedStatement.setDate(5,fecha);
            preparedStatement.setString(6, disco.getImagen());
            preparedStatement.setInt(7, disco.getArtista().getId());
            preparedStatement.setInt(8, disco.getDisquera().getId());
            preparedStatement.setInt(9, disco.getGeneroMusical().getId());

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
    public boolean update(Disco disco)
    {
        PreparedStatement preparedStatement = null;
        String query = "UPDATE tbl_disco SET TITULO = ?, PRECIO = ?, EXISTENCIA = ?, DESCUENTO = ?, WHERE ID = ?";
        int res = 0;

        try
        {
            if( !openConnection() )
            {
                System.out.println("> Error de conexión.");
                return false;
            }
            preparedStatement = connection.prepareStatement(query);

            preparedStatement.setString(1, disco.getTituloDisco());
            preparedStatement.setDouble(2, disco.getPrecio());
            preparedStatement.setInt(3, disco.getExistencias());
            preparedStatement.setDouble(4, disco.getDescuento());
            preparedStatement.setInt(5, disco.getId());

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
    public boolean delete(Disco disco)
    {
        PreparedStatement preparedStatement = null;
        String query = "DELETE FROM tbl_disco WHERE ID = ?";
        int res = 0;
        List<Cancion> list = CancionJdbcImpl.getInstance().findByDiscoId(disco.getId());

        if(!list.isEmpty())
        {
            System.out.println("\n> No se puede eliminar el/los disco(s) porque tiene(n) las siguientes canciones asociadas: ");
            for(Cancion cancion: list)
            {
                System.out.println("- [ID: "+cancion.getId()+"], [NOMBRE: "+cancion.getTituloCancion()+"]");
            }

            System.out.print("> Desea eliminar también estas canciones? (S/N): ");
            String respuesta = ReadUtil.read();

            if(!respuesta.equalsIgnoreCase("S"))
            {
                System.out.println("> Eliminación cancelada.");
                return false;
            }

            for(Cancion cancion: list)
            {
                CancionJdbcImpl.getInstance().delete(cancion);
                System.out.println("> Canciones eliminadas.");
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
            preparedStatement.setInt(1, disco.getId());

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
    public Disco findById(Integer id)
    {
        Disco disco = null;
        String query = "SELECT tbl_disco.*, tbl_artista.ARTISTA AS ARTISTA, tbl_disquera.DISQUERA AS DISQUERA, tbl_genero_musical.GENERO AS GENERO " +
                "FROM tbl_disco " +
                "INNER JOIN tbl_artista ON tbl_disco.tbl_artista_id = tbl_artista.id " +
                "INNER JOIN tbl_disquera ON tbl_disco.tbl_disquera_id = tbl_disquera.id " +
                "INNER JOIN tbl_genero_musical on tbl_disco.tbl_genero_musical_id = tbl_genero_musical.id " +
                "WHERE tbl_disco.ID = ?;";
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
                disco = new Disco();
                disco.setId( resultSet.getInt("ID") );
                disco.setTituloDisco( resultSet.getString("TITULO") );
                disco.setPrecio( resultSet.getDouble("PRECIO") );
                disco.setExistencias( resultSet.getInt("EXISTENCIA") );
                disco.setDescuento( resultSet.getDouble("DESCUENTO") );

                Date date = resultSet.getDate("FECHA_LANZAMIENTO");
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd");
                String fechaFormateada = simpleDateFormat.format(date);

                disco.setFechaLanzamiento( fechaFormateada );
                disco.setImagen( resultSet.getString("IMAGEN") );
                disco.setArtista( new Artista(resultSet.getString("ARTISTA")) );
                disco.setDisquera( new Disquera(resultSet.getString("DISQUERA")) );
                disco.setGeneroMusical( new Genero_Musical(resultSet.getString("GENERO")));
            }

            preparedStatement.close();
            closeConnection();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            return null;
        }
        return disco;
    }

    public List<Disco> findByArtistaId(int artistaId)
    {
        String query = "SELECT tbl_disco.*, tbl_artista.ARTISTA AS ARTISTA, tbl_disquera.DISQUERA AS DISQUERA, tbl_genero_musical.GENERO AS GENERO " +
                "FROM tbl_disco " +
                "INNER JOIN tbl_artista ON tbl_disco.tbl_artista_id = tbl_artista.id " +
                "INNER JOIN tbl_disquera ON tbl_disco.tbl_disquera_id = tbl_disquera.id " +
                "INNER JOIN tbl_genero_musical on tbl_disco.tbl_genero_musical_id = tbl_genero_musical.id " +
                "WHERE tbl_artista.ID = ?;";
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<Disco> list = new ArrayList<>();
        try
        {
            if( !openConnection() )
            {
                return null;
            }
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, artistaId);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Disco disco = new Disco();
                disco.setId( resultSet.getInt("ID") );
                disco.setTituloDisco( resultSet.getString("TITULO") );
                disco.setPrecio( resultSet.getDouble("PRECIO") );
                disco.setExistencias( resultSet.getInt("EXISTENCIA") );
                disco.setDescuento( resultSet.getDouble("DESCUENTO") );

                Date date = resultSet.getDate("FECHA_LANZAMIENTO");
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd");
                String fechaFormateada = simpleDateFormat.format(date);

                disco.setFechaLanzamiento( fechaFormateada );
                disco.setImagen( resultSet.getString("IMAGEN") );
                disco.setArtista( new Artista(resultSet.getString("ARTISTA")) );
                disco.setDisquera( new Disquera(resultSet.getString("DISQUERA")) );
                disco.setGeneroMusical( new Genero_Musical(resultSet.getString("GENERO")));

                list.add(disco);
            }

            preparedStatement.close();
            closeConnection();
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return list;
    }

    public List<Disco> findByDisqueraId(int disqueraId)
    {
        String query = "SELECT tbl_disco.*, tbl_artista.ARTISTA AS ARTISTA, tbl_disquera.DISQUERA AS DISQUERA, tbl_genero_musical.GENERO AS GENERO " +
                "FROM tbl_disco " +
                "INNER JOIN tbl_artista ON tbl_disco.tbl_artista_id = tbl_artista.id " +
                "INNER JOIN tbl_disquera ON tbl_disco.tbl_disquera_id = tbl_disquera.id " +
                "INNER JOIN tbl_genero_musical on tbl_disco.tbl_genero_musical_id = tbl_genero_musical.id " +
                "WHERE tbl_disquera.ID = ?;";
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<Disco> list = new ArrayList<>();
        try
        {
            if( !openConnection() )
            {
                return null;
            }
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, disqueraId);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Disco disco = new Disco();
                disco.setId( resultSet.getInt("ID") );
                disco.setTituloDisco( resultSet.getString("TITULO") );
                disco.setPrecio( resultSet.getDouble("PRECIO") );
                disco.setExistencias( resultSet.getInt("EXISTENCIA") );
                disco.setDescuento( resultSet.getDouble("DESCUENTO") );

                Date date = resultSet.getDate("FECHA_LANZAMIENTO");
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd");
                String fechaFormateada = simpleDateFormat.format(date);

                disco.setFechaLanzamiento( fechaFormateada );
                disco.setImagen( resultSet.getString("IMAGEN") );
                disco.setArtista( new Artista(resultSet.getString("ARTISTA")) );
                disco.setDisquera( new Disquera(resultSet.getString("DISQUERA")) );
                disco.setGeneroMusical( new Genero_Musical(resultSet.getString("GENERO")));

                list.add(disco);
            }

            preparedStatement.close();
            closeConnection();
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return list;
    }

    public List<Disco> findByGenero_MusicalId(int generoId)
    {
        String query = "SELECT tbl_disco.*, tbl_artista.ARTISTA AS ARTISTA, tbl_disquera.DISQUERA AS DISQUERA, tbl_genero_musical.GENERO AS GENERO " +
                "FROM tbl_disco " +
                "INNER JOIN tbl_artista ON tbl_disco.tbl_artista_id = tbl_artista.id " +
                "INNER JOIN tbl_disquera ON tbl_disco.tbl_disquera_id = tbl_disquera.id " +
                "INNER JOIN tbl_genero_musical on tbl_disco.tbl_genero_musical_id = tbl_genero_musical.id " +
                "WHERE tbl_genero_musical.ID = ?;";
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<Disco> list = new ArrayList<>();
        try
        {
            if( !openConnection() )
            {
                return null;
            }
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, generoId);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Disco disco = new Disco();
                disco.setId( resultSet.getInt("ID") );
                disco.setTituloDisco( resultSet.getString("TITULO") );
                disco.setPrecio( resultSet.getDouble("PRECIO") );
                disco.setExistencias( resultSet.getInt("EXISTENCIA") );
                disco.setDescuento( resultSet.getDouble("DESCUENTO") );

                Date date = resultSet.getDate("FECHA_LANZAMIENTO");
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd");
                String fechaFormateada = simpleDateFormat.format(date);

                disco.setFechaLanzamiento( fechaFormateada );
                disco.setImagen( resultSet.getString("IMAGEN") );
                disco.setArtista( new Artista(resultSet.getString("ARTISTA")) );
                disco.setDisquera( new Disquera(resultSet.getString("DISQUERA")) );
                disco.setGeneroMusical( new Genero_Musical(resultSet.getString("GENERO")));

                list.add(disco);
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
