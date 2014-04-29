

package com.bionic.iakovenko.department.commands;

import com.bionic.iakovenko.department.dao.entity.Dispatcher;
import com.bionic.iakovenko.department.dao.entity.Flat;
import com.bionic.iakovenko.department.dao.entity.Person;
import com.bionic.iakovenko.department.dao.entity.Request;
import com.bionic.iakovenko.department.dao.entity.Works;
import com.bionic.iakovenko.department.dao.factory.DAOFactory;
import com.bionic.iakovenko.department.dao.factory.DBDAOFactory;
import com.bionic.iakovenko.department.dao.factory.DbType;
import com.bionic.iakovenko.department.dao.interfaces.IDispatcher;
import com.bionic.iakovenko.department.dao.interfaces.IFlat;
import com.bionic.iakovenko.department.dao.interfaces.IPerson;
import com.bionic.iakovenko.department.dao.interfaces.IWorks;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 *
 * @autor Alex Iakovenko
 * Date: Apr 25, 2014
 * Time: 9:25:45 PM
 */
public class EntitySet {
    private static final DBDAOFactory factory = DAOFactory.getFactory(DbType.MY_SQL);
    
    private EntitySet(){}
    
    public static Set<Person> getPersonList(List<Request> dispRequestList) {

        IPerson personDAO = factory.getPersonDAO();
        Set<Person> personSet = new HashSet<Person>();
        String personID;
        for (Request r : dispRequestList) {
            if (r != null) {
                personID = r.getPersonID();
                Person person = personDAO.findPerson(personID);
                personSet.add(person);
            }
        }
        return personSet;
    }
    public static Set<Flat> getFlatList(List<Request> dispRequestList) {

        IFlat flatDAO = factory.getFlatDAO();
        Set<Flat> flatSet = new HashSet<Flat>();
        short flatID;

        for (Request r : dispRequestList) {
            if (r != null) {
                flatID = r.getFlatID();
                Flat flat = flatDAO.findFlat(flatID);
                flatSet.add(flat);
            }
        }
        return flatSet;
    }

    public static Set<Works> getWorksList(List<Request> dispRequestList) {

        IWorks worksDAO = factory.getWorksDAO();
        Set<Works> worksSet = new HashSet<Works>();
        short worksID;
        for (Request r : dispRequestList) {
            if (r != null) {
                worksID = r.getWorksID();
                Works works = worksDAO.findWorks(worksID);
                worksSet.add(works);
            }
        }
        return worksSet;
    }

    public static Set<Dispatcher> getDispatcherList(List<Request> dispRequestList) {

        IDispatcher dispatcherDAO = factory.getDispatcherDAO();
        Set<Dispatcher> dispatcherSet = new HashSet<Dispatcher>();
        short dispatcherID;
        for (Request r : dispRequestList) {
            if (r != null) {
                dispatcherID = r.getDispatcherID();
                Dispatcher dispatcher = dispatcherDAO.findDispatcher(dispatcherID);
                dispatcherSet.add(dispatcher);
            }
        }
        return dispatcherSet;
    }

}
