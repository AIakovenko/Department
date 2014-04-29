package com.bionic.iakovenko.department.commands.client;

import com.bionic.iakovenko.department.commands.ICommand;
import com.bionic.iakovenko.department.dao.entity.Flat;
import com.bionic.iakovenko.department.dao.entity.Person;
import com.bionic.iakovenko.department.dao.entity.Works;
import com.bionic.iakovenko.department.dao.factory.DAOFactory;
import com.bionic.iakovenko.department.dao.factory.DBDAOFactory;
import com.bionic.iakovenko.department.dao.factory.DbType;
import com.bionic.iakovenko.department.dao.interfaces.IOwner;
import com.bionic.iakovenko.department.dao.interfaces.IWorks;
import com.bionic.iakovenko.department.logger.SingleLogger;
import com.bionic.iakovenko.department.manager.PageManager;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;

/**
 * Class describes command to prepare information for creating a request by 
 * owner(person).
 * 
 * @autor Alex Iakovenko
 * Date: 4/13/14
 * Time: 12:16 PM
 */
public class PrepareRequestCommand implements ICommand {

    private final String ERROR_MESSAGE = "errorMessage";
    private final Logger logger = SingleLogger.getInstance().getLog();
    private final DBDAOFactory factory = DAOFactory.getFactory(DbType.MY_SQL);
    
    private final String PARAM_PERSON = "client";

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        Person person;
        String page;
        
        PageManager pageManager = PageManager.getInstance();
        
        HttpSession session = request.getSession(false);
         
        try{
            person = (Person)session.getAttribute(PARAM_PERSON);
            IOwner ownerDAO = factory.getOwnerDAO();
            List<Flat> flatList = ownerDAO.findFlat(person);
            request.setAttribute("flatList", flatList);

            IWorks worksDAO = factory.getWorksDAO();
            List<Works> worksList = worksDAO.findAll();
            request.setAttribute("worksList", worksList);

            page = pageManager.getProperty(PageManager.PREPARE_REQUEST_PATH);
            
        } catch (Exception e){
            String message = "LOGIN_ERROR_MESSAGE";
            logger.warn(message, e);
            request.setAttribute(ERROR_MESSAGE, message);
            page = pageManager.getProperty(PageManager.ERROR_PAGE_PATH);
        }
        
        return page;
    }
}
