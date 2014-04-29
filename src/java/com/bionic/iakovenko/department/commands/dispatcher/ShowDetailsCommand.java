
package com.bionic.iakovenko.department.commands.dispatcher;

import com.bionic.iakovenko.department.commands.ICommand;
import com.bionic.iakovenko.department.dao.entity.Request;
import com.bionic.iakovenko.department.dao.entity.Worker;
import com.bionic.iakovenko.department.dao.entity.Works;
import com.bionic.iakovenko.department.dao.factory.DAOFactory;
import com.bionic.iakovenko.department.dao.factory.DBDAOFactory;
import com.bionic.iakovenko.department.dao.factory.DbType;
import com.bionic.iakovenko.department.dao.interfaces.IPlan;
import com.bionic.iakovenko.department.dao.interfaces.IRequest;
import com.bionic.iakovenko.department.dao.interfaces.IWorks;
import com.bionic.iakovenko.department.logger.SingleLogger;
import com.bionic.iakovenko.department.manager.PageManager;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;

/**
 *
 * @autor Alex Iakovenko
 * Date: Apr 23, 2014
 * Time: 11:46:11 PM
 */
public class ShowDetailsCommand implements ICommand{
    private final Logger logger = SingleLogger.getInstance().getLog();
    private final DBDAOFactory factory = DAOFactory.getFactory(DbType.MY_SQL);
    
    private final String PARAM_ERROR_MESSAGE = "errorMessage";
    private final String PARAM_DISP_REQUEST_ID = "dispRequestID";
    

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        String page;
        Integer dispRequestID;     

        PageManager pageManager = PageManager.getInstance();
          
        HttpSession session = request.getSession(false);
       
        dispRequestID = (Integer)session.getAttribute(PARAM_DISP_REQUEST_ID);
        try{
            if (dispRequestID == null) {
                dispRequestID = Integer.valueOf(request.getParameter(PARAM_DISP_REQUEST_ID));
            }
            
            if (dispRequestID == -1){
                throw new Exception();
            }      
            session.setAttribute(PARAM_DISP_REQUEST_ID, dispRequestID);
            
            IRequest requestDAO = factory.getRequestDAO();
            Request currRequest = requestDAO.findRequest(dispRequestID);
            request.setAttribute("dispRequest", currRequest);
            
            Short worksID = currRequest.getWorksID();
            IWorks worksDAO = factory.getWorksDAO();
            Works currWorks = worksDAO.findWorks(worksID);
            request.setAttribute("dispWorks", currWorks);
            
            IPlan planDAO = factory.getPlanDAO();
            List<Worker> listWorkers = planDAO.findWorker(currRequest);
            request.setAttribute("workerList", listWorkers);
            
            page = pageManager.getProperty(PageManager.SHOW_DETAILS_PATH);
       
        } catch (Exception e) {
            String message = "NOTHING_HAS_BEEN_CHOSEN";
            logger.warn(message, e);
            request.setAttribute(PARAM_ERROR_MESSAGE, message);
            page = pageManager.getProperty(PageManager.ERROR_PAGE_PATH);
        }
        return page;
    }
    

}
