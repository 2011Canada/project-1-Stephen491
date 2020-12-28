package com.revature.controllers;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LogoutController {
	public void handleLogout(HttpServletRequest req, HttpServletResponse res) throws IOException{
		req.getSession().invalidate();
		res.setStatus(200);
		res.setHeader("Cache-Control","no-cache");
		res.setHeader("Cache-Control","no-store");
		res.setHeader("Pragma","no-cache");
		res.setDateHeader ("Expires", 0);
		res.sendRedirect("http://localhost:8080/project-1-Stephen491/login.html");
	}
}
