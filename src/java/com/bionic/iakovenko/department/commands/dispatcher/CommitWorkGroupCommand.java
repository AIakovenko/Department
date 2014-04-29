package com.bionic.iakovenko.department.commands.dispatcher;

import com.bionic.iakovenko.department.commands.ICommand;
import com.bionic.iakovenko.department.dao.entity.Dispatcher;
import com.bionic.iakovenko.department.dao.entity.Request;
import com.bionic.iakovenko.department.dao.entity.Worker;
import com.bionic.iakovenko.department.dao.factory.DAOFactory;
import com.bionic.iakovenko.department.dao.factory.DBDAOFactory;
import com.bionic.iakovenko.department.dao.factory.DbType;
import com.bionic.iakovenko.department.dao.interfaces.IPlan;
import com.bionic.iakovenko.department.dao.interfaces.IRequest;
import com.bionic.iakovenko.department.dao.interfaces.IWorker;
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
 * Time: 12:33:53 AM
 */
public class CommitWorkGroupCommand implements ICommand{
    private final DBDAOFactory factory = DAOFactory.getFactory(DbType.MY_SQL);
   
    private final String PARAM_ERROR_MESSAGE = "errorMessage";
    private final String PARAM_SELECTED_WORKERS = "selectedWorkers";
    private final String PARAM_DISPATCHER_REQUEST = "dispRequest";
    private final String PARAM_USER= "disp";

    
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        String page;
        Short workerID;
        
        PageManager pageManager = PageManager.getInstance();
        
        HttpSession session = request.getSession(false);
             
        String[] workers = request.getParameterValues(PARAM_SELECTED_WORKERS);
        try{
            if(workers == null){
                throw new Exception("NOTHING_HAS_BEEN_CHOSEN");
            }
        
            IWorker workerDAO = factory.getWorkerDAO();
            IRequest dispRequestDAO = factory.getRequestDAO();
            IPlan workerGroup = factory.getPlanDAO();
            Request dispRequest = (Request)session.getAttribute(PARAM_DISPATCHER_REQUEST);
            Dispatcher dispatcher = (Dispatcher)session.getAttribute(PARAM_USER);
        
            boolean isInsertedToPlan;
            boolean isUpdatedRequest;
        
            for(String worker : workers){
                workerID = Short.valueOf(worker);
                Worker w = workerDAO.findWorker(workerID);
                isInsertedToPlan = workerGroup.insertPlan(dispRequest, w);
                if (!isInsertedToPlan) { 
                    throw new Exception("FAIL_INSERTING_TO_PLAN");
                } 
            }
            isUpdatedRequest = dispRequestDAO.updateRequestByDispatcher(dispRequest, dispatcher);
            if(!isUpdatedRequest){
                throw new Exception("FAIL_REQUEST_HAS_NOT_BEEN_CHANGED");    
            }
            page = pageManager.getProperty(PageManager.COMMIT_WORK_GROUP_PATH);
            
        } catch(Exception e){ 
            request.setAttribute(PARAM_ERROR_MESSAGE, e.getMessage());
            page = pageManager.getProperty(PageManager.ERROR_PAGE_PATH);
        }
        return page;
    }

}
