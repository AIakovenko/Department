package com.bionic.iakovenko.department.commands.dispatcher;

import com.bionic.iakovenko.department.commands.ICommand;
import com.bionic.iakovenko.department.dao.entity.Request;
import com.bionic.iakovenko.department.dao.factory.DAOFactory;
import com.bionic.iakovenko.department.dao.factory.DBDAOFactory;
import com.bionic.iakovenko.department.dao.factory.DbType;
import com.bionic.iakovenko.department.dao.interfaces.IRequest;
import com.bionic.iakovenko.department.manager.PageManager;
import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


/**
 *
 * @autor Alex Iakovenko Date: Apr 19, 2014 Time: 11:43:05 PM
 */
public class SearchRequest implements ICommand {

    private final DBDAOFactory factory = DAOFactory.getFactory(DbType.MY_SQL);
    
    private final String PARAM_NEW_SEARCH = "newSearch";
    private final String PARAM_SEARCHING_REQUEST_ID = "searchingReqID";
    private final String PARAM_WHAT_DATE = "whatDate";
    private final String PARAM_SEARCHING_DATE = "searchingDate";
    private final String PARAM_SEARCH_BY = "searchBy";


    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String page;
        
        PageManager pageManager = PageManager.getInstance();
        
        HttpSession session = request.getSession(false);
    
        String findBy;
        int searchingRequestID;
        Date searchingDate;
        String whatDateParam;
        List<Request> req = null;
        IRequest requestDAO = factory.getRequestDAO();

        Boolean newSearch = Boolean.valueOf(request.getParameter(PARAM_NEW_SEARCH));
        findBy = request.getParameter(PARAM_SEARCH_BY);
        session.setAttribute(PARAM_SEARCH_BY, findBy);
        if (newSearch) {
            session.setAttribute(PARAM_NEW_SEARCH, "false");
            page = pageManager.getProperty(PageManager.SEARCH_REQUEST_PATH);
            return page;
        }

        String paramID = request.getParameter(PARAM_SEARCHING_REQUEST_ID);
        if (paramID != null && !paramID.equals("") && findBy.equals("id")) {
            searchingRequestID = Integer.valueOf(paramID);
            Request reqByID = requestDAO.findRequest(searchingRequestID);
            req = new ArrayList<Request>(1);
            req.add(reqByID);
            request.setAttribute("req", req);

            page = pageManager.getProperty(PageManager.SEARCH_REQUEST_PATH);
            return page;
        }

        String paramDate = request.getParameter(PARAM_SEARCHING_DATE);
        if (paramDate != null && !paramDate.equals("") && findBy.equals("date")) {
            searchingDate = Date.valueOf(paramDate);
            whatDateParam = request.getParameter(PARAM_WHAT_DATE);
            int mark = whatIsDate(whatDateParam);
            req = requestDAO.findRequestByDate(searchingDate, mark);
            request.setAttribute("req", req);

            page = pageManager.getProperty(PageManager.SEARCH_REQUEST_PATH);
            return page;
        }

        page = pageManager.getProperty(PageManager.SEARCH_REQUEST_PATH);
        return page;

    }

    private int whatIsDate(String mark) {
        if (mark.equals("before")) {
            return IRequest.LESS_OR_EQUAL;
        }
        if (mark.equals("equal")) {
            return IRequest.EQUAL;
        }
        if (mark.equals("after")) {
            return IRequest.MORE_OR_EQUAL;
        }
        return IRequest.EQUAL;
    }

}
