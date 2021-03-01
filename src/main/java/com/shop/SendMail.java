package com.shop;


import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Properties;

@WebServlet("/sendmail")
public class SendMail extends HttpServlet {

    private static final Logger logger = LogManager.getLogger(SendMail.class);


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //String content = request.getParameter("content");


        String action = request.getServletPath();

        switch(action) {
            case "/sendmailuserregister":
                try {
                    sendmailuserregister(request, response);
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
                break;
            case "/sendmailcartid":
                try {
                    sendmailcartid(request, response);
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
                break;
            default:
                break;
        }


    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


    }

    private void sendmailuserregister(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, ClassNotFoundException {
       // HttpSession sessionhttp = request.getSession(true);
       // String email = (String) sessionhttp.getAttribute("email");
        String email = request.getParameter("email");

        String to = "yevhen@yevhen-Lenovo-ideapad-320-15ISK";
        String from = "wwwwnewbob@localhost";
        String host = "localhost";

        Properties properties = System.getProperties();
        properties.setProperty("yevhen-Lenovo-ideapad-320-15ISK", host);

        Session session = Session.getDefaultInstance(properties);
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        try{
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(from));
            message.addRecipients(Message.RecipientType.TO, String.valueOf(new InternetAddress(to)));
            message.setSubject("This is the Subject Line!");
            message.setText("This is actual message from " + email);

            logger.info(" user.getEmail() " + email);

            Transport.send(message);
            String title = "Send Email";
            String res = "Sent message successfully....<a href='/bookstore/shop'>Back</a>";
            String docType = "<!doctype html public \"-//w3c//dtd html 4.0 " + "transitional//en\">\n";

            out.println(docType +
                    "<html>\n" +
                    "<head><title>" + title + "</title><head>\n" +
                    "<body bgcolor = \"#f0f0f0\">\n" +
                    "<h1 align = \"center\">" + title + "</h1>\n" +
                    "<p align = \"center\">" + res + "</p>\n" +
                    "</body>\n" +
                    "</html>"
            );

        } catch (MessagingException mex){
            mex.printStackTrace();
        }


    }


    private void sendmailcartid(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, ClassNotFoundException {
        // HttpSession sessionhttp = request.getSession(true);
        // String email = (String) sessionhttp.getAttribute("email");
        String cardid = request.getParameter("cartid");

        String to = "yevhen@yevhen-Lenovo-ideapad-320-15ISK";
        String from = "wwwwnewbob@localhost";
        String host = "localhost";

        Properties properties = System.getProperties();
        properties.setProperty("yevhen-Lenovo-ideapad-320-15ISK", host);

        Session session = Session.getDefaultInstance(properties);
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        try{
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(from));
            message.addRecipients(Message.RecipientType.TO, String.valueOf(new InternetAddress(to)));
            message.setSubject("This is the Subject Line!");
            message.setText("This is actual message from <a href=\"http://localhost:8080/bookstore/createpdfdoc?cartid=" + cardid + "\">Your payment</a>");

            logger.info(" user.getEmail() " + cardid);

            Transport.send(message);
            String title = "Send Email";
            String res = "Sent message successfully....<a href='/bookstore/shop'>Back</a>";
            String docType = "<!doctype html public \"-//w3c//dtd html 4.0 " + "transitional//en\">\n";

            out.println(docType +
                    "<html>\n" +
                    "<head><title>" + title + "</title><head>\n" +
                    "<body bgcolor = \"#f0f0f0\">\n" +
                    "<h1 align = \"center\">" + title + "</h1>\n" +
                    "<p align = \"center\">" + res + "</p>\n" +
                    "</body>\n" +
                    "</html>"
            );

        } catch (MessagingException mex){
            mex.printStackTrace();
        }


    }

}
