package com.revature.models;

public class ReimbursementStatusUpdate {
	private int status_id;
	private int reimb_id;
	private int resolver;
	
	
	public int getStatus_id() {
		return status_id;
	}
	public void setStatus_id(int status_id) {
		this.status_id = status_id;
	}
	public int getReimb_id() {
		return reimb_id;
	}
	public void setReimb_id(int reimb_id) {
		this.reimb_id = reimb_id;
	}
	public ReimbursementStatusUpdate(int status_id, int reimb_id) {
		super();
		this.status_id = status_id;
		this.reimb_id = reimb_id;
	}
	public ReimbursementStatusUpdate() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "ReimbursementStatusUpdate [status_id=" + status_id + ", reimb_id=" + reimb_id + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + reimb_id;
		result = prime * result + status_id;
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
		ReimbursementStatusUpdate other = (ReimbursementStatusUpdate) obj;
		if (reimb_id != other.reimb_id)
			return false;
		if (status_id != other.status_id)
			return false;
		return true;
	}
	
}
