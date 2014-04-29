package com.bionic.iakovenko.department.commands.client;

import com.bionic.iakovenko.department.commands.ICommand;
import com.bionic.iakovenko.department.dao.entity.Flat;
import com.bionic.iakovenko.department.dao.entity.Works;
import com.bionic.iakovenko.department.dao.factory.DAOFactory;
import com.bionic.iakovenko.department.dao.factory.DBDAOFactory;
import com.bionic.iakovenko.department.dao.factory.DbType;
import com.bionic.iakovenko.department.dao.interfaces.IFlat;
import com.bionic.iakovenko.department.dao.interfaces.IWorks;
import com.bionic.iakovenko.department.logger.SingleLogger;
import com.bionic.iakovenko.department.manager.PageManager;
import java.io.IOException;
import java.sql.Date;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;

/**
 * @autor Alex Iakovenko Date: 4/11/14 Time: 10:11 PM
 */
public class SendRequestCommand implements ICommand {

    private final Logger logger = SingleLogger.getInstance().getLog();
    private final DBDAOFactory factory = DAOFactory.getFactory(DbType.MY_SQL);
    private final String ERROR_MESSAGE = "errorMessage";
    private final String PARAM_FLAT_ID = "flatID";
    private final String PARAM_WORKS_ID = "worksID";
    private final String PARAM_DATE = "date";


    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Date date;
        Short flatID;
        Short worksID;
        String page;
        
        PageManager pageManager = PageManager.getInstance();

        HttpSession session = request.getSession(false);
        
        try {
            flatID = (Short) session.getAttribute(PARAM_FLAT_ID);
            if (flatID == null) {
                flatID = Short.valueOf(request.getParameter(PARAM_FLAT_ID));
                session.setAttribute(PARAM_FLAT_ID, flatID);
            }
            
            date = (Date) session.getAttribute(PARAM_DATE);
            if (date == null) {
                date = Date.valueOf(request.getParameter(PARAM_DATE));
                session.setAttribute(PARAM_DATE, date);
            }
           
            worksID = (Short) session.getAttribute(PARAM_WORKS_ID);
            if (worksID == null) {
                worksID = Short.valueOf(request.getParameter(PARAM_WORKS_ID));
                session.setAttribute(PARAM_WORKS_ID, worksID);
            }

            IFlat flatDAO = factory.getFlatDAO();
            Flat flat = flatDAO.findFlat(flatID);
           session.setAttribute("flat", flat);

            IWorks worksDAO = factory.getWorksDAO();
            Works work = worksDAO.findWorks(worksID);
            session.setAttribute("works", work);

            page = pageManager.getProperty(PageManager.SEND_REQUEST_PATH);

        } catch (Exception e) {
            String message = "ILLEGAL_PARAMETER_EXCEPTION";
            logger.warn(message, e);
            request.setAttribute(ERROR_MESSAGE, message);
            page = pageManager.getProperty(PageManager.ERROR_PAGE_PATH);
        }
        return page;
    }
}
