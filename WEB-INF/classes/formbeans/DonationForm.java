package formbeans;

import java.util.ArrayList;
import java.util.List;

import org.mybeans.form.FormBean;

public class DonationForm extends FormBean {

	private String address;
	private String amount;
	private String nameOfcardHolder;
	private String description;
	private String title;
	private String cardNumber;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	private String id;
	public String getCardNumber() {
		return cardNumber;
	}
	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}
	public String getCvv() {
		return cvv;
	}
	public void setCvv(String cvv) {
		this.cvv = cvv;
	}
	private String cvv;
    
    public String getAction() {
		return action;
	}
	public void setAction(String action) {
		this.action = action;
	}
	String action;
    public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	String category;
    public String getCreated() {
		return created;
	}
	public void setCreated(String created) {
		this.created = created;
	}
	String created;
    
    public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
	public String getNameOfcardHolder() {
		return nameOfcardHolder;
	}
	public void setNameOfcardHolder(String nameOfcardHolder) {
		this.nameOfcardHolder = nameOfcardHolder;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}

	public List<String> getValidationErrors() {
		List<String> errors = new ArrayList<String>();
		Double checkPrice;
		//if (action == null || action.length() == 0) {
			//errors.add("Action is required");
		//}

		if ((address == null || address.length() == 0) && action != null) {
			errors.add("Blog Name is required");
		}
		
		if (errors.size() > 0) return errors;

       // if (( cardNumber != null ) && cardNumber.matches(".*[<>\" a-z A-Z].*") && !(cardNumber.length() != 16)) errors.add("Card Number Error");
        
        if ((action != null ) && !action.equals("Add")) errors.add("Invalid action");

        if ((amount == null || amount.length() == 0 ) && action != null) {
			errors.add("Kindly Enter the amount you want to donate ");
		} else {
			if ( action != null)
			try {
				 checkPrice = Double.parseDouble(amount);
			} catch (NumberFormatException e) {      
					errors.add("Invalid Amount . Kindly change it");
		}
		
	}
        return errors;
	}
}
