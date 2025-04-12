package org.floresmateo.sql.hibernateimpl;

import org.floresmateo.hibernate.HibernateUtil;
import org.floresmateo.model.Municipio;
import org.floresmateo.sql.GenericSql;
import org.hibernate.Session;

import java.util.List;

public class MunicipioHiberImpl implements GenericSql<Municipio>
{
    private static MunicipioHiberImpl municipioHiber;

    private MunicipioHiberImpl()
    {
    }

    public static MunicipioHiberImpl getInstance()
    {
        if( municipioHiber==null )
        {
            municipioHiber = new MunicipioHiberImpl();
        }
        return municipioHiber;
    }

    @Override
    public List<Municipio> findAll()
    {
        Session session = HibernateUtil.getSession();
        List<Municipio> list = session
                .createQuery("FROM Municipio", Municipio.class)
                .getResultList();

        session.close();
        return list;
    }

    @Override
    public boolean save(Municipio municipio)
    {
        Session session = HibernateUtil.getSession();
        session.beginTransaction();

        session.persist(municipio);
        session.getTransaction().commit();

        session.close();
        return true;
    }

    @Override
    public boolean update(Municipio municipio)
    {
        Session session = HibernateUtil.getSession();
        session.beginTransaction();

        session.merge(municipio);
        session.getTransaction().commit();

        session.close();
        return true;
    }

    @Override
    public boolean delete(Municipio municipio)
    {
        Session session = HibernateUtil.getSession();
        session.beginTransaction();

        session.remove(municipio);
        session.getTransaction().commit();

        session.close();
        return true;
    }

    @Override
    public Municipio findById(Integer id)
    {
        Session session = HibernateUtil.getSession();
        Municipio municipio = session
                .get( Municipio.class, id );

        session.close();
        return municipio;
    }
}
