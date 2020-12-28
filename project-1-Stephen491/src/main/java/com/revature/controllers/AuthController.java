package com.revature.controllers;

import java.io.IOException;
import com.revature.models.Credentials;
import com.revature.models.User;
import com.revature.services.LoginService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.ObjectMapper;



public class AuthController{
	
	ObjectMapper om = new ObjectMapper(); 
	LoginService ls = new LoginService(); 
	
	
	public void userLogin(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		Credentials cred = om.readValue(req.getInputStream(), Credentials.class);
		User currentUser;
		int userid;
		currentUser = ls.authenticateUser(cred);
		if(currentUser==null) {
			res.sendError(401);
			
		}
		else {
			userid = currentUser.getId();
			HttpSession sess = req.getSession();
			sess.setAttribute("userid", userid);
			sess.setAttribute("username", currentUser.getUsername());
			sess.setAttribute("email", currentUser.getEmail());
			sess.setAttribute("role_id", currentUser.getRole());
			sess.setAttribute("first-name", currentUser.getFirstName());
			sess.setAttribute("last-name", currentUser.getFirstName());
			
			
			if(currentUser.getRole()==2) {
				System.out.println("going to employee home");
				res.sendRedirect("http://localhost:8080/project-1-Stephen491/employeehome.html");
				res.setStatus(200);
				return ;
			}
			else if(currentUser.getRole()==1) {
				System.out.println("going to financial manager home");
				res.sendRedirect("http://localhost:8080/project-1-Stephen491/financialmanagerhome.html");
				res.setStatus(200);
				return ;
			}
		}
		

	}
	
	
}