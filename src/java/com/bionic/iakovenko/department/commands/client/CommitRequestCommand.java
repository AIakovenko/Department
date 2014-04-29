
package com.bionic.iakovenko.department.commands.client;

import com.bionic.iakovenko.department.commands.ICommand;
import com.bionic.iakovenko.department.dao.entity.*;
import com.bionic.iakovenko.department.dao.factory.DAOFactory;
import com.bionic.iakovenko.department.dao.factory.DBDAOFactory;
import com.bionic.iakovenko.department.dao.factory.DbType;
import com.bionic.iakovenko.department.dao.interfaces.*;
import com.bionic.iakovenko.department.manager.PageManager;
import java.io.IOException;
import java.sql.Date;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


/**
 *
 * @autor Alex Iakovenko
 * Date: Apr 14, 2014
 * Time: 10:40:19 AM
 */
public class CommitRequestCommand implements ICommand{
    private final DBDAOFactory factory = DAOFactory.getFactory(DbType.MY_SQL);
    
    private final String ERROR_MESSAGE = "errorMessage";
    private final String PARAM_PERSON = "client";
    private final String PARAM_FLAT = "flat";
    private final String PARAM_WORKS= "works";
    private final String PARAM_DATE = "date";
    
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse respponse) 
            throws ServletException, IOException{
        String page;
        
        PageManager pageManager = PageManager.getInstance();  
        HttpSession session = request.getSession(false);        
        
        Person person = (Person)session.getAttribute(PARAM_PERSON);
        Flat flat = (Flat)session.getAttribute(PARAM_FLAT);
        Works works = (Works)session.getAttribute(PARAM_WORKS);
        Date date = (Date)session.getAttribute(PARAM_DATE);
                 
        Request clientRequest = new Request();
        clientRequest.setPersonID(person.getPersonID());
        clientRequest.setFlatID(flat.getFlatID());
        clientRequest.setWorksID(works.getWorksID());
        clientRequest.setRequestedTime(date);
        
        IRequest clientRequestDAO = factory.getRequestDAO();
        if(clientRequestDAO.insertRequest(clientRequest)){
            page = pageManager.getProperty(PageManager.COMMIT_REEQUEST_PATH);
        } else {
            request.setAttribute(ERROR_MESSAGE, "FAIL_REQUEST_HAS_NOT_BEEN_ADDED");
            page = pageManager.getProperty(PageManager.ERROR_PAGE_PATH);
        }
        return page;  
    }
    

}
