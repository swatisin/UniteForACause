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

public class RegisterEvent extends Action {
	private FormBeanFactory<EventForm>  EventFormFactory  = FormBeanFactory.getInstance(EventForm.class);
	
	private EventDAO eventDAO;
	private RegisterEventDAO registerDAO;

	public RegisterEvent(Model model) {
		eventDAO = model.getEventDAO();
		registerDAO = model.getRegisterEventDAO();
	}

	public String getName() { return "registerEvent.do"; }
    
    public String perform(HttpServletRequest request) {
        List<String> errors = new ArrayList<String>();
        request.setAttribute("errors",errors);
        
        try {
       		// Fetch the items now, so that in case there is no form or there are errors
       		// We can just dispatch to the JSP to show the item list (and any errors)
       		
	        EventForm form = EventFormFactory.create(request);
       		System.out.println(form.getId());

       		request.setAttribute("item", eventDAO.getEvent(form.getId()));
        	request.setAttribute("form", form);
       		System.out.println(form.getAction());
       		System.out.println(form.getId());
       		
	        
       		System.out.println("#1");

	        RegisterEventBean bean = new RegisterEventBean();
	        
	        bean.setEventId(form.getId());
       		bean.setUserName(((User) request.getSession().getAttribute("user")).getUserName());
       		System.out.println("#2");

        	if ( form.getAction().equals("Register") ){
           		System.out.println("#3");

        		registerDAO.addToTop(bean);     			
       		}
        	else {
        		errors.add("Invalid action: " + form.getAction());
        	}

       		// Fetch the items again, since we modified the list
       		//request.setAttribute("item", eventDAO.getEvent(form.getId()));
       		
       		return "homepage.do";

        } catch (RollbackException e) {
        	errors.add(e.getMessage());
        	return "error.jsp";
        } catch (FormBeanException e) {
        	errors.add(e.getMessage());
        	return "error.jsp";
        }
    }
}