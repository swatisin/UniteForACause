package controller;
import java.util.ArrayList;
import java.util.List;
import java.text.*;

import javax.servlet.http.HttpServletRequest;

import org.genericdao.RollbackException;
import org.mybeans.form.FormBeanException;
import org.mybeans.form.FormBeanFactory;

import databeans.*;
import formbeans.*;
import model.*;

public class ListDonations extends Action {
	
	private CharityDAO charityDAO;

	public ListDonations(Model model) {
		charityDAO = model.getCharityDAO();
	}

	public String getName() { return "listDonation.do"; }
    
	 public String perform(HttpServletRequest request) {
	        List<String> errors = new ArrayList<String>();
	        request.setAttribute("errors",errors);
	        
	        try {
	       		// Fetch the items now, so that in case there is no form or there are errors
	       		// We can just dispatch to the JSP to show the item list (and any errors)
	       		request.setAttribute("items", charityDAO.getItems());
	       		
	       		// Fetch the items again, since we modified the list
	       		
	       		return "listAllDonations.jsp";

	        } catch (RollbackException e) {
	        	errors.add(e.getMessage());
	        	return "error.jsp";
	        } 
	    }
}