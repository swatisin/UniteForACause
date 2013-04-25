package controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import model.Model;
import model.UserDAO;

import org.genericdao.RollbackException;
import org.mybeans.form.FormBeanException;
import org.mybeans.form.FormBeanFactory;

import databeans.User;
import email.Constants;
import email.GMailServer;
import formbeans.RegisterForm;
import controller.*;
import javax.servlet.ServletContext;
import controller.EmailUtility;



public class RegisterAction extends Action {
	
	private FormBeanFactory<RegisterForm> formBeanFactory = FormBeanFactory.getInstance(RegisterForm.class);

	private UserDAO userDAO;
	  private String host;
	  private String port;
	  private String useradmin;
	  private String password;
	
	public RegisterAction(Model model) {
		userDAO = model.getUserDAO();
	}

	public String getName() { return "register.do"; }

    public String perform(HttpServletRequest request) {
    	
        List<String> errors = new ArrayList<String>();
        request.setAttribute("errors",errors);
        String welcomeMessage = " Hello";
        String welcomeContent = " Testing 123";
        host = (String) request.getAttribute("host");
        port = (String )request.getAttribute("port");
        useradmin = (String )request.getAttribute("useradmin");
        System.out.println(useradmin);
        useradmin = "cmugivemea@gmail.com";
        password = (String )request.getAttribute("password");
       String resultMessage = "";


        

        try {
	        RegisterForm form = formBeanFactory.create(request);
	        request.setAttribute("form",form);
	
	       
	        if (!form.isPresent()) {
	            return "register.jsp";
	        }
	
	        errors.addAll(form.getValidationErrors());
	        if (errors.size() != 0) {
	            return "register.jsp";
	        }
	
	        // Create the user bean
	        User user = new User();
	        user.setUserName(form.getUserName());
	        user.setFirstName(form.getFirstName());
	        user.setLastName(form.getLastName());
	        user.setPassword(form.getPassword());
        	userDAO.create(user);
        
	        HttpSession session = request.getSession(false);
	        session.setAttribute("user",user);
            GMailServer sender = new GMailServer("cmugivemea@gmail.com", "cmu@1234");
            
            try {
            	System.out.println (form.getEmailAddress());
            sender.sendMail("Registration Mail From UniteForACause","Sign up mail for registeration","cmugivemea@gmail.com",form.getEmailAddress());
        }
               catch (Exception e) {
             e.printStackTrace();
        } 
            return "homepage.do";

			
        } catch (RollbackException e) {
        	errors.add(e.getMessage());
        	return "register.jsp";
        } catch (FormBeanException e) {
        	errors.add(e.getMessage());
        	return "register.jsp";
        }
    }
}
