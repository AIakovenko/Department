
package com.bionic.iakovenko.department.filters;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @autor Alex Iakovenko Date: Apr 25, 2014 Time: 10:23:14 AM
 */
public class AliveSessionFilter implements Filter {

    private FilterConfig filterConfig;
    private boolean sessionFlag;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        this.filterConfig = filterConfig;
        sessionFlag = true;
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;

        String action = httpRequest.getParameter("command");
        httpRequest.setAttribute("command", action);

        HttpSession session;
        if (sessionFlag) {
            session = httpRequest.getSession(true);
            sessionFlag = false;
        } else {
            session = httpRequest.getSession(false);
        }

        if (session == null) {
            request.setAttribute("command", null);
        } 
        chain.doFilter(request, response);
        

    }

    @Override
    public void destroy() {
    }

}
