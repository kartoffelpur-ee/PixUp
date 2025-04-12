package org.floresmateo.sql.hibernateimpl;

import org.floresmateo.hibernate.HibernateUtil;
import org.floresmateo.model.Genero_Musical;
import org.floresmateo.model.Genero_Musical;
import org.floresmateo.sql.GenericSql;
import org.hibernate.Session;

import java.util.List;

public class Genero_MusicalHiberImpl implements GenericSql<Genero_Musical>
{
    private static Genero_MusicalHiberImpl generoMusicalHiber;

    private Genero_MusicalHiberImpl()
    {
    }

    public static Genero_MusicalHiberImpl getInstance()
    {
        if(generoMusicalHiber==null)
        {
            generoMusicalHiber = new Genero_MusicalHiberImpl();
        }
        return generoMusicalHiber;
    }

    @Override
    public List<Genero_Musical> findAll()
    {
        Session session = HibernateUtil.getSession();
        List<Genero_Musical> list = session
                .createQuery("FROM Genero_Musical", Genero_Musical.class)
                .getResultList();

        session.close();
        return list;
    }

    @Override
    public boolean save(Genero_Musical generoMusical)
    {
        Session session = HibernateUtil.getSession();
        session.beginTransaction();

        session.persist(generoMusical);
        session.getTransaction().commit();

        session.close();
        return true;
    }

    @Override
    public boolean update(Genero_Musical generoMusical)
    {
        Session session = HibernateUtil.getSession();
        session.beginTransaction();

        session.merge(generoMusical);
        session.getTransaction().commit();

        session.close();
        return true;
    }

    @Override
    public boolean delete(Genero_Musical generoMusical)
    {
        Session session = HibernateUtil.getSession();
        session.beginTransaction();

        session.remove(generoMusical);
        session.getTransaction().commit();

        session.close();
        return true;
    }

    @Override
    public Genero_Musical findById(Integer id)
    {
        Session session = HibernateUtil.getSession();
        Genero_Musical generoMusical = session
                .get( Genero_Musical.class, id );

        session.close();
        return generoMusical;
    }
}
