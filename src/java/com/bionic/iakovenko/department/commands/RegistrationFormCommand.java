package com.bionic.iakovenko.department.commands;

import com.bionic.iakovenko.department.dao.entity.Person;
import com.bionic.iakovenko.department.dao.entity.Users;
import com.bionic.iakovenko.department.dao.factory.DAOFactory;
import com.bionic.iakovenko.department.dao.factory.DBDAOFactory;
import com.bionic.iakovenko.department.dao.factory.DbType;
import com.bionic.iakovenko.department.dao.interfaces.IPerson;
import com.bionic.iakovenko.department.dao.interfaces.IUsers;
import com.bionic.iakovenko.department.logger.SingleLogger;
import com.bionic.iakovenko.department.manager.PageManager;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;

/**
 *
 * @autor Alex Iakovenko Date: Apr 28, 2014 Time: 9:52:35 AM
 */
public class RegistrationFormCommand implements ICommand {

    private final Logger logger = SingleLogger.getInstance().getLog();
    private final DBDAOFactory factory = DAOFactory.getFactory(DbType.MY_SQL);

    private final String PARAM_ERROR_MESSAGE = "errorMessage";
    private final String PARAM_LOGIN = "login";
    private final String PARAM_PASSWORD = "password";
    private final String PARAM_PASSWORD_AGAIN = "passwordAgain";
    private final String PARAM_PASSPORT_ID = "id";
    private final String PARAM_FAMILY_NAME = "familyName";
    private final String PARAM_GIVEN_NAME = "givenName";
    private final String PARAM_ADDITIONAL_NAME = "additionalName";
    private final String PARAM_ADDRESS = "address";
    private final String PARAM_BUILDING = "building";
    private final String PARAM_APARTMENT = "apartment";

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String page;
        String login;
        String password;
        String passwordAgain;

        PageManager pageManager = PageManager.getInstance();
        HttpSession session = request.getSession(false);

        login = request.getParameter(PARAM_LOGIN);
        if (login == null){
            login = (String)session.getAttribute(PARAM_LOGIN);
        }
        password = request.getParameter(PARAM_PASSWORD);
        if (password == null) {
            password = (String) session.getAttribute(PARAM_PASSWORD);
        }

        try {
            
            if (isLoginOccupied(login)) {
                throw new Exception("FAIL_LOGIN_IS_OCCUPIED");
            }

            passwordAgain = request.getParameter(PARAM_PASSWORD_AGAIN);
            if (passwordAgain == null) {
                passwordAgain = (String) session.getAttribute(PARAM_PASSWORD_AGAIN);
            }
            if (!password.equals(passwordAgain)) {
                throw new Exception("FAIL_PASSWORDS_ARE_NOT_EQUAL");
            }

            String id = request.getParameter(PARAM_PASSPORT_ID);
            if (id == null) {
                id = (String) session.getAttribute(PARAM_PASSPORT_ID);
            }
            if (isUserOccupied(id)) {
                throw new Exception("FAIL_USER_IS_OCCUPIED");

            }

            String familyName = request.getParameter(PARAM_FAMILY_NAME);
            if (familyName == null) {
                familyName = (String) session.getAttribute(PARAM_FAMILY_NAME);
            }

            String givenName = request.getParameter(PARAM_GIVEN_NAME);
            if (givenName == null) {
                givenName = (String) session.getAttribute(PARAM_GIVEN_NAME);
            }

            String additionalName = request.getParameter(PARAM_ADDITIONAL_NAME);
            if (additionalName == null) {
                additionalName = (String) session.getAttribute(PARAM_ADDITIONAL_NAME);
            }

            String address = request.getParameter(PARAM_ADDRESS);
            if (address == null) {
                address = (String) session.getAttribute(PARAM_ADDRESS);
            }

            String building = request.getParameter(PARAM_BUILDING);
            if (building == null) {
                building = (String) session.getAttribute(PARAM_BUILDING);
            }

            String apartment = request.getParameter(PARAM_APARTMENT);
            if (apartment == null) {
                apartment = (String) session.getAttribute(PARAM_APARTMENT);
            }
            
            session.setAttribute(PARAM_LOGIN, login);
            session.setAttribute(PARAM_PASSWORD, password);
            session.setAttribute(PARAM_PASSWORD_AGAIN, passwordAgain);
            session.setAttribute(PARAM_PASSPORT_ID, id);
            session.setAttribute(PARAM_FAMILY_NAME, familyName);
            session.setAttribute(PARAM_GIVEN_NAME, givenName);
            session.setAttribute(PARAM_ADDITIONAL_NAME, additionalName);
            session.setAttribute(PARAM_ADDRESS, address);
            session.setAttribute(PARAM_BUILDING, building);
            session.setAttribute(PARAM_APARTMENT, apartment);

            page = pageManager.getProperty(PageManager.REGISTRATION_CONFIRM_PATH);

        } catch (Exception e) {
            String message = e.getMessage();
            if (message == null){
                message = e.toString();
            }
            logger.error(e.getMessage(), e);
            request.setAttribute(PARAM_ERROR_MESSAGE, message);
            page = pageManager.getProperty(PageManager.ERROR_PAGE_PATH);
        }
        return page;
    }

    private boolean isLoginOccupied(String login) {
        IUsers usersDAO = factory.getUsersDAO();
        Users user = usersDAO.findUser(login);
        if (user != null) {
            return true;
        }
        return false;
    }

    private boolean isUserOccupied(String id) {
        IPerson personDAO = factory.getPersonDAO();
        Person p = personDAO.findPerson(id);
        if (p != null) {
            return true;
        }
        return false;
    }

}
