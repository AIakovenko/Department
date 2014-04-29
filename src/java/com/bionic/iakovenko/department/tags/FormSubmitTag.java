
package com.bionic.iakovenko.department.tags;

import com.bionic.iakovenko.department.logger.SingleLogger;
import java.io.IOException;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.BodyTagSupport;
import org.apache.log4j.Logger;

/**
 *
 * @autor Alex Iakovenko
 * Date: Apr 21, 2014
 * Time: 10:12:05 PM
 */
public class FormSubmitTag extends BodyTagSupport{
    private final Logger logger = SingleLogger.getInstance().getLog();
    private JspWriter out;
    private String commandValue;
    private String buttonName;
    
    
    public void setCommandValue(String commandValue){
        this.commandValue = commandValue;
    }
    
    public void setButtonName(String buttonName){
        this.buttonName = buttonName;
    }

    @Override
    public int doStartTag() throws JspException {
        try{
            out = pageContext.getOut();
            out.write("<form method=\"POST\" action=\"department\">");
            out.write("<input type=\"hidden\" name=\"command\" value=\"" + 
                    commandValue + "\"/>" );
        } catch (IOException e){
            logger.error(e.toString());
            throw new JspException(e.toString());
            
            
        }
        return EVAL_BODY_INCLUDE;
    }

    @Override
    public int doEndTag() throws JspException {
        try{
            out.write("<input type=\"submit\" value=\"" + buttonName + "\"/>");
            out.write("</form>");
        } catch (IOException e){
            logger.error(e.toString());
            throw new JspException (e.toString());
        }
        return SKIP_BODY;
    }
    

}
