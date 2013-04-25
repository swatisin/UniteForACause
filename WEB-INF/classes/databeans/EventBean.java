package databeans;



import java.sql.Date;

import org.genericdao.PrimaryKey;

@PrimaryKey("id")
public class EventBean {
	private int    id;
	private String title;
	private String description;
	private Date eventDate;
	private String category;
	private String userName;
	private Date remindarDate;
	public int getPosition() {
		return position;
	}
	public void setPosition(int position) {
		this.position = position;
	}
	private int    position;

	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
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
	public Date getEventDate() {
		return eventDate;
	}
	public void setEventDate(Date eventDate) {
		this.eventDate = eventDate;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public Date getRemindarDate() {
		return remindarDate;
	}
	public void setRemindarDate(Date remindarDate) {
		this.remindarDate = remindarDate;
	}	

}
