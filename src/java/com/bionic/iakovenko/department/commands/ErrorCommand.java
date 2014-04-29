
package com.bionic.iakovenko.department.commands;

import com.bionic.iakovenko.department.manager.PageManager;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @autor Alex Iakovenko
 * Date: Apr 25, 2014
 * Time: 2:14:38 PM
 */
public class ErrorCommand implements ICommand{
    

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        String page;
        PageManager pageManager = PageManager.getInstance();
        page = pageManager.getProperty(PageManager.ERROR_PAGE_PATH);
        return page;
    }
    

}
