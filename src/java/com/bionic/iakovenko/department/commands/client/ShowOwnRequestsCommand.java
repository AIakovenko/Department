
package com.bionic.iakovenko.department.commands.client;

import com.bionic.iakovenko.department.commands.EntitySet;
import com.bionic.iakovenko.department.commands.ICommand;
import com.bionic.iakovenko.department.dao.entity.Dispatcher;
import com.bionic.iakovenko.department.dao.entity.Flat;
import com.bionic.iakovenko.department.dao.entity.Person;
import com.bionic.iakovenko.department.dao.entity.Request;
import com.bionic.iakovenko.department.dao.entity.Works;
import com.bionic.iakovenko.department.dao.factory.DAOFactory;
import com.bionic.iakovenko.department.dao.factory.DBDAOFactory;
import com.bionic.iakovenko.department.dao.factory.DbType;
import com.bionic.iakovenko.department.dao.interfaces.IRequest;
import com.bionic.iakovenko.department.logger.SingleLogger;
import com.bionic.iakovenko.department.manager.PageManager;
import java.io.IOException;
import java.util.List;
import java.util.Set;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;

/**
 *
 * @autor Alex Iakovenko
 * Date: Apr 25, 2014
 * Time: 5:29:14 PM
 */
public class ShowOwnRequestsCommand implements ICommand{
    
    private final Logger logger = SingleLogger.getInstance().getLog();
    private final DBDAOFactory factory = DAOFactory.getFactory(DbType.MY_SQL);
    private final String ERROR_MESSAGE = "errorMessage";
    private final String PARAM_CLIENT = "client";

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        String page;
        Person person;
        PageManager pageManager = PageManager.getInstance();
        
        HttpSession session = request.getSession(false);
 
        person = (Person)session.getAttribute(PARAM_CLIENT);
        try{
            IRequest requestDAO = factory.getRequestDAO();
            List<Request> clientRequestList = requestDAO.findRequestByPerson(person);
            request.setAttribute("clientRequestList", clientRequestList);
            
            Set<Flat> clientFlatList = EntitySet.getFlatList(clientRequestList);
            request.setAttribute("clientFlatList", clientFlatList);
            
            Set<Works> clientWorksList = EntitySet.getWorksList(clientRequestList);
            request.setAttribute("clientWorksList", clientWorksList);
            
            Set<Dispatcher> clientDispatcherList = EntitySet.getDispatcherList(clientRequestList);
            request.setAttribute("clientDispatcherList", clientDispatcherList);
            
           page = pageManager.getProperty(PageManager.SHOW_OWN_REQUESTS_PATH);
        } catch (Exception e){
            String message = "ILLEGAL_PARAMETER_EXCEPTION";
            logger.warn(message, e);
            request.setAttribute(ERROR_MESSAGE, message);
            page = pageManager.getProperty(PageManager.ERROR_PAGE_PATH);      
        }
        return page;
    }

}
