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

public class AddBlog extends Action {
	private FormBeanFactory<blogform>  blogformFactory  = FormBeanFactory.getInstance(blogform.class);
	
	private BlogDAO blogDAO;

	public AddBlog(Model model) {
		blogDAO = model.getBlogDAO();
	}

	public String getName() { return "addBlog.do"; }
    
    public String perform(HttpServletRequest request) {
        List<String> errors = new ArrayList<String>();
        request.setAttribute("errors",errors);
        
        try {
       		// Fetch the items now, so that in case there is no form or there are errors
       		// We can just dispatch to the JSP to show the item list (and any errors)
       		request.setAttribute("items", blogDAO.getUserBlogs(((User) request.getSession().getAttribute("user")).getUserName()));
       		
	        blogform form = blogformFactory.create(request);
        	request.setAttribute("form", form);

	        errors.addAll(form.getValidationErrors());
	        if (errors.size() > 0) {
	        	return "todolist.jsp";
	        }
	        java.util.Date utilDate = new java.util.Date();
	        java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
	        BlogBean bean = new BlogBean();
	        bean.setItem(form.getItem());
	        bean.setTitle(form.getTitle());
	        bean.setContent(form.getContent());
	        bean.setCategory(form.getCategory());
	        bean.setCreated(sqlDate);
       		bean.setUserName(((User) request.getSession().getAttribute("user")).getUserName());

       		if ( form.getAction() == null) {
       			//errors.add("Action is required: ");
       			return "todolist.jsp";
       		} else if (form.getAction().equals("Add")) {
        		blogDAO.addToTop(bean);
        	} else {
        		errors.add("Invalid action: " + form.getAction());
        	}

       		// Fetch the items again, since we modified the list
       		request.setAttribute("items", blogDAO.getUserBlogs(bean.getUserName()));
       		
       		return "todolist.jsp";

        } catch (RollbackException e) {
        	errors.add(e.getMessage());
        	return "error.jsp";
        } catch (FormBeanException e) {
        	errors.add(e.getMessage());
        	return "error.jsp";
        }
    }
}