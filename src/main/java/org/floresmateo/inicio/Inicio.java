package org.floresmateo.inicio;

import org.floresmateo.hibernate.HibernateUtil;
import org.floresmateo.vista.SeleccionEjecutable;

public class Inicio
{
    public static void main(String[] args)
    {
        System.out.println("\t<:: PixUp ::>");
        HibernateUtil.getSessionFactory(); // Se inicia Hibernate desde un principio
        SeleccionEjecutable.getInstance().run();
        System.out.println("\t¡Hasta pronto!");
    }
}