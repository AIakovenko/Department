
package com.bionic.iakovenko.department.commands;

import com.bionic.iakovenko.department.manager.PageManager;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @autor Alex Iakovenko
 * Date: Apr 16, 2014
 * Time: 9:31:33 PM
 */
public class LogOutCommand implements ICommand{
    private final String PARAM_LOCALE = "locale";

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        String page;
        
        PageManager pageManager = PageManager.getInstance();
        HttpSession session = request.getSession(false);
        
        String locale = (String) session.getAttribute(PARAM_LOCALE);
        
        /* Write value of current locale as request atribute because
         * session will be invalidated.
         */
        request.setAttribute(PARAM_LOCALE, locale); 
        
        session.invalidate();
        
        page = pageManager.getProperty(PageManager.LOGIN_PAGE_PATH);
        return page; 
    }
    

}
