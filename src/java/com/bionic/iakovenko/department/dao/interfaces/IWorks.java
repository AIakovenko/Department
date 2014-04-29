package com.bionic.iakovenko.department.dao.interfaces;

import com.bionic.iakovenko.department.dao.entity.Works;
import java.util.List;

/**
 * The interface provides for working with a table Works.
 *
 * @autor Alex Iakovenko
 * Date: 3/31/14
 * Time: 11:48 AM
 */
public interface IWorks {
    /**
     * Selects from table Works all notes.
     * @return list of Works.
     */
    public List<Works> findAll();

    /**
     * Selects from table Works one note by id.
     * @param worksId           work id number;
     * @return                  Works object.
     */
    public Works findWorks(short worksId);

    /**
     * Selects from table Works one note by name.
     * @param name              name of works;
     * @return                  Works object.
     */
    public List<Works> findWorks(String name);

    /**
     * Inserts note about Works to Works table.
     * @param works     the work which would be inserted;
     * @return true     if note about Works has been inserted.
     *         false    if the exception has thrown.
     */
    public boolean insertWorks(Works works);

    /**
     * Removes note about works from Works table.
     * @param works     the work which would be removed;
     * @return true     if note about Works has been removed.
     *         false    if the exception has thrown.
     */
    public boolean deleteWorks(Works works);
}
