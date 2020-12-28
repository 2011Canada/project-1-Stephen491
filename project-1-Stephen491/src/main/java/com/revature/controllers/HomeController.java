package com.revature.controllers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class HomeController {
	
	public void handleLoad(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		HttpSession sess;
		sess = req.getSession();
		
		if(sess.getAttribute("role_id")==null) {
			res.setStatus(401);
			res.getWriter().write("You shall not pass");
			
		}
		else {
			int role;
			role = (int) sess.getAttribute("role_id");
			
			if(role==2) {//employee 
				res.getWriter().write("Ok");
				res.setStatus(200);
				
				
				
			}
			else if(role==1) {//manager
				res.getWriter().write("Ok");
				res.setStatus(200);
				
				
			}
			else {
				res.setStatus(403);
				res.getWriter().write("You shall not pass");
			}
			
			
			
	
		}
		
		
		
	}
}
