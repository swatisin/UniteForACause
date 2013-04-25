package controller;



import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.genericdao.RollbackException;
import org.mybeans.form.FormBeanFactory;

import databeans.*;
import formbeans.*;
import model.*;

public class HomePage extends Action {
	
	private BlogDAO blogDAO;
	private EventDAO eventDAO;
	private CharityDAO charityDAO;
	private RegisterEventDAO registerEventsDAO;

	public HomePage(Model model) {
		blogDAO = model.getBlogDAO();
		eventDAO = model.getEventDAO();
		charityDAO = model.getCharityDAO();
		registerEventsDAO = model.getRegisterEventDAO();
	}

	public String getName() { return "homepage.do"; }
    
    public String perform(HttpServletRequest request) {
        List<String> errors = new ArrayList<String>();
        request.setAttribute("errors",errors);
        
        try {
       		// Fetch the items now, so that in case there is no form or there are errors
       		// We can just dispatch to the JSP to show the item list (and any errors)
       		request.setAttribute("blogs", blogDAO.getUserBlogs(((User) request.getSession().getAttribute("user")).getUserName()));
       		request.setAttribute("events", eventDAO.getUserEvents(((User) request.getSession().getAttribute("user")).getUserName()));
       		request.setAttribute("charities", charityDAO.getUserCharity(((User) request.getSession().getAttribute("user")).getUserName()));   
    		RegisterEventBean[] rEvents = (RegisterEventBean[]) registerEventsDAO.getUserRegistration(((User) request.getSession().getAttribute("user")).getUserName());
    		EventBean[] items = new EventBean[rEvents.length];
    		for (int i = 0; i < rEvents.length; i++) {
				RegisterEventBean rEvent = rEvents[i];
				items[i] = new EventBean();
				items[i] = eventDAO.getEvent(rEvent.getEventId());
    		}
       		request.setAttribute("register", items);   

       		//request.setAttribute("register", registerEventsDAO.getUserRegistration(((User) request.getSession().getAttribute("user")).getUserName()));       		
     		
       		return "userHomePage.jsp";

        } catch (RollbackException e) {
        	errors.add(e.getMessage());
        	return "error.jsp";
        } 
    }
}