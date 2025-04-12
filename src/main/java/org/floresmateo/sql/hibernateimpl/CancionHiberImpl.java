package org.floresmateo.sql.hibernateimpl;

import org.floresmateo.hibernate.HibernateUtil;
import org.floresmateo.model.Cancion;
import org.floresmateo.model.Cancion;
import org.floresmateo.sql.GenericSql;
import org.hibernate.Session;

import java.util.List;

public class CancionHiberImpl implements GenericSql<Cancion>
{
    private static CancionHiberImpl cancionHiber;

    private CancionHiberImpl()
    {
    }

    public static CancionHiberImpl getInstance()
    {
        if(cancionHiber==null)
        {
            cancionHiber = new CancionHiberImpl();
        }
        return cancionHiber;
    }

    @Override
    public List<Cancion> findAll()
    {
        Session session = HibernateUtil.getSession();
        List<Cancion> list = session
                .createQuery("FROM Cancion", Cancion.class)
                .getResultList();

        session.close();
        return list;
    }

    @Override
    public boolean save(Cancion cancion)
    {
        Session session = HibernateUtil.getSession();
        session.beginTransaction();

        session.persist(cancion);
        session.getTransaction().commit();

        session.close();
        return true;
    }

    @Override
    public boolean update(Cancion cancion)
    {
        Session session = HibernateUtil.getSession();
        session.beginTransaction();

        session.merge(cancion);
        session.getTransaction().commit();

        session.close();
        return true;
    }

    @Override
    public boolean delete(Cancion cancion)
    {
        Session session = HibernateUtil.getSession();
        session.beginTransaction();

        session.remove(cancion);
        session.getTransaction().commit();

        session.close();
        return true;
    }

    @Override
    public Cancion findById(Integer id)
    {
        Session session = HibernateUtil.getSession();
        Cancion cancion = session
                .get( Cancion.class, id );

        session.close();
        return cancion;
    }
}
