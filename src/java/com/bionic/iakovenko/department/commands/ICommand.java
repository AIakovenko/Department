package com.bionic.iakovenko.department.commands;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @autor Alex Iakovenko
 * Date: 4/9/14
 * Time: 12:41 PM
 */
public interface ICommand {
    public String execute (HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException;
}
