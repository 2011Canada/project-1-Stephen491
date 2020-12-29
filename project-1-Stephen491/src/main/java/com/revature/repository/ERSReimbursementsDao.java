package com.revature.repository;

import java.io.File;
import java.sql.Timestamp;
import java.util.List;

import com.revature.models.Reimbursement;

public interface ERSReimbursementsDao {
	public List<Reimbursement> getAllReimbursements();
	public List<Reimbursement> getAllPendingReimbursements();
	public List<Reimbursement> getCurrentUserReimbursements(int userid);
	public Reimbursement getReimbursement(int reimb_id);
	public boolean resolveReimbursement(int reimb_id, boolean decision);
	public List<Reimbursement> getReimbursementsByStatus(int status_id);
	public boolean addReimbursement(Reimbursement reimb);
	public boolean addReimbursement(double amount, String description, Timestamp dateSubmitted, int author, int status_id, int type_id);
	
	
}
