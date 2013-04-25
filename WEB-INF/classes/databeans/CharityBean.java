package databeans;



import java.sql.Date;

import org.genericdao.PrimaryKey;

@PrimaryKey("id")
public class CharityBean {
	private int    id;
	private String title;
	private String category;
	private double amount;
	private Date created;
	private String userName;
	private String item;
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	private String description;

	public String getItem() {
		return item;
	}
	public void setItem(String item) {
		this.item = item;
	}

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
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public Date getCreated() {
		return created;
	}
	public void setCreated(Date created) {
		this.created = created;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
}
