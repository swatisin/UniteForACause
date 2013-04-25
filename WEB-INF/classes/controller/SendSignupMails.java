package controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import model.BlogDAO;
import model.Model;
import model.emailDAO;

import org.genericdao.RollbackException;
import org.mybeans.form.FormBeanException;
import org.mybeans.form.FormBeanFactory;

import databeans.CharityBean;
import databeans.EmailBean;
import databeans.User;
import databeans.signupEmail;
import formbeans.DonationForm;
import formbeans.SignupForm;
import formbeans.blogform;
import email.*;

public class SendSignupMails  extends Action {
	
	private FormBeanFactory<SignupForm>  signupformFactory  = FormBeanFactory.getInstance(SignupForm.class);
	
	private emailDAO EmailDAO;
	
	public SendSignupMails(Model model) {
		EmailDAO = model.getEmailDAO();
	}

	
	public String getName() { return "signup.do"; }

	public String perform(HttpServletRequest request) {
		
		 List<String> errors = new ArrayList<String>();
		  request.setAttribute("errors",errors);
	        
	        try {
	       		// Fetch the items now, so that in case there is no form or there are errors
	       		// We can just dispatch to the JSP to show the item list (and any errors)
	       		request.setAttribute("items", EmailDAO.getItems());
	       		signupEmail [] listmail = EmailDAO.getItems();
	       		String mailList = "cmugivemea@gmail.com";
	       		
	       		for ( int i = 0 ; i < listmail.length ; i ++)
	       			mailList = mailList + "," + listmail[i].getEmailAddress();
	       		
	       	Constants.setEmailTO(mailList); 
	       	System.out.println(mailList);
	       		
	       		
	       	DBScheduler dbs = new DBScheduler();
          
	         try {
	         	dbs.callScheduler();
	         } catch ( Exception e ) {
	        
	         } 
	       		
	       		SignupForm form = signupformFactory.create(request);
	        	request.setAttribute("form", form);

		        errors.addAll(form.getValidationErrors());
		        if (errors.size() > 0) {
		        	return "index.jsp";
		        }
		      //  System.out.println("Date is " + form.getE);
		        
		        signupEmail bean = new signupEmail();
		        bean.setEmailAddress(form.getEmailAddress());
		        
	       		
	        	if (form.getButton().equals("Add For Signup")) {
	        		EmailDAO.addForSignup(bean);
	        	} else {
	        		errors.add("Invalid action: " + form.getButton());
	        	}

	       		// Fetch the items again, since we modified the list
	       		request.setAttribute("items", EmailDAO.getItems());
	       		
	       		return "signupSuccess.jsp";

	        } catch (RollbackException e) {
	        	errors.add(e.getMessage());
	        	return "error.jsp";
	        } catch (FormBeanException e) {
	        	errors.add(e.getMessage());
	        	return "error.jsp";
	        }
		
	  
	  }

}
