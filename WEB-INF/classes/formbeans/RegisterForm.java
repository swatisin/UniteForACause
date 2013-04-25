package formbeans;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.mybeans.form.FormBean;

//import databeans.priavte;

public class RegisterForm extends FormBean {
	private String firstName;
	private String lastName;
	private String userName;
	private String password;
	private String confirm ;
	public String getEmailAddress() {
		return emailAddress;
	}
	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	private String emailAddress;

	
	public String getFirstName() { return firstName; }
	public String getLastName()  { return lastName;  }
	public String getUserName()  { return userName;  }
	public String getPassword()  { return password;  }
	public String getConfirm()   { return confirm;   }
	
	public void setFirstName(String s) { firstName = trimAndConvert(s,"<>\"");  }
	public void setLastName(String s)  { lastName  = trimAndConvert(s,"<>\"");  }
	public void setUserName(String s)  { userName  = trimAndConvert(s,"<>\"");  }
	public void setPassword(String s)  { password  = s.trim();                  }
	public void setConfirm(String s)   { confirm   = s.trim();                  }

	public List<String> getValidationErrors() {
		List<String> errors = new ArrayList<String>();

		

		if (userName == null || userName.length() == 0) {
			errors.add("User Name is required");
		}
		
		if ( emailAddress == null || emailAddress.length() == 0)
			errors.add("Email Address is required");
		
		if (password == null || password.length() == 0) {
			errors.add("Password is required");
		}

		if (confirm == null || confirm.length() == 0) {
			errors.add("Confirm Password is required");
		}
		
		if (errors.size() > 0) {
			return errors;
		}
		
		if (!password.equals(confirm)) {
			errors.add("Passwords are not the same");
		}
		
			Pattern p = Pattern.compile("[\\w]*[[.]??[\\w- &&[^.]]*?]+?[^.]@[\\w]+[.][a-zA-Z]{2,5}+$");  
	        Matcher m = p.matcher(emailAddress);  
	        boolean result = m.matches();  
	        if (!result) errors.add("Invalid Email Address");
		return errors;
	}
}
