package com.revature.servlets;

import java.io.IOException;
import com.revature.models.Credentials;
import com.revature.models.User;
import com.revature.services.LoginService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.ObjectMapper;



public class AuthenticationServlet extends HttpServlet{
	
	ObjectMapper om = new ObjectMapper(); 
	LoginService ls = new LoginService(); 
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		Credentials cred = om.readValue(req.getInputStream(), Credentials.class);
		
		User currentUser;
		int userid;
		
		currentUser = ls.authenticateUser(cred);
		if(currentUser==null) {
			resp.sendError(401);
			
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
			resp.setStatus(200);
		}
		

	}
	
	
}
