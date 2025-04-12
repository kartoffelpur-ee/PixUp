package org.floresmateo.sql.hibernateimpl;

import org.floresmateo.hibernate.HibernateUtil;
import org.floresmateo.model.Artista;
import org.floresmateo.sql.GenericSql;
import org.hibernate.Session;

import java.util.List;

public class ArtistaHiberImpl implements GenericSql<Artista>
{
    private static ArtistaHiberImpl artistaHiber;

    private ArtistaHiberImpl()
    {
    }
    
    public static ArtistaHiberImpl getInstance()
    {
        if(artistaHiber == null)
        {
             artistaHiber = new ArtistaHiberImpl();
        }
        return artistaHiber;
    }

    @Override
    public List<Artista> findAll()
    {
        Session session = HibernateUtil.getSession();
        List<Artista> list = session
                .createQuery("FROM Artista", Artista.class)
                .getResultList();

        session.close();
        return list;
    }

    @Override
    public boolean save(Artista artista)
    {
        Session session = HibernateUtil.getSession();
        session.beginTransaction();

        session.persist(artista);
        session.getTransaction().commit();

        session.close();
        return true;
    }

    @Override
    public boolean update(Artista artista)
    {
        Session session = HibernateUtil.getSession();
        session.beginTransaction();

        session.merge(artista);
        session.getTransaction().commit();

        session.close();
        return true;
    }

    @Override
    public boolean delete(Artista artista)
    {
        Session session = HibernateUtil.getSession();
        session.beginTransaction();

        session.remove(artista);
        session.getTransaction().commit();

        session.close();
        return true;
    }

    @Override
    public Artista findById(Integer id)
    {
        Session session = HibernateUtil.getSession();
        Artista artista = session
                .get( Artista.class, id );

        session.close();
        return artista;
    }
}
