/*
 * SendMailServlet
 *
 * Copyright (c) 1998-2002 Karl Moss. All Rights Reserved.
 * 
 * Developed in conjunction with the book "Java Servlets Developer's
 * Guide" published by McGraw-Hill/Osborne Media Group
 *
 * You may study, use, modify, and distribute this software for any
 * purpose.
 *
 * This software is provided WITHOUT WARRANTY either expressed or
 * implied.
 *
 * @author  Karl Moss
 * @date Dec 18, 2001
 */

package controller;

import sun.net.smtp.SmtpClient;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.ServletException;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * <p>This servlet will format an email form in HTML and, when
 * the user submits the form, will mail the message using
 * SMTP
 */
@SuppressWarnings("serial")
public class SendMailServlet extends HttpServlet
{
    public static String MAIL_FROM = "from";
    public static String MAIL_SUBJECT = "subject";
    public static String MAIL_BODY = "body";

    // Multiple 'to' addresses can be separated by commas
    public static String MAIL_TO = "karl@servletguru.com";

    // The SMTP server
    public static String MAIL_HOST = "larry-boy";

    public void doGet(HttpServletRequest req,
                      HttpServletResponse resp)
            throws ServletException, IOException
    {
        // Set the content type of the response
        resp.setContentType("text/html");

        // Get the PrintWriter to write the response
        PrintWriter out = resp.getWriter();

        // Create the HTML form
        out.println("<html>");
        out.println("<head>");
        out.println("<title>Send Email</title>");
        out.println("<center><h2>Send Email to Karl Moss</h2>");
        out.println("<br><form method=POST action=\"" +
                    req.getRequestURI() + "\">");
        out.println("<table>");
        out.println("<tr><td>From:</td>");
        out.println("<td><input type=text name=" +
                    MAIL_FROM + " size=30></td></tr>");
        out.println("<tr><td>Subject:</td>");
        out.println("<td><input type=text name=" +
                    MAIL_SUBJECT + " size=30></td></tr>");
        out.println("<tr><td>Text:</td>");
        out.println("<td><textarea name=" + MAIL_BODY +
                    " cols=40 rows=6></textarea></td></tr>");
        out.println("</table><br>");
        out.println("<input type=submit value=\"Send\">");
        out.println("<input type=reset value=\"Reset\">");
        out.println("</form></center></body></html>");

        // Wrap up
        out.println("</body>");
        out.println("</html>");
        out.flush();
    }

    public void doPost(HttpServletRequest req,
                       HttpServletResponse resp)
            throws ServletException, IOException
    {
        // Set the content type of the response
        resp.setContentType("text/html");

        // Create a PrintWriter to write the response
        PrintWriter out = new PrintWriter(resp.getOutputStream());

        // Get the data from the form
        String from = req.getParameter(MAIL_FROM);
        String subject = req.getParameter(MAIL_SUBJECT);
        String body = req.getParameter(MAIL_BODY);

        try
        {
            // Create a new SMTP client
            SmtpClient mailer = new SmtpClient(MAIL_HOST);

            // Set the 'from' and 'to' addresses
            mailer.from(from);
            mailer.to(MAIL_TO);

            // Get the PrintStream for writing the rest of the message
            java.io.PrintStream ps = mailer.startMessage();

            // Write out any mail headers
            ps.println("From: " + from);
            ps.println("To: " + MAIL_TO);
            ps.println("Subject: " + subject);

            // Write out the message body
            ps.print("\r\n");
            ps.println(body);

            ps.flush();
            ps.close();

            // Send the message
            mailer.closeServer();

            // Let the user know that the mail was sent
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Send Email</title>");
            out.println("<body><center>");
            out.println("<h2>Your email has been sent!</h2>");
            out.println("</center></body></html>");
        }
        catch (Exception ex)
        {
            // Got an error sending the mail; notify the client
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Send Email Error</title>");
            out.println("<body><center>");
            out.println("<h2>There was an error sending your email</h2>");
            out.println("<br>Message=" + ex.getMessage());
            out.println("</center>");
            out.println("</body></html>");
        }

        // Wrap up
        out.flush();
    }
}
