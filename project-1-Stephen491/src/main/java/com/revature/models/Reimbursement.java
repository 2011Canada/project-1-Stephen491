package com.revature.models;

import java.sql.Timestamp;
import java.time.OffsetDateTime;

public class Reimbursement {
	private int id;
	private double amount;
	private String description;
	private Timestamp dateSubmitted;
	private Timestamp dateResolved; 
	private int author;
	private String author_username;
	private String author_firstName;
	private String author_lastName;
	private String author_email;
	private int resolver;
	private String resolver_username;
	private String resolver_firstName;
	private String resolver_lastName;
	private String resolver_email;
	private int status_id;
	
	@Override
	public String toString() {
		return "Reimbursement [id=" + id + ", amount=" + amount + ", description=" + description + ", dateSubmitted="
				+ dateSubmitted + ", dateResolved=" + dateResolved + ", author=" + author + ", author_username="
				+ author_username + ", author_firstName=" + author_firstName + ", author_lastName=" + author_lastName
				+ ", author_email=" + author_email + ", resolver=" + resolver + ", resolver_username="
				+ resolver_username + ", resolver_firstName=" + resolver_firstName + ", resolver_lastName="
				+ resolver_lastName + ", resolver_email=" + resolver_email + ", status_id=" + status_id + ", status="
				+ status + ", type_id=" + type_id + ", type=" + type + "]";
	}
	public Reimbursement(int id, double amount, String description, Timestamp dateSubmitted, Timestamp dateResolved,
			int author, String author_username, String author_firstName, String author_lastName, String author_email,
			int resolver, String resolver_username, String resolver_firstName, String resolver_lastName,
			String resolver_email, int status_id, String status, int type_id, String type) {
		super();
		this.id = id;
		this.amount = amount;
		this.description = description;
		this.dateSubmitted = dateSubmitted;
		this.dateResolved = dateResolved;
		this.author = author;
		this.author_username = author_username;
		this.author_firstName = author_firstName;
		this.author_lastName = author_lastName;
		this.author_email = author_email;
		this.resolver = resolver;
		this.resolver_username = resolver_username;
		this.resolver_firstName = resolver_firstName;
		this.resolver_lastName = resolver_lastName;
		this.resolver_email = resolver_email;
		this.status_id = status_id;
		this.status = status;
		this.type_id = type_id;
		this.type = type;
	}
	public String getAuthor_username() {
		return author_username;
	}
	public void setAuthor_username(String author_username) {
		this.author_username = author_username;
	}
	public String getAuthor_firstName() {
		return author_firstName;
	}
	public void setAuthor_firstName(String author_firstName) {
		this.author_firstName = author_firstName;
	}
	public String getAuthor_lastName() {
		return author_lastName;
	}
	public void setAuthor_lastName(String author_lastName) {
		this.author_lastName = author_lastName;
	}
	public String getAuthor_email() {
		return author_email;
	}
	public void setAuthor_email(String author_email) {
		this.author_email = author_email;
	}
	public String getResolver_username() {
		return resolver_username;
	}
	public void setResolver_username(String resolver_username) {
		this.resolver_username = resolver_username;
	}
	public String getResolver_firstName() {
		return resolver_firstName;
	}
	public void setResolver_firstName(String resolver_firstName) {
		this.resolver_firstName = resolver_firstName;
	}
	public String getResolver_lastName() {
		return resolver_lastName;
	}
	public void setResolver_lastName(String resolver_lastName) {
		this.resolver_lastName = resolver_lastName;
	}
	public String getResolver_email() {
		return resolver_email;
	}
	public void setResolver_email(String resolver_email) {
		this.resolver_email = resolver_email;
	}
	private String status;
	private int type_id;
	private String type;
	
	
	public Reimbursement(int id, double amount, String description, Timestamp dateSubmitted,
			Timestamp dateResolved, int author, int resolver, int status_id, String status, int type_id,
			String type) {
		super();
		this.id = id;
		this.amount = amount;
		this.description = description;
		this.dateSubmitted = dateSubmitted;
		this.dateResolved = dateResolved;
		this.author = author;
		this.resolver = resolver;
		this.status_id = status_id;
		this.status = status;
		this.type_id = type_id;
		this.type = type;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Timestamp getDateSubmitted() {
		return dateSubmitted;
	}
	public void setDateSubmitted(Timestamp dateSubmitted) {
		this.dateSubmitted = dateSubmitted;
	}
	public Timestamp getDateResolved() {
		return dateResolved;
	}
	public void setDateResolved(Timestamp dateResolved) {
		this.dateResolved = dateResolved;
	}
	public int getAuthor() {
		return author;
	}
	public void setAuthor(int author) {
		this.author = author;
	}
	public int getResolver() {
		return resolver;
	}
	public void setResolver(int resolver) {
		this.resolver = resolver;
	}
	public int getStatus_id() {
		return status_id;
	}
	public void setStatus_id(int status_id) {
		this.status_id = status_id;
	}
	public int getType_id() {
		return type_id;
	}
	public void setType_id(int type_id) {
		this.type_id = type_id;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(amount);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + author;
		result = prime * result + ((author_email == null) ? 0 : author_email.hashCode());
		result = prime * result + ((author_firstName == null) ? 0 : author_firstName.hashCode());
		result = prime * result + ((author_lastName == null) ? 0 : author_lastName.hashCode());
		result = prime * result + ((author_username == null) ? 0 : author_username.hashCode());
		result = prime * result + ((dateResolved == null) ? 0 : dateResolved.hashCode());
		result = prime * result + ((dateSubmitted == null) ? 0 : dateSubmitted.hashCode());
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + id;
		result = prime * result + resolver;
		result = prime * result + ((resolver_email == null) ? 0 : resolver_email.hashCode());
		result = prime * result + ((resolver_firstName == null) ? 0 : resolver_firstName.hashCode());
		result = prime * result + ((resolver_lastName == null) ? 0 : resolver_lastName.hashCode());
		result = prime * result + ((resolver_username == null) ? 0 : resolver_username.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		result = prime * result + status_id;
		result = prime * result + ((type == null) ? 0 : type.hashCode());
		result = prime * result + type_id;
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Reimbursement other = (Reimbursement) obj;
		if (Double.doubleToLongBits(amount) != Double.doubleToLongBits(other.amount))
			return false;
		if (author != other.author)
			return false;
		if (author_email == null) {
			if (other.author_email != null)
				return false;
		} else if (!author_email.equals(other.author_email))
			return false;
		if (author_firstName == null) {
			if (other.author_firstName != null)
				return false;
		} else if (!author_firstName.equals(other.author_firstName))
			return false;
		if (author_lastName == null) {
			if (other.author_lastName != null)
				return false;
		} else if (!author_lastName.equals(other.author_lastName))
			return false;
		if (author_username == null) {
			if (other.author_username != null)
				return false;
		} else if (!author_username.equals(other.author_username))
			return false;
		if (dateResolved == null) {
			if (other.dateResolved != null)
				return false;
		} else if (!dateResolved.equals(other.dateResolved))
			return false;
		if (dateSubmitted == null) {
			if (other.dateSubmitted != null)
				return false;
		} else if (!dateSubmitted.equals(other.dateSubmitted))
			return false;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (id != other.id)
			return false;
		if (resolver != other.resolver)
			return false;
		if (resolver_email == null) {
			if (other.resolver_email != null)
				return false;
		} else if (!resolver_email.equals(other.resolver_email))
			return false;
		if (resolver_firstName == null) {
			if (other.resolver_firstName != null)
				return false;
		} else if (!resolver_firstName.equals(other.resolver_firstName))
			return false;
		if (resolver_lastName == null) {
			if (other.resolver_lastName != null)
				return false;
		} else if (!resolver_lastName.equals(other.resolver_lastName))
			return false;
		if (resolver_username == null) {
			if (other.resolver_username != null)
				return false;
		} else if (!resolver_username.equals(other.resolver_username))
			return false;
		if (status == null) {
			if (other.status != null)
				return false;
		} else if (!status.equals(other.status))
			return false;
		if (status_id != other.status_id)
			return false;
		if (type == null) {
			if (other.type != null)
				return false;
		} else if (!type.equals(other.type))
			return false;
		if (type_id != other.type_id)
			return false;
		return true;
	}
	public Reimbursement() {
		super();
		// TODO Auto-generated constructor stub
	}

	
	
	
	
	
}
