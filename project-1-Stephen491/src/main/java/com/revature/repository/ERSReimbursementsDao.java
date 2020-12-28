package com.revature.repository;

import java.util.List;

import com.revature.models.Reimbursement;

public interface ERSReimbursementsDao {
	public List<Reimbursement> getAllPendingReimbursements();
	public List<Reimbursement> getCurrentUserReimbursements(int userid);
	public Reimbursement getReimbursement(int reimb_id);
	public boolean resolveReimbursement(int reimb_id, boolean decision);
	public List<Reimbursement> getReimbursementsByStatus(int status_id);
	public boolean addReimbursement(Reimbursement reimb);
	
	
	
}
