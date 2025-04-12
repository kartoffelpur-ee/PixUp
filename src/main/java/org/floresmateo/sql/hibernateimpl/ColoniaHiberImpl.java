package org.floresmateo.sql.hibernateimpl;

import org.floresmateo.hibernate.HibernateUtil;
import org.floresmateo.model.Colonia;
import org.floresmateo.sql.GenericSql;
import org.hibernate.Session;

import java.util.List;

public class ColoniaHiberImpl implements GenericSql<Colonia>
{
    private static ColoniaHiberImpl coloniaHiber;

    private ColoniaHiberImpl()
    {
    }

    public static ColoniaHiberImpl getInstance()
    {
        if(coloniaHiber==null)
        {
            coloniaHiber = ColoniaHiberImpl.getInstance();
        }
        return coloniaHiber;
    }

    @Override
    public List<Colonia> findAll()
    {
        Session session = HibernateUtil.getSession();
        List<Colonia> list = session
                .createQuery("FROM Colonia", Colonia.class)
                .getResultList();

        session.close();
        return list;
    }

    @Override
    public boolean save(Colonia colonia)
    {
        Session session = HibernateUtil.getSession();
        session.beginTransaction();

        session.persist(colonia);
        session.getTransaction().commit();

        session.close();
        return true;
    }

    @Override
    public boolean update(Colonia colonia)
    {
        Session session = HibernateUtil.getSession();
        session.beginTransaction();

        session.merge(colonia);
        session.getTransaction().commit();

        session.close();
        return true;
    }

    @Override
    public boolean delete(Colonia colonia)
    {
        Session session = HibernateUtil.getSession();
        session.beginTransaction();

        session.remove(colonia);
        session.getTransaction().commit();

        session.close();
        return true;
    }

    @Override
    public Colonia findById(Integer id)
    {
        Session session = HibernateUtil.getSession();
        Colonia colonia = session
                .get( Colonia.class, id );

        session.close();
        return colonia;
    }
}
