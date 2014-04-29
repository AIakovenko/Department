
package com.bionic.iakovenko.department.filters;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 *
 * @autor Alex Iakovenko Date: Apr 22, 2014 Time: 10:59:06 PM
 */
public class LocaleFilter implements Filter {

    private FilterConfig filterConfig;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        this.filterConfig = filterConfig;
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpSession session = httpRequest.getSession(false);

        if (session != null) {
            String thisPage = httpRequest.getParameter("command");                  //save the name of a page which should be reloaded

            String locale = httpRequest.getParameter("locale");                     //save parameter of current locale if it has been changed. 
            if (locale == null) {
                locale = (String) session.getAttribute("locale");                   //if locale hasn't been changed the variable will be initialiged by old value of locale
            }
            if (locale == null) {
                locale = "en_GB";                                                   //the default value of locale 
            }
            session.setAttribute("locale", locale);
            request.setAttribute("thisPage", thisPage);
        } else {
            
            httpRequest.setAttribute("locale", "en_GB");
        }
        chain.doFilter(request, response);

    }

    @Override
    public void destroy() {

    }

}
