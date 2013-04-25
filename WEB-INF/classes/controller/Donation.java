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
import java.util.HashMap;
import java.util.Map;

import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.Charge;
import java.util.Date;


public class Donation extends Action {
	private FormBeanFactory<DonationForm>  DonationFormFactory  = FormBeanFactory.getInstance(DonationForm.class);
	
	private CharityDAO charityDAO;

	public Donation(Model model) {
		charityDAO = model.getCharityDAO();
	}

	public String getName() { return "donation.do"; }
    
	public String perform(HttpServletRequest request) {
        List<String> errors = new ArrayList<String>();
        request.setAttribute("errors",errors);
        String flag = (String)request.getAttribute("flag");
        CharityBean chBean = (CharityBean) request.getAttribute("itemSent");
			System.out.println(flag + chBean);
        
        try {
       		// Fetch the items now, so that in case there is no form or there are errors
       		// We can just dispatch to the JSP to show the item list (and any errors)
       		if ( (flag != null) && (chBean != null) )
       			System.out.println("HUrrau!!");
        	
        	request.setAttribute("items", charityDAO.getUserCharity(((User) request.getSession().getAttribute("user")).getUserName()));
       		
       		DonationForm form = DonationFormFactory.create(request);
        	request.setAttribute("form", form);

	        errors.addAll(form.getValidationErrors());
	        if (errors.size() > 0) {
	        	return "charity.jsp";
	        }
	      //  System.out.println("Date is " + form.getE);
	        java.sql.Date sqlDate = null;

	        java.util.Date utilDate = new Date();
	        sqlDate = new java.sql.Date(utilDate.getTime());

	    
	        CharityBean bean = new CharityBean();
	        bean.setDescription(form.getDescription());
	        bean.setTitle(form.getTitle());
	        bean.setCategory(form.getCategory());
	        bean.setCreated(sqlDate);
	        
	        if (form.getAmount() == null )
	        	form.setAmount("0.0");
	        bean.setAmount(Double.parseDouble(form.getAmount()));
       		bean.setUserName(((User) request.getSession().getAttribute("user")).getUserName());
       		if ( form.getAction() == null){
       			System.out.println("Failing here");
       			form.setAction("Add");
       		}
        	if (form.getAction().equals("Add")) {
        		charityDAO.addToTop(bean);
        	} else if (form.getAction().equals("Donate")) {
        		System.out.println("Donate noW");
        		//charityDAO.addToBottom(bean);
        	} else {
        		errors.add("Invalid action: " + form.getAction());
        	}

       		// Fetch the items again, since we modified the list
       		request.setAttribute("items", charityDAO.getUserCharity(bean.getUserName()));
       		
       		return "charity.jsp";

        } catch (RollbackException e) {
        	errors.add(e.getMessage());
        	return "error.jsp";
        } catch (FormBeanException e) {
        	errors.add(e.getMessage());
        	return "error.jsp";
        }
    }
}