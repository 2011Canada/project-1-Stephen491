package com.revature.services;

import java.util.List;

import com.revature.models.Reimbursement;
import com.revature.repository.ERSReimbursementsDao;
import com.revature.repository.ERSReimbursementsDaoImpl;

public class TicketService {
	
	ERSReimbursementsDao ersReimbursementsDao = new ERSReimbursementsDaoImpl();
	
	public List<Reimbursement> getPastTickets(int userid) {
		return ersReimbursementsDao.getCurrentUserReimbursements(userid);
		
	}
	
	
	
	
	
	
	
	
}
