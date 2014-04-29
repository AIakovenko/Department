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
 * Date: Apr 23, 2014
 * Time: 11:04:55 AM
 */
public class WellDoneCommand implements ICommand{
    private final String PARAM_GROUP_ID = "groupID";

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String page;
        
        PageManager pageManager = PageManager.getInstance();
        HttpSession session = request.getSession();
        
        Byte groupID = (Byte)session.getAttribute(PARAM_GROUP_ID);
        
        switch(groupID){
            case IGroups.CLIENTS:
                page = pageManager.getProperty(PageManager.COMMIT_REEQUEST_PATH);
                break;
            case IGroups.DISPATCHERS:
                page = pageManager.getProperty(PageManager.COMMIT_WORK_GROUP_PATH);
                break;
            default:
                page = pageManager.getProperty(PageManager.ERROR_PAGE_PATH);
        }
        return page;
    }
    

}
