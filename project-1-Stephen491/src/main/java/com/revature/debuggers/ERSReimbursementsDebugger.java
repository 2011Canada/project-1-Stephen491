package com.revature.debuggers;

import java.util.ArrayList;
import java.util.List;

import com.revature.models.Reimbursement;
import com.revature.repository.ERSReimbursementsDao;
import com.revature.repository.ERSReimbursementsDaoImpl;

public class ERSReimbursementsDebugger {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ERSReimbursementsDao reimbDao = new ERSReimbursementsDaoImpl();
		List<Reimbursement> theList = new ArrayList<>();
		theList = reimbDao.getCurrentUserReimbursements(1);
		
		System.out.println(theList.get(0).getDescription());
		
	}

}
