package formbeans;


import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.mybeans.form.FormBean;

public class blogform extends FormBean {
	private String action;
	private String item;
	private String title;
	private String content;
	private String category;

	
	public String getAction() { return action; }
	public String getItem()   { return item;   }
	
	public void setAction(String s) { action = s;        }
	public void setItem(String s)   { item   = s.trim(); }
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}


	

	public List<String> getValidationErrors() {
		List<String> errors = new ArrayList<String>();

		//if (action == null || action.length() == 0) {
			//errors.add("Action is required");
		//}

		if ((item == null || item.length() == 0) && action != null) {
			errors.add("Blog Name is required");
		}
		
		if (errors.size() > 0) return errors;

        if (( item != null ) && item.matches(".*[<>\"].*")) errors.add("Item may not contain angle brackets or quotes");
        
        if ((action != null ) && !action.equals("Add")) errors.add("Invalid action");


		return errors;
	}
}
