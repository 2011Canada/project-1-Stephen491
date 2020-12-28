package com.revature.controllers;

import java.io.IOException;
import java.util.List;

import javax.json.JsonArray;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.revature.models.Reimbursement;
import com.revature.models.ReimbursementForm;
import com.revature.repository.ERSReimbursementsDao;
import com.revature.repository.ERSReimbursementsDaoImpl;

public class ReimbursementsController {

	ObjectMapper om = new ObjectMapper(); 
	ERSReimbursementsDao ersReimbursementDao = new ERSReimbursementsDaoImpl();
	Gson gson = new Gson(); 
	
	public void handlePost(HttpServletRequest req, HttpServletResponse res) throws IOException{
		if(req.getSession().getAttribute("role_id")==null) {
			res.sendError(401);
		}
		else if((int)req.getSession().getAttribute("role_id")==2) {
			System.out.println("reimb controller called");
			ReimbursementForm reimb = om.readValue(req.getInputStream(), ReimbursementForm.class);
			System.out.println(reimb.getDescription());
			
			//insert into database
			
			
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
		
	}

