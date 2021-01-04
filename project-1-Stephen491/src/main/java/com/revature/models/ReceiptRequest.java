package com.revature.models;

public class ReceiptRequest {
	private int reimb_owner_id;
	private int reimb_id;
	
	public int getReimb_owner_id() {
		return reimb_owner_id;
	}
	public void setReimb_owner_id(int reimb_owner_id) {
		this.reimb_owner_id = reimb_owner_id;
	}
	public int getReimb_id() {
		return reimb_id;
	}
	public void setReimb_id(int reimb_id) {
		this.reimb_id = reimb_id;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + reimb_id;
		result = prime * result + reimb_owner_id;
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
		ReceiptRequest other = (ReceiptRequest) obj;
		if (reimb_id != other.reimb_id)
			return false;
		if (reimb_owner_id != other.reimb_owner_id)
			return false;
		return true;
	}
	public ReceiptRequest(int reimb_owner_id, int reimb_id) {
		super();
		this.reimb_owner_id = reimb_owner_id;
		this.reimb_id = reimb_id;
	}
	public ReceiptRequest() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "ReceiptRequest [reimb_owner_id=" + reimb_owner_id + ", reimb_id=" + reimb_id + "]";
	}
	
	 
	
}
