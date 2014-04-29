package com.bionic.iakovenko.department.commands.dispatcher;

import com.bionic.iakovenko.department.commands.ICommand;
import com.bionic.iakovenko.department.dao.entity.Request;
import com.bionic.iakovenko.department.dao.factory.DAOFactory;
import com.bionic.iakovenko.department.dao.factory.DBDAOFactory;
import com.bionic.iakovenko.department.dao.factory.DbType;
import com.bionic.iakovenko.department.dao.interfaces.IRequest;
import com.bionic.iakovenko.department.manager.PageManager;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @autor Alex Iakovenko
 * Date: Apr 16, 2014
 * Time: 8:51:30 PM
 */
public class ChoiceUndoneRequest implements ICommand{
    private final DBDAOFactory factory = DAOFactory.getFactory(DbType.MY_SQL);

    
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        String page;
        PageManager pageManager = PageManager.getInstance();

        
        IRequest requestDAO = factory.getRequestDAO();
        List<Request> req = requestDAO.findPreparedRequest();
        request.setAttribute("req", req);
         
        page = pageManager.getProperty(PageManager.CHOICE_UNDONE_REQUEST);
        return page;
    }
    
    
    

}
