package org.floresmateo.vista;

import org.floresmateo.util.ReadUtil;

public abstract class LeerAcciones implements Ejecutable
{
    protected Integer opcion;
    protected boolean flag;

    public LeerAcciones( )
    {
        flag = true;
    }

    public abstract void despliegaMenu( );
    public abstract int valorMinMenu( );
    public abstract int valorMaxMenu( );
    public abstract void procesaOpcion( );

    @Override
    public void run( )
    {
        while (flag)
        {
            despliegaMenu();
            opcion = ReadUtil.readInt( );
            if (opcion >= valorMinMenu( ) && opcion <= valorMaxMenu( ) )
            {
                if( opcion == valorMaxMenu( ) )
                {
                    flag = false;
                }
                else
                {
                    procesaOpcion( );
                }
            }
            else
            {
                Menu.opcionInvalida();
            }
        }
    }

    public void setFlag(boolean flag)
    {
        this.flag = flag;
    }
}
