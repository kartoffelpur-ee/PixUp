package org.floresmateo.jdbc;

import java.util.List;

public interface GenericJdbc<T>
{
    List<T> findAll();
}
