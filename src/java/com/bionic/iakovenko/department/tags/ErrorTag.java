
package com.bionic.iakovenko.department.tags;

import com.bionic.iakovenko.department.logger.SingleLogger;
import java.io.IOException;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import org.apache.log4j.Logger;

/**
 *
 * @autor Alex Iakovenko Date: Apr 18, 2014 Time: 12:11:38 PM
 */
public class ErrorTag extends TagSupport {

    private final Logger logger = SingleLogger.getInstance().getLog();
    private final String UNKNOW_ERROR = "unknown error";
    private String message;

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public int doStartTag() throws JspException {
        try {
            JspWriter out = pageContext.getOut();
            if (message != null) {
                out.write(message);
            } else {
                out.write(UNKNOW_ERROR);
            }
        } catch (IOException e) {
            logger.error(e);
            throw new JspException(e.toString());
        }

        return SKIP_BODY;
    }

}
