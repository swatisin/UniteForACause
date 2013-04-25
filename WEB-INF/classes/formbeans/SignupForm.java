package formbeans;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.mybeans.form.FormBean;

public class SignupForm extends FormBean {
	public String getEmailAddress() {
		return emailAddress;
	}


	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}


	private String emailAddress;
	public String getButton() {
		return button;
	}


	public void setButton(String button) {
		this.button = button;
	}


	private String button ;
	
	
	public List<String> getValidationErrors() {
		List<String> errors = new ArrayList<String>();

		
		Pattern p = Pattern.compile("[\\w]*[[.]??[\\w- &&[^.]]*?]+?[^.]@[\\w]+[.][a-zA-Z]{2,5}+$");  
        Matcher m = p.matcher(emailAddress);  
        boolean result = m.matches();  
        if (!result) errors.add("Invalid Email Address");

		return errors;

	}


}
