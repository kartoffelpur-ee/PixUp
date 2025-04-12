package org.floresmateo.sql.jdbcimpl;

import org.floresmateo.sql.Conexion;
import org.floresmateo.sql.GenericSql;
import org.floresmateo.model.*;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class CancionSqlImpl extends Conexion implements GenericSql<Cancion>
{
    private static CancionSqlImpl cancionJdbc;

    private CancionSqlImpl()
    {
        super();
    }

    public static CancionSqlImpl getInstance()
    {
        if(cancionJdbc==null)
        {
            cancionJdbc = new CancionSqlImpl();
        }
        return cancionJdbc;
    }

    @Override
    public List<Cancion> findAll()
    {
        Statement statement = null;
        ResultSet resultSet = null;
        Cancion cancion = null;
        List<Cancion> list = null;
        String sql = "SELECT tbl_cancion.*, " +
                    "tbl_disco.*, tbl_artista.ARTISTA, tbl_disquera.DISQUERA, tbl_genero_musical.GENERO FROM tbl_cancion " +
                "INNER JOIN tbl_disco ON tbl_cancion.tbl_disco_id = tbl_disco.id " +
                "INNER JOIN tbl_artista ON tbl_disco.tbl_artista_id = tbl_artista.id " +
                "INNER JOIN tbl_disquera ON tbl_disco.tbl_disquera_id = tbl_disquera.id " +
                "INNER JOIN tbl_genero_musical ON tbl_disco.tbl_genero_musical_id = tbl_genero_musical.id" +
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
                cancion = new Cancion();
                cancion.setId( resultSet.getInt(1) );
                cancion.setTituloCancion( resultSet.getString(2) );

                Time duracion = resultSet.getTime("DURACION");

                if (duracion != null) {
                    long milisegundos = duracion.getTime();
                    double minutos = (double) milisegundos / 60000;
                    cancion.setDuracion(minutos);
                } else {
                    cancion.setDuracion(0.0);
                }

                cancion.setDuracion(0);

                Disco disco = new Disco();
                disco.setId(resultSet.getInt(5 ));
                disco.setTituloDisco(resultSet.getString(6));
                disco.setPrecio(resultSet.getDouble("PRECIO"));
                disco.setExistencias(resultSet.getInt("EXISTENCIA"));
                disco.setDescuento(resultSet.getDouble("DESCUENTO"));

                Date date = resultSet.getDate("FECHA_LANZAMIENTO");
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd");
                String fechaFormateada = simpleDateFormat.format(date);
                disco.setFechaLanzamiento(fechaFormateada);

                disco.setImagen(resultSet.getString("IMAGEN"));
                disco.setDisquera(new Disquera(resultSet.getString("DISQUERA")));
                disco.setArtista(new Artista(resultSet.getString("ARTISTA")));
                disco.setGeneroMusical(new Genero_Musical(resultSet.getString("GENERO")));

                cancion.setDisco( disco );

                list.add(cancion);
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
    public boolean save(Cancion cancion)
    {
        PreparedStatement preparedStatement = null;
        String query = "INSERT INTO tbl_cancion (TITULO, DURACION, TBL_DISCO_ID) VALUES ( ?, ?, ? )";
        int res = 0;

        try
        {
            if( !openConnection() )
            {
                System.out.println("> Error de conexión.");
                return false;
            }
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, cancion.getTituloCancion());

            double duracionEnMinutos = cancion.getDuracion();  // ej. 4.5
            // Convertimos los minutos a milisegundos
            long duracionMs = (long)(duracionEnMinutos * 60 * 1000);
            // Creamos el objeto Time con 00:00 + duración
            Time duracionTime = new Time(duracionMs);

            preparedStatement.setTime(2, duracionTime);
            preparedStatement.setInt(3, cancion.getDisco().getId());

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
    public boolean update(Cancion cancion)
    {
        PreparedStatement preparedStatement = null;
        String query = "UPDATE tbl_cancion SET TITULO = ? WHERE ID = ?";
        int res = 0;

        try
        {
            if( !openConnection() )
            {
                System.out.println("> Error de conexión.");
                return false;
            }
            preparedStatement = connection.prepareStatement(query);

            preparedStatement.setString(1, cancion.getTituloCancion());
            preparedStatement.setInt(2, cancion.getId());

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
    public boolean delete(Cancion cancion)
    {
        PreparedStatement preparedStatement = null;
        String query = "DELETE FROM tbl_cancion WHERE ID = ?";
        int res = 0;

        try
        {
            if( !openConnection() )
            {
                System.out.println("> Error de conexión.");
                return false;
            }
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, cancion.getId());

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
    public Cancion findById(Integer id)
    {
        Cancion cancion = null;
        String query = "SELECT tbl_cancion.ID, tbl_cancion.TITULO as CANCION, tbl_cancion.DURACION, tbl_cancion.TBL_DISCO_ID, " +
                "tbl_disco.*, tbl_artista.ARTISTA, tbl_disquera.DISQUERA, tbl_genero_musical.GENERO FROM tbl_cancion " +
                "INNER JOIN tbl_disco ON tbl_cancion.tbl_disco_id = tbl_disco.id " +
                "INNER JOIN tbl_artista ON tbl_disco.tbl_artista_id = tbl_artista.id " +
                "INNER JOIN tbl_disquera ON tbl_disco.tbl_disquera_id = tbl_disquera.id " +
                "INNER JOIN tbl_genero_musical ON tbl_disco.tbl_genero_musical_id = tbl_genero_musical.id " +
                "WHERE tbl_cancion.ID = ?;";
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
                cancion = new Cancion();
                cancion.setId( resultSet.getInt(1) );
                cancion.setTituloCancion( resultSet.getString("TITULO") );

                Date date = resultSet.getDate("FECHA_LANZAMIENTO");
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd");
                String fechaFormateada = simpleDateFormat.format(date);

                Disco disco = new Disco(
                        resultSet.getString("TITULO"),
                        resultSet.getDouble("PRECIO"),
                        resultSet.getInt("EXISTENCIA"),
                        resultSet.getDouble("DESCUENTO"),
                        fechaFormateada,
                        resultSet.getString("IMAGEN"),
                        new Disquera(resultSet.getString("DISQUERA")),
                        new Artista(resultSet.getString("ARTISTA")),
                        new Genero_Musical(resultSet.getString("GENERO"))
                );

                cancion.setDisco( disco );
            }

            preparedStatement.close();
            closeConnection();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            return null;
        }
        return cancion;
    }

    public List<Cancion> findByDiscoId(int discoId)
    {
        String query = "SELECT tbl_cancion.ID, tbl_cancion.TITULO as CANCION, tbl_cancion.DURACION, tbl_cancion.TBL_DISCO_ID, " +
                "tbl_disco.*, tbl_artista.ARTISTA, tbl_disquera.DISQUERA, tbl_genero_musical.GENERO FROM tbl_cancion " +
                "INNER JOIN tbl_disco ON tbl_cancion.tbl_disco_id = tbl_disco.id " +
                "INNER JOIN tbl_artista ON tbl_disco.tbl_artista_id = tbl_artista.id " +
                "INNER JOIN tbl_disquera ON tbl_disco.tbl_disquera_id = tbl_disquera.id " +
                "INNER JOIN tbl_genero_musical ON tbl_disco.tbl_genero_musical_id = tbl_genero_musical.id " +
                "WHERE tbl_disco.ID = ?;";
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<Cancion> list = new ArrayList<>();

        try
        {
            if( !openConnection() )
            {
                return null;
            }
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, discoId);

            resultSet = preparedStatement.executeQuery();

            while(resultSet.next())
            {
                Cancion cancion = new Cancion();
                cancion.setId( resultSet.getInt(1) );
                cancion.setTituloCancion( resultSet.getString("TITULO") );

                Date date = resultSet.getDate("FECHA_LANZAMIENTO");
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd");
                String fechaFormateada = simpleDateFormat.format(date);

                Disco disco = new Disco(
                        resultSet.getString("TITULO"),
                        resultSet.getDouble("PRECIO"),
                        resultSet.getInt("EXISTENCIA"),
                        resultSet.getDouble("DESCUENTO"),
                        fechaFormateada,
                        resultSet.getString("IMAGEN"),
                        new Disquera(resultSet.getString("DISQUERA")),
                        new Artista(resultSet.getString("ARTISTA")),
                        new Genero_Musical(resultSet.getString("GENERO"))
                );

                cancion.setDisco( disco );
                list.add(cancion);
            }

            preparedStatement.close();
            closeConnection();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            return null;
        }
        return list;
    }
}
