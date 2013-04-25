package databeans;



import java.sql.Date;

import org.genericdao.PrimaryKey;

@PrimaryKey("id")
public class BlogBean {
	private int    id;
	private String item;
	private String title;
	private String content;
	private Date created;
	private String category;
	private String userName;
	private String userid;
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
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Date getCreated() {
		return created;
	}
	public void setCreated(Date created) {
		this.created = created;
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
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}

}
