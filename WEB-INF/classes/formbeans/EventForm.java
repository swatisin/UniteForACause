package formbeans;


import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.mybeans.form.FormBean;

public class EventForm extends FormBean {
	private String action;
	private String item;
	private String title;
	private String description;
	private String eventDate;
	private String category;
	private String remindarDate;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}

	private String id;
	
	public String getAction() {
		return action;
	}
	public void setAction(String action) {
		this.action = action;
	}

	public String getItem() {
		return item;
	}

	public void setItem(String item) {
		this.item = item;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getEventDate() {
		return eventDate;
	}

	public void setEventDate(String eventDate) {
		this.eventDate = eventDate;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}


	public String getRemindarDate() {
		return remindarDate;
	}

	public void setRemindarDate(String remindarDate) {
		this.remindarDate = remindarDate;
	}

	public List<String> getValidationErrors() {
		List<String> errors = new ArrayList<String>();

		//if (action == null || action.length() == 0) {
			//errors.add("Action is required");
		//}
		if ( title == null && action != null)
			errors.add("Title is required");

		if ((item == null || item.length() == 0 ) && action != null) {
			errors.add("Title is required");
		}
		
		if ( eventDate == null && action != null)
			errors.add("Event Date is required");
		
		if (errors.size() > 0) return errors;

        if ( (item != null ) && item.matches(".*[<>\"].*")) errors.add("Item may not contain angle brackets or quotes");
        
     //   if (!action.equals("Add Event") &&  !action.equals("Register")) errors.add("Invalid action");


		return errors;
	}
}
