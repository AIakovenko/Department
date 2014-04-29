package com.bionic.iakovenko.department.commands;

import com.bionic.iakovenko.department.dao.entity.*;
import com.bionic.iakovenko.department.dao.factory.DAOFactory;
import com.bionic.iakovenko.department.dao.factory.DBDAOFactory;
import com.bionic.iakovenko.department.dao.factory.DbType;
import com.bionic.iakovenko.department.dao.interfaces.*;
import com.bionic.iakovenko.department.logger.SingleLogger;
import com.bionic.iakovenko.department.manager.PageManager;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;

/**
 * @autor Alex Iakovenko Date: 4/7/14 Time: 11:22 AM
 */
public class LoginCommand implements ICommand {

    private final Logger logger = SingleLogger.getInstance().getLog();
    private final DBDAOFactory factory = DAOFactory.getFactory(DbType.MY_SQL);

    private final String PARAM_ERROR_MESSAGE = "errorMessage";
    private final String PARAM_LOGIN = "login";
    private final String PARAM_PASSWORD = "password";
    private final String LOGIN_ERROR_MESSAGE = "LOGIN_ERROR_MESSAGE";

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String page;
        String login;
        String password;

        PageManager pageManager = PageManager.getInstance();
        HttpSession session = request.getSession(true);
        
        login = request.getParameter(PARAM_LOGIN);
        password = request.getParameter(PARAM_PASSWORD);

        if (login == null) {
            login = (String) session.getAttribute(PARAM_LOGIN);
            password = (String) session.getAttribute(PARAM_PASSWORD);
        }
        try {
            if (login == null || password == null) {
                throw new Exception(LOGIN_ERROR_MESSAGE);
            }
            IUsers usersDAO = factory.getUsersDAO();
            Users user = usersDAO.findUser(login);
            String userLogin = user.getLogin();
            String userPassword = user.getPassword();
            Byte groupID = user.getGroup_ID();
            
            if (login.equals(userLogin) && password.equals(userPassword)) {
                switch (groupID) {
                    case IGroups.CLIENTS:
                        IPerson personDAO = factory.getPersonDAO();
                        Person person = personDAO.findPersonByLogin(login);

                        session.setAttribute(PARAM_LOGIN, login);
                        session.setAttribute(PARAM_PASSWORD, password);
                        session.setAttribute("client", person);
                        session.setAttribute("groupID", groupID);
                        page = pageManager.getProperty(PageManager.MAIN_CLIENT_PAGE_PATH);
                        break;

                    case IGroups.DISPATCHERS:

                        IDispatcher dispatcherDAO = factory.getDispatcherDAO();
                        Dispatcher dispatcher = dispatcherDAO.findDispatchersByLogin(login);
                        session.setAttribute(PARAM_LOGIN, login);
                        session.setAttribute(PARAM_PASSWORD, password);
                        session.setAttribute("disp", dispatcher);
                        session.setAttribute("groupID", groupID);
                        page = pageManager.getProperty(PageManager.MAIN_DISPATCHER_PAGE_PATH);
                        break;

                    default:
                        throw new Exception(LOGIN_ERROR_MESSAGE);
                }
            } else {
                throw new Exception(LOGIN_ERROR_MESSAGE);
            }
        } catch (Exception e) {
            String message = e.getMessage();
            if (message == null) {
                message = e.toString();
            }
            logger.error(message, e);
            request.setAttribute(PARAM_ERROR_MESSAGE, message);
            page = pageManager.getProperty(PageManager.ERROR_PAGE_PATH);
        }
        return page;
    }
}
