package com.bionic.iakovenko.department.commands;

import com.bionic.iakovenko.department.dao.entity.Flat;
import com.bionic.iakovenko.department.dao.entity.Person;
import com.bionic.iakovenko.department.dao.entity.Users;
import com.bionic.iakovenko.department.dao.factory.DAOFactory;
import com.bionic.iakovenko.department.dao.factory.DBDAOFactory;
import com.bionic.iakovenko.department.dao.factory.DbType;
import com.bionic.iakovenko.department.dao.interfaces.IFlat;
import com.bionic.iakovenko.department.dao.interfaces.IGroups;
import com.bionic.iakovenko.department.dao.interfaces.IOwner;
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
 * @autor Alex Iakovenko Date: Apr 27, 2014 Time: 11:40:04 AM
 */
public class RegistrationCommitCommand implements ICommand {

    private final Logger logger = SingleLogger.getInstance().getLog();
    private final DBDAOFactory factory = DAOFactory.getFactory(DbType.MY_SQL);

    private final String PARAM_ERROR_MESSAGE = "errorMessage";
    private final String PARAM_LOCALE = "locale";
    private final String PARAM_LOGIN = "login";
    private final String PARAM_PASSWORD = "password";
    private final String PARAM_PASSPORT_ID = "id";
    private final String PARAM_FAMILY_NAME = "familyName";
    private final String PARAM_GIVEN_NAME = "givenName";
    private final String PARAM_ADDITIONAL_NAME = "additionalName";
    private final String PARAM_ADDRESS = "address";
    private final String PARAM_BUILDING = "building";
    private final String PARAM_APARTMENT = "apartment";
    private final byte GROUP_ID = IGroups.CLIENTS;

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String page;
        String locale;

        Users newUser;
        Person newPerson;
        Flat newFlat;

        PageManager pageManager = PageManager.getInstance();
        HttpSession session = request.getSession();

        try {

            newUser = new Users();
            newUser.setGroup_ID(GROUP_ID);
            newUser.setLogin((String)session.getAttribute(PARAM_LOGIN));
            newUser.setPassword((String) session.getAttribute(PARAM_PASSWORD));

            newPerson = new Person();
            newPerson.setPersonID((String) session.getAttribute(PARAM_PASSPORT_ID));
            newPerson.setFamilyName((String) session.getAttribute(PARAM_FAMILY_NAME));
            newPerson.setGivenName((String) session.getAttribute(PARAM_GIVEN_NAME));
            newPerson.setAdditionalName((String) session.getAttribute(PARAM_ADDITIONAL_NAME));
            newPerson.setLogin((String) session.getAttribute(PARAM_LOGIN));

            newFlat = new Flat();
            newFlat.setAddress((String) session.getAttribute(PARAM_ADDRESS));
            newFlat.setBuilding(Short.valueOf((String)session.getAttribute(PARAM_BUILDING)));
            newFlat.setApartment(Short.valueOf((String)session.getAttribute(PARAM_APARTMENT)));
            short flatID = getFlatID(newFlat);
            newFlat.setFlatID(flatID);

            IUsers usersDAO = factory.getUsersDAO();
            if (!usersDAO.insertUser(newUser)) {
                throw new Exception("FAIL_USER_HAS_NOT_BEEN_ADDED");
            }

            IPerson personDAO = factory.getPersonDAO();
            if (!personDAO.insertPerson(newPerson)) {
                usersDAO.deleteUser(newUser);
                throw new Exception("FAIL_USER_HAS_NOT_BEEN_ADDED");
            }

            IFlat flatDAO = factory.getFlatDAO();
            if (!flatDAO.insertFlat(newFlat)) {
                usersDAO.deleteUser(newUser);
                personDAO.deletePerson(newPerson);
                throw new Exception("FAIL_ADDRESS_HAS_NOT_BEEN_ADDED");
            }
            IOwner ownerDAO = factory.getOwnerDAO();
            if (!ownerDAO.insertOwner(newPerson, newFlat)) {
                usersDAO.deleteUser(newUser);
                personDAO.deletePerson(newPerson);
                flatDAO.deleteFlat(newFlat);
                throw new Exception("FAIL_DATA_HAS_NOT_BEEN_ADDED");
            }

            page = pageManager.getProperty(PageManager.REGISTRATION_COMMIT_PATH);
        } catch (Exception e) {
            String message = e.getMessage();
            logger.warn(message, e);
            request.setAttribute(PARAM_ERROR_MESSAGE, message);
            page = pageManager.getProperty(PageManager.ERROR_PAGE_PATH);
        }
        locale = (String)session.getAttribute(PARAM_LOCALE);
        request.setAttribute(PARAM_LOCALE, locale);
        session.invalidate();
        return page;
    }

    private short getFlatID(Flat flat) {
        IFlat flatDAO = factory.getFlatDAO();
        Flat f = flatDAO.findFlat(flat.getAddress(), flat.getBuilding(), flat.getApartment());
        if (f != null) {
            return f.getFlatID();
        }
        f = flatDAO.findLastFlat();
        short id = f.getFlatID();
        return ++id;
    }

}
