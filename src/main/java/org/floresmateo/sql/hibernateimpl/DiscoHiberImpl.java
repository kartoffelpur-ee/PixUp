package org.floresmateo.sql.hibernateimpl;

import org.floresmateo.hibernate.HibernateUtil;
import org.floresmateo.model.Disco;
import org.floresmateo.sql.GenericSql;
import org.hibernate.Session;

import java.util.List;

public class DiscoHiberImpl implements GenericSql<Disco>
{
    private static DiscoHiberImpl discoHiber;

    private DiscoHiberImpl()
    {
    }

    public static DiscoHiberImpl getInstance()
    {
        if(discoHiber==null)
        {
            discoHiber = new DiscoHiberImpl();
        }
        return discoHiber;
    }

    @Override
    public List<Disco> findAll()
    {
        Session session = HibernateUtil.getSession();
        List<Disco> list = session
                .createQuery("FROM Disco", Disco.class)
                .getResultList();

        session.close();
        return list;
    }

    @Override
    public boolean save(Disco disco)
    {
        Session session = HibernateUtil.getSession();
        session.beginTransaction();

        session.persist(disco);
        session.getTransaction().commit();

        session.close();
        return true;
    }

    @Override
    public boolean update(Disco disco)
    {
        Session session = HibernateUtil.getSession();
        session.beginTransaction();

        session.merge(disco);
        session.getTransaction().commit();

        session.close();
        return true;
    }

    @Override
    public boolean delete(Disco disco)
    {
        Session session = HibernateUtil.getSession();
        session.beginTransaction();

        session.remove(disco);
        session.getTransaction().commit();

        session.close();
        return true;
    }

    @Override
    public Disco findById(Integer id)
    {
        Session session = HibernateUtil.getSession();
        Disco disco = session
                .get( Disco.class, id );

        session.close();
        return disco;
    }
}
