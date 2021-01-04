package com.revature.controllers;

import java.io.BufferedReader;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.List;

import javax.json.JsonArray;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.revature.models.Reimbursement;
import com.revature.models.ReimbursementForm;
import com.revature.models.ReimbursementStatusUpdate;
import com.revature.repository.ERSReimbursementStatusDAO;
import com.revature.repository.ERSReimbursementStatusDAOImpl;
import com.revature.repository.ERSReimbursementTypeDAO;
import com.revature.repository.ERSReimbursementTypeDaoImpl;
import com.revature.repository.ERSReimbursementsDao;
import com.revature.repository.ERSReimbursementsDaoImpl;

public class ReimbursementsController {

	ObjectMapper om = new ObjectMapper(); 
	ERSReimbursementsDao ersReimbursementDao = new ERSReimbursementsDaoImpl();
	ERSReimbursementTypeDAO ersReimbursementTypeDao = new ERSReimbursementTypeDaoImpl();
	ERSReimbursementStatusDAO ersReimbursementStatusDao = new ERSReimbursementStatusDAOImpl();
	Gson gson = new Gson(); 
	
	public void handlePost(HttpServletRequest req, HttpServletResponse res) throws IOException{
		if(req.getSession().getAttribute("role_id")==null) {
			res.sendError(401);
		}
		else if((int)req.getSession().getAttribute("role_id")==2) {
			System.out.println("reimb controller called");
			String line;
			
			ReimbursementForm reimb = om.readValue(req.getInputStream(), ReimbursementForm.class);
			System.out.println(reimb.getDescription());
			//insert into database
			String reimbTypeString = ersReimbursementTypeDao.getTypeName(reimb.getType_id());
			Timestamp dateSubmitted = new Timestamp(System.currentTimeMillis()); 
			byte[] receipt = null;
			boolean hasReceipt = false;
			if(reimb.getReceipt()!=null) {	
				receipt = reimb.getReceipt();
				hasReceipt = true;
			}
			int author = (int)req.getSession().getAttribute("userid");
			String reimbStatusString = ersReimbursementStatusDao.getStatusName(1);
			
			if(ersReimbursementDao.addReimbursement(reimb.getAmount(), reimb.getDescription(), dateSubmitted, author, 1, reimb.getType_id(), receipt, hasReceipt)) {
				res.setStatus(200);
			}
			
			
			
			
			
		}
		else if((int)req.getSession().getAttribute("role_id")==1) {
			
			
			
			
		}
		else {
			res.sendError(403);
		}
			

			
	}
		
		
		
	
	public void handleGet(HttpServletRequest req, HttpServletResponse res) throws IOException{
		if(req.getSession().getAttribute("role_id")==null) {
			res.sendError(401);
		}
		else if((int)req.getSession().getAttribute("role_id")==2) {
			List<Reimbursement> list = ersReimbursementDao.getCurrentUserReimbursements((int)req.getSession().getAttribute("userid"));
			String listToSend = gson.toJson(list);
			res.setStatus(200);
			res.getWriter().write(listToSend);
			
			
		}
		else if((int)req.getSession().getAttribute("role_id")==1) {
			
			
			
			
			
		}
		else {
			res.sendError(403);
		}
			
	
	}
	public void handleFMGet(HttpServletRequest req, HttpServletResponse res) throws IOException{
		//Needs to also get author first/last name, author username, author email, resolver first and last name, and resolver email
		if(req.getSession().getAttribute("role_id")==null) {
			res.sendError(401);
		}
		else if((int)req.getSession().getAttribute("role_id")==1) {
			List<Reimbursement> list = ersReimbursementDao.getAllReimbursements();
			String listToSend = gson.toJson(list);
			res.setStatus(200);
			res.getWriter().write(listToSend);
			
			
		}
		else if(!((int)req.getSession().getAttribute("role_id")==1)) {
			res.sendError(403);
		}
		else {
			res.sendError(403);
		}

			
	}
	public void handleFMPost(HttpServletRequest req, HttpServletResponse res) throws IOException{
		if(req.getSession().getAttribute("role_id")==null) {
			res.sendError(401);
		}
		else if((int)req.getSession().getAttribute("role_id")==1) {
			System.out.println("Update reimburse called");
			ReimbursementStatusUpdate reimb = om.readValue(req.getInputStream(), ReimbursementStatusUpdate.class);
			System.out.println("Update reimburse called2");
			int resolver = (int)req.getSession().getAttribute("userid");
			int reimb_id = reimb.getReimb_id();
			int status_id = reimb.getStatus_id();
			Timestamp solved = new Timestamp(System.currentTimeMillis()); 
			
			if(ersReimbursementDao.updateReimbursementStatus(resolver, status_id, reimb_id, solved)) {
				System.out.println("Reimbursement status updated");
				res.setStatus(200);
			}
			else {
				System.out.println("Oops something went wrong!");
			}
			
			
			
			
			
		}
		else if(!((int)req.getSession().getAttribute("role_id")==1)) {
			res.sendError(403);
		}
		else {
			res.sendError(403);
		}

			
	}
	
		
}

