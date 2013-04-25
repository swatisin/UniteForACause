package databeans;




import org.genericdao.PrimaryKey;

@PrimaryKey("id")
public class RegisterEventBean {
	private int    id;
	
	public String getEventId() {
		return eventId;
	}
	public void setEventId(String eventId) {
		this.eventId = eventId;
	}
	private String eventId;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	private String userName;
	

}
