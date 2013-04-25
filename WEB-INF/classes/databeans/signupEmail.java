package databeans;

import org.genericdao.PrimaryKey;

@PrimaryKey("id")
public class signupEmail {

	private String emailAddress;
	public String getEmailAddress() {
		return emailAddress;
	}
	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	private int    id; 
}
