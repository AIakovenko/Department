package com.bionic.iakovenko.department.dao.interfaces;

import com.bionic.iakovenko.department.dao.entity.Worker;
import java.util.List;

/**
 * The interface provides for working with a table Worker.
 *
 * @autor Alex Iakovenko
 * Date: 3/31/14
 * Time: 11:23 AM
 */
public interface IWorker {
    /**
     * Selects from table Worker all notes.
     * @return list of Workers.
     */
    public List<Worker> findAll();

    /**
     * Selects from table Worker one note by id.
     * @param workerId          worker id number;
     * @return                  Worker object.
     */
    public Worker findWorker(short workerId);

    /**
     * Selects from table Worker one note by name.
     * @param name              worker's name;
     * @return                  Workers.
     */
    public List<Worker> findWorker(String name);

    /**
     * Inserts note about Worker to Worker table.
     * @param worker    worker who would be inserted.
     * @return true     if note about Worker has been inserted.
     *         false    if the exception has thrown.
     */
    public boolean insertWorker(Worker worker);

    /**
     * Removes note about worker to Worker table.
     * @param worker    worker who would be removed.
     * @return true     if note about Worker has been removed.
     *         false    if the exception has thrown.
     */
    public boolean deleteWorker(Worker worker);
}
