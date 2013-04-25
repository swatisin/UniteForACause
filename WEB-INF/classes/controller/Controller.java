package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Model;
import databeans.User;
import email.*;
import javax.servlet.ServletContext;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.mail.Message;
import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;


@SuppressWarnings("serial")
public class Controller extends HttpServlet {
	ServletContext context;
    public void init() throws ServletException {
        Model model = new Model(getServletConfig());

        Action.add(new ChangePwdAction(model));
        Action.add(new LoginAction(model));
        Action.add(new LogoutAction(model));
        Action.add(new RegisterAction(model));
        Action.add(new RemoveAction(model));
        Action.add(new RemoveEvent(model));
        Action.add(new AddBlog(model));
        Action.add(new AddEvent(model));
        Action.add(new Donation(model));
        Action.add(new HomePage(model));
        Action.add(new ListEvents(model));        
        Action.add(new ListBlogs(model));
        Action.add(new SendSignupMails(model));
        Action.add(new RegisterEvent(model));
        Action.add(new SearchBlogs(model));
        Action.add(new AdminAction(model));
        Action.add(new DonateAction(model));
        Action.add(new ListDonations(model));
        
        
        

        System.out.println("Reaching here");
      
        context = getServletContext();
       // String host = context.getInitParameter("host");
        //String port = context.getInitParameter("port");
        //String user = context.getInitParameter("user");
        //String pass = context.getInitParameter("pass");

       
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String nextPage = performTheAction(request);
        sendToNextPage(nextPage,request,response);
    }
    
    /*
     * Extracts the requested action and (depending on whether the user is logged in)
     * perform it (or make the user login).
     * @param request
     * @return the next page (the view)
     */
    private String performTheAction(HttpServletRequest request) {
        HttpSession session     = request.getSession(true);
        String      servletPath = request.getServletPath();
        User        user = (User) session.getAttribute("user");
        String      action = getActionName(servletPath);

        // System.out.println("servletPath="+servletPath+" requestURI="+request.getRequestURI()+"  user="+user);

        if (action.equals("register.do") || action.equals("login.do") || action.equals("signup.do") || action.equals("listBlog.do") || action.equals("listEvent.do") || action.equals("listDonation.do") || action.equals("searchBlog.do")){
        	// Allow these actions without logging in
        	if (action.equals("login.do") )
        		return Action.perform(action, request);
        	else {
        		request.setAttribute("host", context.getInitParameter("host"));
        		request.setAttribute("port", context.getInitParameter("port"));
        		request.setAttribute("user", context.getInitParameter("useradmin"));
        		request.setAttribute("password", context.getInitParameter("password"));
        		return Action.perform(action,request);
        	}
        }
        
        if (user == null ) {
        	// If the user hasn't logged in, direct him to the login page
			return Action.perform("login.do",request);
        }

      	// Let the logged in user run his chosen action
		return Action.perform(action,request);
    }

    /*
     * If nextPage is null, send back 404
     * If nextPage ends with ".do", redirect to this page.
     * If nextPage ends with ".jsp", dispatch (forward) to the page (the view)
     *    This is the common case
     */
    private void sendToNextPage(String nextPage, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
    	if (nextPage == null) {
    		response.sendError(HttpServletResponse.SC_NOT_FOUND,request.getServletPath());
    		return;
    	}
    	
    	if (nextPage.endsWith(".do")) {
			response.sendRedirect(nextPage);
			return;
    	}
    	
    	if (nextPage.endsWith(".jsp")) {
	   		RequestDispatcher d = request.getRequestDispatcher("WEB-INF/" + nextPage);
	   		d.forward(request,response);
	   		return;
    	}
    	
    	if (nextPage.equals("image")) {
	   		RequestDispatcher d = request.getRequestDispatcher(nextPage);
	   		d.forward(request,response);
	   		return;
    	}
    	
    	throw new ServletException(Controller.class.getName()+".sendToNextPage(\"" + nextPage + "\"): invalid extension.");
    }

	/*
	 * Returns the path component after the last slash removing any "extension"
	 * if present.
	 */
    private String getActionName(String path) {
    	// We're guaranteed that the path will start with a slash
        int slash = path.lastIndexOf('/');
        return path.substring(slash+1);
    }
}
