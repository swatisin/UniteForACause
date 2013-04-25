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

public class AddEvent extends Action {
	private FormBeanFactory<EventForm>  EventFormFactory  = FormBeanFactory.getInstance(EventForm.class);
	
	private EventDAO eventDAO;

	public AddEvent(Model model) {
		eventDAO = model.getEventDAO();
	}

	public String getName() { return "addEvent.do"; }
    
    public String perform(HttpServletRequest request) {
        List<String> errors = new ArrayList<String>();
        request.setAttribute("errors",errors);
        
        try {
       		// Fetch the items now, so that in case there is no form or there are errors
       		// We can just dispatch to the JSP to show the item list (and any errors)
       		request.setAttribute("items", eventDAO.getUserEvents(((User) request.getSession().getAttribute("user")).getUserName()));
       		
	        EventForm form = EventFormFactory.create(request);
        	request.setAttribute("form", form);
       		System.out.println(form.getAction());
       		System.out.println(form.getId());
       		errors.addAll(form.getValidationErrors());
	        if (errors.size() > 0) {
	        	return "events.jsp";
	        }
	        System.out.println("Date is " + form.getEventDate());
	        DateFormat fmt = new SimpleDateFormat("yyyy-mm-dd");
	        java.sql.Date sqlDate = null;
	        java.sql.Date sqlDate2 = null;

	        try {
	        if ( form.getEventDate() != null ) {
	        	java.util.Date utilDate = fmt.parse(form.getEventDate());
	        	sqlDate = new java.sql.Date(utilDate.getTime());
	        	sqlDate2 = new java.sql.Date(utilDate.getTime() -  1 * 24 * 60 * 60 * 1000);
	        }
	        } catch (ParseException e) {
	            e.printStackTrace();
	        }
	        EventBean bean = new EventBean();
	        bean.setDescription(form.getDescription());
	        bean.setTitle(form.getTitle());
	        bean.setCategory(form.getCategory());
	        bean.setEventDate(sqlDate);
	        bean.setRemindarDate(sqlDate2);
       		bean.setUserName(((User) request.getSession().getAttribute("user")).getUserName());

        	if ( form.getAction() == null )
        		return "events.jsp";
       		else if (form.getAction().equals("Add Event")) {
        		eventDAO.addToTop(bean);
        	} else {
        		errors.add("Invalid action: " + form.getAction());
        	}

       		// Fetch the items again, since we modified the list
       		request.setAttribute("items", eventDAO.getUserEvents(bean.getUserName()));
       		
       		return "events.jsp";

        } catch (RollbackException e) {
        	errors.add(e.getMessage());
        	return "error.jsp";
        } catch (FormBeanException e) {
        	errors.add(e.getMessage());
        	return "error.jsp";
        }
    }
}