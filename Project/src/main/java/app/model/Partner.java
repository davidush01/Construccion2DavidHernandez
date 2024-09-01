package app.model;

import java.sql.Date;

public class Partner {
	private long id;
	private User userTol;
	private double amount;
	private boolean type;
	private Date creationDate;
	public Partner() {
		
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public User getUserTol() {
		return userTol;
	}
	public void setUserTol(User userTol) {
		this.userTol = userTol;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public boolean isType() {
		return type;
	}
	public void setType(boolean type) {
		this.type = type;
	}
	public Date getCreationDate() {
		return creationDate;
	}
	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}
	

}
