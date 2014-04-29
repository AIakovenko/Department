
package com.bionic.iakovenko.department.commands;

import com.bionic.iakovenko.department.dao.interfaces.IGroups;
import com.bionic.iakovenko.department.manager.PageManager;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @autor Alex Iakovenko
 * Date: Apr 21, 2014
 * Time: 8:09:06 PM
 */
public class GoMenuCommand implements ICommand{

    private final String PARAM_ERROR_MESSAGE = "errorMessage";
    private final String PARAM_GROUP_ID = "groupID";

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String page;
        
        PageManager pageManager = PageManager.getInstance();
        
        HttpSession session = request.getSession(false);
        
        Byte groupID =(Byte)session.getAttribute(PARAM_GROUP_ID);
        
        switch(groupID){
            case IGroups.CLIENTS:
                page = pageManager.getProperty(PageManager.MAIN_CLIENT_PAGE_PATH);
                break;
            case IGroups.DISPATCHERS:
                page = pageManager.getProperty(PageManager.MAIN_DISPATCHER_PAGE_PATH);
                break;
            default:
                request.setAttribute(PARAM_ERROR_MESSAGE, "LOGIN_ERROR_MESSAGE");
                page = pageManager.getProperty(PageManager.ERROR_PAGE_PATH);
                
        }
        return page;        
    }
    

}
