package com.bionic.iakovenko.department.dao.interfaces;

import com.bionic.iakovenko.department.dao.entity.Dispatcher;
import com.bionic.iakovenko.department.dao.entity.Flat;
import com.bionic.iakovenko.department.dao.entity.Person;
import com.bionic.iakovenko.department.dao.entity.Request;
import com.bionic.iakovenko.department.dao.entity.Works;
import java.sql.Date;
import java.util.List;

/**
 * The interface provides for working with a table Request.
 *
 * @autor Alex Iakovenko
 * Date: 3/31/14
 * Time: 12:11 PM
 */
public interface IRequest {

    /**
     * Option describes all notes to have the date less then demanded.
     */
    public static final int LESS = -2;

    /**
     * Option describes all notes to have the date less then demanded or equal.
     */
    public static final int LESS_OR_EQUAL = -1;

    /**
     * Option describes all notes to have the date equal with demanded.
     */
    public static final int EQUAL = 0;

    /**
     * Option describes all notes to have the date more then demanded.
     */
    public static final int MORE = 1;

    /**
     * Option describes all notes to have the date more then demanded or equal.
     */
    public static final int MORE_OR_EQUAL = 2;

    /**
     * Selects from table Request all notes.
     * @return list of Request objects.
     */
    public List<Request> findAll();

    /**
     * Selects from table Request note by id.
     * @param requestId     number of the request;
     * @return              demanded request.
     */
    public Request findRequest(int requestId);

    /**
     * Selects from table Request by person id notes about requests person has done.
     * For implementation method must be taken person ID value from Person.
     * It can be done calling the method <code>getPersonID()</> from Person object.
     * @param person      Person object;
     * @return              list of requests.
     */
    public List<Request> findRequestByPerson(Person person);

    /**
     * Selects from table Request by flat id notes about requests has been done for this flat.
     * For implementation method must be taken person ID value from Person.
     * It can be done calling the method <code>getFlatID()</> from Flat object.
     * @param flat        Flat object;
     * @return              list of requests.
     */
    public List<Request> findRequestByFlat(Flat flat);

    /**
     * Selects from table Request by works id notes about requests has been done with this works type.
     * For implementation method must be taken works ID value from Works.
     * It can be done calling the method <code>getWorksID()</> from Works object.
     * @param works         Works object;
     * @return              list of requests.
     */
    public List<Request> findRequestByWork(Works works);

    /**
     * Selects from table Request by requested time notes about requests has been done or will be planed.
     * @param requestedDate requested date;
     * @param option        one of the constants:
     *                      <code>LESS</code>,
     *                      <code>LESS_OR_EQUAL</code>,
     *                      <code>EQUAL</code>,
     *                      <code>MORE</code>,
     *                      <code>MORE_OR_EQUAL</code>;
     * @return              list of requests.
     */
    public List<Request> findRequestByDate(Date requestedDate, int option);

    /**
     * Selects from table Request a note having the biggest id number.
     * @return              Request
     */
    public Request findLastRequest();

    /**
     * Looking for requests which was prepared by a client.
     * @return              Requests.
     */
    public List<Request> findPreparedRequest();

    /**
     * Inserts note about Request to Request table.
     * @param request           Request which can be inserted.
     * @return true             if note about request has been inserted.
     *         false            if the exception has thrown.
     */
    public boolean insertRequest(Request request);
    
    /**
     * Updates information about dispatcher working this request on.
     *
     * @param request           Request which can be updated.
     * @param dispatcher        Dispatcher, who works request on;
     * @return                  true if note about request has been updated. false if the
     *                          exception has thrown.
     */
    public boolean updateRequestByDispatcher(Request request, Dispatcher dispatcher);

    /**
     * Removes note about Request from Request table.
     * @param request           Request which should be removed.
     * @return true             if note about request has been removed.
     *         false            if the exception has thrown.
     */
    public boolean deleteRequest(Request request);

}
