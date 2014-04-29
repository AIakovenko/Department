package com.bionic.iakovenko.department.commands.dispatcher;

import com.bionic.iakovenko.department.commands.ICommand;
import com.bionic.iakovenko.department.dao.entity.Request;
import com.bionic.iakovenko.department.dao.entity.Worker;
import com.bionic.iakovenko.department.dao.entity.Works;
import com.bionic.iakovenko.department.dao.factory.DAOFactory;
import com.bionic.iakovenko.department.dao.factory.DBDAOFactory;
import com.bionic.iakovenko.department.dao.factory.DbType;
import com.bionic.iakovenko.department.dao.interfaces.IRequest;
import com.bionic.iakovenko.department.dao.interfaces.IWorker;
import com.bionic.iakovenko.department.dao.interfaces.IWorks;
import com.bionic.iakovenko.department.manager.PageManager;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


/**
 * @autor Alex Iakovenko Date: Apr 15, 2014 Time: 11:38:13 AM
 */
public class FormingWorkGroupCommand implements ICommand {

    private final DBDAOFactory factory = DAOFactory.getFactory(DbType.MY_SQL);

    private final String PARAM_ERROR_MESSAGE = "errorMessage";
    private final String PARAM_DISP_REQUEST_ID = "dispRequestID";

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String page;

        PageManager pageManager = PageManager.getInstance();

        HttpSession session = request.getSession(false);

        Integer dispRequestID = (Integer) session.getAttribute(PARAM_DISP_REQUEST_ID);
        if (dispRequestID == null) {
            try {
                dispRequestID = Integer.valueOf(request.getParameter(PARAM_DISP_REQUEST_ID));
            } catch (NumberFormatException e) {
                request.setAttribute(PARAM_ERROR_MESSAGE, "NOTHING_HAS_BEEN_CHOSEN");
                return pageManager.getProperty(PageManager.ERROR_PAGE_PATH);
            }
        }
        session.setAttribute(PARAM_DISP_REQUEST_ID, dispRequestID);

        IRequest dispRequestDAO = factory.getRequestDAO();
        Request dispRequest = dispRequestDAO.findRequest(dispRequestID);
        session.setAttribute("dispRequest", dispRequest);

        IWorks worksDAO = factory.getWorksDAO();
        Works works = worksDAO.findWorks(dispRequest.getWorksID());
        session.setAttribute("works", works);

        IWorker workerDAO = factory.getWorkerDAO();
        List<Worker> workerList = workerDAO.findAll();
        session.setAttribute("workerList", workerList);

        page = pageManager.getProperty(PageManager.FORMING_WORKGROUP_PATH);

        return page;
    }

}
