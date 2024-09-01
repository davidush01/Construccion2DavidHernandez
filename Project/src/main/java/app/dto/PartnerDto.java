package app.dto;

import java.sql.Date;


public class PartnerDto {
	private long id;
	private UserDto userTol;
	private double amount;
	private boolean type;
	private Date creationDate;
	
	public PartnerDto() {
		
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public UserDto getUserTol() {
		return userTol;
	}

	public void setUserTol(UserDto userTol) {
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
	public void setCreationDate(Date date) {
		this.creationDate=date;
	}

	
	


	

}
