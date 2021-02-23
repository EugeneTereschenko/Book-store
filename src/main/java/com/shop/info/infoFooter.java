package com.shop.info;


import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.io.IOException;

public class infoFooter extends SimpleTagSupport {

    public void doTag() throws JspException, IOException {
        JspWriter out = getJspContext().getOut();
        out.println("<footer class=\"bg-light text-center text-lg-start\">");
        out.println("<div class=\"text-center p-3\"  align = \"center\" style=\"background-color: rgba(0, 0, 0, 0.2);\">");
        out.println("© 2020 Copyright:");
        out.println("</div>");
        out.println("</footer>");
    }

}
