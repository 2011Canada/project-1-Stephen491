package com.revature.servlets;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpFilter;

public class AuthFilter extends HttpFilter{
	
	
	public void handleFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws IOException, ServletException{
		String URI = req.getRequestURI().substring(req.getContextPath().length(), 
				req.getRequestURI().length());
		
		switch(URI) {
			case "/employeehome.html":
				switch(req.getMethod()) {
					case "GET": {
						if(req.getSession().getAttribute("role_id")==null) {
							res.sendError(401);
						}
						else if((int)req.getSession().getAttribute("role_id")==2) {
							res.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); 
					        res.setHeader("Pragma", "no-cache"); 
					        res.setDateHeader("Expires", 0); 
							chain.doFilter(req, res);
						}
						else {
							res.sendError(403);
						}
						break;
					}
				
				}//switch method
				break;
			case "/employeehome.js":
				switch(req.getMethod()) {
					case "GET": {
						if(req.getSession().getAttribute("role_id")==null) {
							res.sendError(401);
						}
						else if((int)req.getSession().getAttribute("role_id")==2) {
							res.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); 
					        res.setHeader("Pragma", "no-cache"); 
					        res.setDateHeader("Expires", 0); 
							chain.doFilter(req, res);
						}
						else {
							res.sendError(403);
						}
						break;
					}
				
				}//switch method
				break;
			case "/employeehome.css":
				switch(req.getMethod()) {
					case "GET": {
						if(req.getSession().getAttribute("role_id")==null) {
							res.sendError(401);
						}
						else if((int)req.getSession().getAttribute("role_id")==2) {
							res.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); 
					        res.setHeader("Pragma", "no-cache"); 
					        res.setDateHeader("Expires", 0); 
							chain.doFilter(req, res);
						}
						else {
							res.sendError(403);
						}
						break;
					}
				
				}//switch method
				break;
			case "/financemanagerhome.html":
				switch(req.getMethod()) {
				case "GET": {
					System.out.println("Tried");
					if(req.getSession().getAttribute("role_id")==null) {
						res.sendError(401);
					}
					else if((int)req.getSession().getAttribute("role_id")==1) {
						res.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); 
				        res.setHeader("Pragma", "no-cache"); 
				        res.setDateHeader("Expires", 0); 
						chain.doFilter(req, res);
					}
					else {
						res.sendError(403);
					}
					break;
			}
		
		}//switch method
		break;
		}//switch location
		
	
		
	}
	
	
	public void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain) {
		try {
			handleFilter(req, res, chain);
		}
		catch(IOException e) {
			e.printStackTrace();
		}
		catch(ServletException e) {
			e.printStackTrace();
		}
		
	}
	public void destroy() {
		
	}
}
