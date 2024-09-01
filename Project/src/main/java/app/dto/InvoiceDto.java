package app.dto;

import java.sql.Date;

public class InvoiceDto {
	private long id;
	private PersonDto personId;
	private PartnerDto partnerId;
	private Date creationDate;
	private double amount;
	private String status;
	
	public InvoiceDto() {
		
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public PersonDto getPersonId() {
		return personId;
	}

	public void setPersonId(PersonDto personId) {
		this.personId = personId;
	}

	public PartnerDto getPartnerId() {
		return partnerId;
	}

	public void setPartnerId(PartnerDto partnerId) {
		this.partnerId = partnerId;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	
	

}
