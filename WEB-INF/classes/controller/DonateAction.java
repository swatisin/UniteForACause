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

public class DonateAction extends Action {
	private FormBeanFactory<DonationForm>  DonateFormFactory  = FormBeanFactory.getInstance(DonationForm.class);
	
	private CharityDAO charityDAO;
	private DonationDAO donationDAO;

	public DonateAction(Model model) {
		charityDAO = model.getCharityDAO();
		donationDAO = model.getDonationDAO();
	}

	public String getName() { return "registerDonations.do"; }
    
    public String perform(HttpServletRequest request) {
        List<String> errors = new ArrayList<String>();
        request.setAttribute("errors",errors);
        
        try {
       		// Fetch the items now, so that in case there is no form or there are errors
       		// We can just dispatch to the JSP to show the item list (and any errors)
       		
        	DonationForm form = DonateFormFactory.create(request);
       		System.out.println(form.getId());

       		request.setAttribute("item", charityDAO.getItem(form.getId()));
        	request.setAttribute("form", form);
       		System.out.println(form.getAction());
       		System.out.println(form.getId());
       		
	        
       		System.out.println("#1");

	        DonationBean bean = new DonationBean();
	        
	        bean.setDonationId(form.getId());
       		bean.setUserName(((User) request.getSession().getAttribute("user")).getUserName());
       		System.out.println("#2");

        	if ( (form.getAction() != null) && form.getAction().equals("Donate") ){
           		System.out.println("#3 and in test mode");
           		System.out.println(charityDAO.getItem(form.getId()));
           		
           		request.setAttribute("itemSent", charityDAO.getItem(form.getId()));
           		request.setAttribute("flag", "1");
            	request.setAttribute("items", charityDAO.getUserCharity(((User) request.getSession().getAttribute("user")).getUserName()));
           		donationDAO.addToTop(bean); 
           		return "charity.jsp";
           		//donationDAO.addToTop(bean);     			
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