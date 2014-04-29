
package com.bionic.iakovenko.department.servlet;

import com.bionic.iakovenko.department.commands.CommandFactory;
import com.bionic.iakovenko.department.commands.ICommand;
import com.bionic.iakovenko.department.logger.SingleLogger;
import com.bionic.iakovenko.department.manager.PageManager;
import org.apache.log4j.Logger;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 *
 * @author alex
 */
public class DepartmentServlet extends HttpServlet {
    private final Logger logger = SingleLogger.getInstance().getLog();
    private final String PARAM_ERROR_MESSAGE = "errorMessage";


    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        response.setContentType("text/html;charset=UTF-8");
        String page;
        String message;
        try {
            ICommand command = CommandFactory.getInstance().getCommand(request);
            page = command.execute(request, response);

        } catch (ServletException e) {
            message = "SERVLET_EXCEPTION_ERROR_MESSAGE";
            logger.error(message, e);
            request.setAttribute(PARAM_ERROR_MESSAGE, message );
            page = PageManager.getInstance().getProperty(PageManager.ERROR_PAGE_PATH);
        } catch (IOException e) {
            message = "IO_EXCEPTION_ERROR_MESSAGE";
            log(message, e);
            request.setAttribute(PARAM_ERROR_MESSAGE, message);
            page = PageManager.getInstance().getProperty(PageManager.ERROR_PAGE_PATH);
        }
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(page);
        dispatcher.forward(request, response);
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
