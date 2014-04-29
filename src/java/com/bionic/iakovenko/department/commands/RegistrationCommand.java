package com.bionic.iakovenko.department.commands;

import com.bionic.iakovenko.department.manager.PageManager;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


/**
 *
 * @autor Alex Iakovenko Date: Apr 26, 2014 Time: 2:18:29 PM
 */
public class RegistrationCommand implements ICommand {

    private final String PARAM_LOCALE = "locale";
    private final String PARAM_LOGIN = "login";
    private final String PARAM_PASSWORD = "password";

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String page;
        String login;
        String password;

        PageManager pageManager = PageManager.getInstance();

        HttpSession session = request.getSession(false);
        if (session != null) {
            String locale = (String)session.getAttribute(PARAM_LOCALE);
            request.setAttribute(PARAM_LOCALE, locale);
            session.invalidate();
            session = request.getSession(true);
        } else {
            session = request.getSession(true);
        }

        login = request.getParameter(PARAM_LOGIN);
        password = request.getParameter(PARAM_PASSWORD);
        session.setAttribute(PARAM_LOGIN, login);
        session.setAttribute(PARAM_PASSWORD, password);

        page = pageManager.getProperty(PageManager.REGISTRATION_PATH);

        return page;
    }

}
