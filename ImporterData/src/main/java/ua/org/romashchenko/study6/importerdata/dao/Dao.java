/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.org.romashchenko.study6.importerdata.dao;

import java.util.List;

/**
 *
 * @author Christina Romashchenko
 */
public interface Dao<T> {

    public long insert(T object);

    public boolean multipleInsert(final List<T> objects);

    public List<T> findAll();

    public long getId(T object);

    public boolean isExist(T object);

    public T getById(long id);
}
