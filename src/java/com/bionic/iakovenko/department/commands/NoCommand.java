package com.bionic.iakovenko.department.commands;

import com.bionic.iakovenko.department.manager.PageManager;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @autor Alex Iakovenko Date: 4/10/14 Time: 10:52 PM
 */
public class NoCommand implements ICommand {

    private final String PARAM_GROUP_ID = "groupID";

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String page;

        PageManager pageManager = PageManager.getInstance();
        HttpSession session = request.getSession(false);
        if (session == null) {
            return pageManager.getProperty(PageManager.LOGIN_PAGE_PATH);
        }
        
        /*
        * If user has already passed autorization and hasn't done logout yet,
        * the session has to have an attribute <code>groupID</code>
        */
        Byte groupID = (Byte) session.getAttribute(PARAM_GROUP_ID);
        
        /* In case the next expression is true, user will avoid entering login
         * and password.
         */
        if (groupID != null) {
            page = pageManager.getProperty(PageManager.ENTER_PAGE_PATH);
        } else {
            page = pageManager.getProperty(PageManager.LOGIN_PAGE_PATH);
        } 
        return page;
    }
}
