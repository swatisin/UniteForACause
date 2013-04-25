package controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import model.BlogDAO;
import model.EventDAO;
import model.Model;
import model.UserDAO;

import org.genericdao.RollbackException;
import org.mybeans.form.FormBeanException;
import org.mybeans.form.FormBeanFactory;

import databeans.BlogBean;
import databeans.EventBean;
import databeans.User;
import formbeans.IdForm;

/*
 * Removes a photo.  Given an "id" parameter.
 * Checks to see that id is valid number for a photo and that
 * the logged user is the owner.
 * 
 * Sets up the "userList" and "photoList" request attributes
 * and if successful, forwards back to to "manage.jsp".
 */
public class RemoveEvent extends Action {
	private FormBeanFactory<IdForm> formBeanFactory = FormBeanFactory.getInstance(IdForm.class);

	private EventDAO eventDAO;
	private UserDAO  userDAO;

    public RemoveEvent(Model model) {
    	eventDAO = model.getEventDAO();
    	userDAO  = model.getUserDAO();
	}

    public String getName() { return "deleteEvent.do"; }

    public String perform(HttpServletRequest request) {
        List<String> errors = new ArrayList<String>();
        request.setAttribute("errors",errors);
        
		try {
            // Set up user list for nav bar
			request.setAttribute("userList",userDAO.getUsers());

	    	IdForm form = formBeanFactory.create(request);
	    	
	    	User user = (User) request.getSession().getAttribute("user");

			int id = form.getIdAsInt();
			eventDAO.delete(id);

    		// Be sure to get the photoList after the delete
			EventBean[] eventList = eventDAO.getEvents();
	        request.setAttribute("events",eventList);

	        return "admin.do";
		} catch (RollbackException e) {
    		errors.add(e.getMessage());
    		return "error.jsp";
		} catch (FormBeanException e) {
    		errors.add(e.getMessage());
    		return "error.jsp";
    	}
    }
}
