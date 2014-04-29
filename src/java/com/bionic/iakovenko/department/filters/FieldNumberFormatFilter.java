/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bionic.iakovenko.department.filters;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @autor Alex Iakovenko Date: Apr 22, 2014 Time: 11:08:24 AM
 */
public class FieldNumberFormatFilter implements Filter {

    private FilterConfig filterConfig;
    private final String PARAM_ERROR_MESSAGE = "errorMessage";
    private final String PARAM_COMMAND = "command";
    private final String MESSAGE_COMMAND = "error";
    private final String MESSAGE = "ILLEGAL_PARAMETER_EXCEPTION";

    /*
     * This parameter belong to case when user "Dispatcher" wonts to find
     * a request using request's id number. The format of field must be a munberical.
     */
    private final String PARAM_REQUEST_ID = "searchingReqID";

    /*
     * This parameter belong to case when user "Client" registers in system and 
     * enters a number of buildung. The format of field must be a munberical.
     */
    private final String PARAM_BUILDING = "building";

    /*
     * This parameter belong to case when user "Client" registers in system and 
     * enters a number of apartment. The format of field must be a munberical.
     */
    private final String PARAM_APARTMENT = "apartment";

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        this.filterConfig = filterConfig;
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;

        Pattern pattern = Pattern.compile("[0-9]+");
        Matcher matcher;

        String paramSearchingReqID = httpRequest.getParameter(PARAM_REQUEST_ID);
        if (paramSearchingReqID != null) {
            matcher = pattern.matcher(paramSearchingReqID);
            if (!matcher.matches()) {
                request.setAttribute(PARAM_ERROR_MESSAGE, MESSAGE);
                httpRequest.setAttribute(PARAM_COMMAND, MESSAGE_COMMAND);
            }
        }
        
        String paramBuilding = httpRequest.getParameter(PARAM_BUILDING);
        if (paramBuilding != null) {
            matcher = pattern.matcher(paramBuilding);
            if (!matcher.matches()) {
                request.setAttribute(PARAM_ERROR_MESSAGE, MESSAGE);
                httpRequest.setAttribute(PARAM_COMMAND, MESSAGE_COMMAND);
            }
        }
        
        String paramApartment = httpRequest.getParameter(PARAM_APARTMENT);
        if (paramApartment != null) {
            matcher = pattern.matcher(paramApartment);
            if (!matcher.matches()) {
                request.setAttribute(PARAM_ERROR_MESSAGE, MESSAGE);
                httpRequest.setAttribute(PARAM_COMMAND, MESSAGE_COMMAND);
            }
        }
        
        chain.doFilter(request, response);

    }

    @Override
    public void destroy() {
    }

}
