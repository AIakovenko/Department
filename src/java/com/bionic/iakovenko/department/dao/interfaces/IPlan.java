package com.bionic.iakovenko.department.dao.interfaces;

import com.bionic.iakovenko.department.dao.entity.Request;
import com.bionic.iakovenko.department.dao.entity.Worker;
import com.bionic.iakovenko.department.dao.entity.Plan;
import java.util.List;

/**
 * The interface provides for working with a table Working_plan.
 *
 * @autor Alex Iakovenko
 * Date: 3/31/14
 * Time: 11:32 AM
 */
public interface IPlan {
    /**
     * Selects from table Plan all notes.
     * @return list of Plan objects.
     */
    public List<Plan> findAll();

    /**
     * Selects from table Plan by worker id notes about request which worker must do or has done.
     * For implementation method must be taken worker ID value from Worker.
     * It can be done calling the method <code>getWorkerID()</> from Worker object.
     * @param worker        worker object;
     * @return              list of request objects.
     */
    public List<Request> findRequest(Worker worker);

    /**
     * Selects from table Plan by request id notes about workers which must do or have done this request.
     * For implementation method must be taken request ID value from Request.
     * It can be done calling the method <code>getRequestID()</> from Request object.
     * @param request       Request object;
     * @return              list of Worker objects.
     */
    public List<Worker> findWorker(Request request);

    /**
     * Inserts note about Plan to Plan table.
     * @param request       Request object,
     * @param worker        Worker object.
     * @return true     if note about working plan has been inserted.
     *         false    if the exception has thrown.
     */
    public boolean insertPlan(Request request, Worker worker);

    /**
     * Removes note about Plan from Plan table.
     * @param request       removed Request object,
     * @param worker        removed Worker object.
     * @return true     if note about working plan has been removed.
     *         false    if the exception has thrown.
     */
    public boolean deletePlan(Request request, Worker worker);

    /**
     * Removes note about Plan from Plan table by request id.
     * @param request       removed Request object,
     * @return true         number of rows which have been removed.
     *                      if no one row has been removed then would be
     *                      returned 0.
     */
    public int deletePlanByRequest(Request request);

    /**
     * Removes note about Plan from Plan table by worker id number.
     * @param worker        removed Worker object.
     * @return true         number of rows which have been removed.
     *                      if no one row has been removed then would be
     *                      returned 0.
     */
    public int deletePlanByWorker(Worker worker);

}
