package controller;



import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.genericdao.RollbackException;
import org.mybeans.form.FormBeanFactory;

import databeans.*;
import formbeans.*;
import model.*;

public class AdminAction extends Action {
	
	private BlogDAO blogDAO;
	private EventDAO eventDAO;
	private CharityDAO charityDAO;
	private RegisterEventDAO registerEventsDAO;

	public AdminAction(Model model) {
		blogDAO = model.getBlogDAO();
		eventDAO = model.getEventDAO();
		charityDAO = model.getCharityDAO();
		registerEventsDAO = model.getRegisterEventDAO();
	}

	public String getName() { return "admin.do"; }
    
    public String perform(HttpServletRequest request) {
        List<String> errors = new ArrayList<String>();
        request.setAttribute("errors",errors);
        
        try {
       		// Fetch the items now, so that in case there is no form or there are errors
       		// We can just dispatch to the JSP to show the item list (and any errors)
       		request.setAttribute("blogs", blogDAO.getBlogs());
       		request.setAttribute("events", eventDAO.getEvents());
       		request.setAttribute("charities", charityDAO.getItems());   
       		
       		request.setAttribute("numDonations", charityDAO.getCount() );
       		System.out.println(charityDAO.getCount());
       		request.setAttribute("sum", charityDAO.getSum() );

    		

       		//request.setAttribute("register", registerEventsDAO.getUserRegistration(((User) request.getSession().getAttribute("user")).getUserName()));       		
     		
       		return "admin.jsp";

        } catch (RollbackException e) {
        	errors.add(e.getMessage());
        	return "error.jsp";
        } 
    }
}