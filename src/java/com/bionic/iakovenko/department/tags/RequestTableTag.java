
package com.bionic.iakovenko.department.tags;

import com.bionic.iakovenko.department.commands.EntitySet;
import com.bionic.iakovenko.department.dao.entity.Dispatcher;
import com.bionic.iakovenko.department.dao.entity.Flat;
import com.bionic.iakovenko.department.dao.entity.Person;
import com.bionic.iakovenko.department.dao.entity.Request;
import com.bionic.iakovenko.department.dao.entity.Works;
import com.bionic.iakovenko.department.logger.SingleLogger;
import java.io.IOException;
import java.util.List;
import java.util.Set;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import org.apache.log4j.Logger;

/**
 *
 * @autor Alex Iakovenko Date: Apr 19, 2014 Time: 9:10:52 AM
 */
public class RequestTableTag extends TagSupport {

    private final Logger logger = SingleLogger.getInstance().getLog();
    private final String PARAM_REQUEST_ID = "req";
    private final String PARAM_DISP_REQUEST_ID = "dispRequestID";

    private String name;
    private boolean select;

    public void setName(String name) {
        this.name = name;
    }

    public void setSelect(String value) {
        this.select = new Boolean(value);
    }

    @Override
    public int doStartTag() throws JspException {

        try {

            JspWriter out = pageContext.getOut();
            HttpServletRequest request = (HttpServletRequest)pageContext.getRequest();
            HttpSession session = request.getSession(false);
            if (session != null){
                session.removeAttribute(PARAM_DISP_REQUEST_ID);
            }
            List<Request> req = (List<Request>) request.getAttribute(PARAM_REQUEST_ID);

            Set<Person> dispPersonList = EntitySet.getPersonList(req);
            Set<Flat> dispFlatList = EntitySet.getFlatList(req);
            Set<Works> dispWorksList = EntitySet.getWorksList(req);
            Set<Dispatcher> dispDispatcherList = EntitySet.getDispatcherList(req);

            for (Request r : req) {
                if (r != null) {
                    out.write("<tr>");
                    if (select) {
                        out.write("<td class=\"" + name + "\"> "
                                + "<input type=\"radio\" name=\"" + PARAM_DISP_REQUEST_ID+ "\" value=\"" + r.getRequestID() + "\"/>");
                        out.write("</td>");
                    }

                    out.write("<td class=\"" + name + "\">" + r.getRequestID() + "</td>");
                    for (Person p : dispPersonList) {
                        if (r.getPersonID().equals(p.getPersonID())) {
                            out.write("<td class=\"" + name + "\">" + p.getFamilyName() + " "
                                    + p.getGivenName() + " " + p.getAdditionalName()
                                    + "</td>");
                        }
                    }
                    for (Flat f : dispFlatList) {
                        if (r.getFlatID() == f.getFlatID()) {
                            out.write("<td class=\"" + name + "\">" + f.getAddress() + ", "
                                    + f.getBuilding() + " кв." + f.getApartment() + " " + "</td>");
                        }
                    }
                    for (Works w : dispWorksList) {
                        if (r.getWorksID() == w.getWorksID()) {
                            out.write("<td class=\"" + name + "\">" + w.getName() + "</td>");
                        }
                    }
                    out.write("<td class=\"" + name + "\">" + r.getRequestedTime() + "</td>");
                    out.write("<td class=\"" + name + "\">");

                    for (Dispatcher d : dispDispatcherList) {
                        if (r.getDispatcherID() == d.getDispatcherID()) {
                            out.write(d.getName());
                        }
                    }

                    out.write("</td>");
                    out.write("</tr>");
                } else {
                    out.write("<tr>");
                    int emptyID = -1;
                    if (select) {
                        out.write("<td class=\"" + name + "\"> "
                                + "<input type=\"radio\" name=\"" + PARAM_DISP_REQUEST_ID + "\" value=\"" + emptyID + "\" checked=\"true\"/>");
                        out.write("</td>");
                        out.write("<td class=\"" + name + "\" colspan=\"6\"></td> ");
                        out.write("</tr>");
                    }
                    
                }
            }

        } catch (IOException e) {
            logger.error(e.toString());
            throw new JspException(e.toString());
        }
        return SKIP_BODY;
    }  

}
