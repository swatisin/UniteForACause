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

public class ListEvents extends Action {
	
	private EventDAO eventDAO;

	public ListEvents(Model model) {
		eventDAO = model.getEventDAO();
	}

	public String getName() { return "listEvent.do"; }
    
	 public String perform(HttpServletRequest request) {
	        List<String> errors = new ArrayList<String>();
	        request.setAttribute("errors",errors);
	        
	        try {
	       		// Fetch the items now, so that in case there is no form or there are errors
	       		// We can just dispatch to the JSP to show the item list (and any errors)
	       		request.setAttribute("items", eventDAO.getEvents());
	       		
	       		// Fetch the items again, since we modified the list
	       		
	       		return "listAllevents.jsp";

	        } catch (RollbackException e) {
	        	errors.add(e.getMessage());
	        	return "error.jsp";
	        } 
	    }
}