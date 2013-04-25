package controller;



import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.genericdao.RollbackException;
import org.mybeans.form.FormBeanException;
import org.mybeans.form.FormBeanFactory;

import databeans.*;
import formbeans.*;
import model.*;

public class ListBlogs extends Action {
	private FormBeanFactory<blogform>  blogformFactory  = FormBeanFactory.getInstance(blogform.class);
	
	private BlogDAO blogDAO;

	public ListBlogs(Model model) {
		blogDAO = model.getBlogDAO();
	}

	public String getName() { return "listBlog.do"; }
    
    public String perform(HttpServletRequest request) {
        List<String> errors = new ArrayList<String>();
        request.setAttribute("errors",errors);
        
        try {
       		// Fetch the items now, so that in case there is no form or there are errors
       		// We can just dispatch to the JSP to show the item list (and any errors)
       		request.setAttribute("items", blogDAO.getBlogs());
       		
       		// Fetch the items again, since we modified the list
       		
       		return "listAllBlogs.jsp";

        } catch (RollbackException e) {
        	errors.add(e.getMessage());
        	return "error.jsp";
        } 
    }
}