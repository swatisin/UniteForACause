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

public class SearchBlogs extends Action {
	private FormBeanFactory<blogform>  blogformFactory  = FormBeanFactory.getInstance(blogform.class);
	
	private BlogDAO blogDAO;

	public SearchBlogs(Model model) {
		blogDAO = model.getBlogDAO();
	}

	public String getName() { return "searchBlog.do"; }
    
    public String perform(HttpServletRequest request) {
        List<String> errors = new ArrayList<String>();
        request.setAttribute("errors",errors);
        
        try {
       		// Fetch the items now, so that in case there is no form or there are errors
       		// We can just dispatch to the JSP to show the item list (and any errors)
       		request.setAttribute("items", blogDAO.getBlogs());
       		
	        blogform form = blogformFactory.create(request);
        	request.setAttribute("form", form);

	       
        	if (form.getAction().equals("Search Blog")) {
        		request.setAttribute("blogs",blogDAO.searchBlog(form.getCategory()));
        	} else {
        		errors.add("Invalid action: " + form.getAction());
        	}

       		// Fetch the items again, since we modified the list
       		request.setAttribute("items", blogDAO.getBlogs());
       		
       		return "searchblogs.jsp";

        } catch (RollbackException e) {
        	errors.add(e.getMessage());
        	return "error.jsp";
        } catch (FormBeanException e) {
        	errors.add(e.getMessage());
        	return "error.jsp";
        }
    }
}