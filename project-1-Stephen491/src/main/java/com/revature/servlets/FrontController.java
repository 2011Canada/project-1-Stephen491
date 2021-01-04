package com.revature.servlets;


import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.revature.controllers.AuthController;
import com.revature.controllers.ErrorController;
import com.revature.controllers.HomeController;
import com.revature.controllers.LogoutController;
import com.revature.controllers.ReceiptsController;
import com.revature.controllers.ReimbursementsController;


	public class FrontController extends HttpServlet {
		
		private AuthController authController = new AuthController();
		
		private ErrorController errorController = new ErrorController();
		
		private HomeController homeController = new HomeController();
		
		private ReimbursementsController reimbursementsController =  new ReimbursementsController();
		
		private LogoutController logoutController = new LogoutController();
		
		private ReceiptsController receiptsController = new ReceiptsController(); 
		
		protected void directControlRouter(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
			//how to get a value from your init params

			
			//be our front controller
			String URI = req.getRequestURI().substring(req.getContextPath().length(), 
														req.getRequestURI().length());
			
			System.out.println(URI);
			switch (URI) {
				case "/": {
					switch (req.getMethod()) {
						case "GET": {
							System.out.println(req.getSession());
							System.out.println(req.getSession().getAttribute("role_id"));
							if(req.getSession().getAttribute("role_id")==null) {
								res.sendRedirect("http://localhost:8080/project-1-Stephen491/login.html");
							}
							else if((int)req.getSession().getAttribute("role_id")==2) {
								res.sendRedirect("http://localhost:8080/project-1-Stephen491/employeehome.html");
							}
							else if((int)req.getSession().getAttribute("role_id")==1) {
								res.sendRedirect("http://localhost:8080/project-1-Stephen491/financialmanagerhome.html");
							}
							else{
								res.sendRedirect("http://localhost:8080/project-1-Stephen491/login.html");
							}

						}
						break;
				}
					break;
				}
				
				case "/login":{
					switch (req.getMethod()) {
						case "GET":{
							res.setStatus(400);
							res.getWriter().write("Method Not Supported");
							break;
						}
						case "POST":{
							authController.userLogin(req, res);
							break;
						}
						case "PUT":{
							res.setStatus(400);
							res.getWriter().write("Method Not Supported");
							break;
						}
						case "DELETE":{
							res.setStatus(400);
							res.getWriter().write("Method Not Supported");
							break;
						}
						default:{
							res.setStatus(400);
							res.getWriter().write("Method Not Supported");
							break;
						}
					
					}
					break;
				}
				
				case "/home": {
					switch(req.getMethod()) {
						case "GET" :{
							homeController.handleLoad(req, res);
							break;
						}		
					}
					
					
					
				}
				case "/users": {
					switch (req.getMethod()) {
						case "GET":{
							//userController.findAllUsers(req, res);
							break;
						}
						case "POST":{
							res.setStatus(400);
							res.getWriter().write("Method Not Supported");
							break;
						}
						case "PUT":{
							res.setStatus(400);
							res.getWriter().write("Method Not Supported");
							break;
						}
						case "DELETE":{
							res.setStatus(400);
							res.getWriter().write("Method Not Supported");
							break;
						}
						default:{
							res.setStatus(400);
							res.getWriter().write("Method Not Supported");
							break;
						}
					}
					break;
				}
				case "/reimbursements": {
					switch (req.getMethod()) {
						case "GET":{
							
							reimbursementsController.handleGet(req, res);
							break;
						}
						case "POST":{
							System.out.println("reimb in front controller");
							reimbursementsController.handlePost(req, res);
							break;
						}
						case "PUT":{
							res.setStatus(400);
							res.getWriter().write("Method Not Supported");
							break;
						}
						case "DELETE":{
							res.setStatus(400);
							res.getWriter().write("Method Not Supported");
							break;
						}
						default:{
							res.setStatus(400);
							res.getWriter().write("Method Not Supported");
							break;
						}
					}
					break;
				}
				case "/allreimbursements": {
					switch (req.getMethod()) {
						case "GET":{
							reimbursementsController.handleFMGet(req, res);
							break;
						}
						case "POST":{
							res.setStatus(400);
							res.getWriter().write("Method Not Supported");
							break;
						}
						case "PUT":{
							res.setStatus(400);
							res.getWriter().write("Method Not Supported");
							break;
						}
						case "DELETE":{
							res.setStatus(400);
							res.getWriter().write("Method Not Supported");
							break;
						}
						default:{
							res.setStatus(400);
							res.getWriter().write("Method Not Supported");
							break;
						}
					}
					break;
				}
				case "/updatereimbursements": {
					switch (req.getMethod()) {
						case "GET":{
							res.setStatus(400);
							res.getWriter().write("Method Not Supported");
							break;
						}
						case "POST":{
							reimbursementsController.handleFMPost(req, res);
							break;
						}
						case "PUT":{
							res.setStatus(400);
							res.getWriter().write("Method Not Supported");
							break;
						}
						case "DELETE":{
							res.setStatus(400);
							res.getWriter().write("Method Not Supported");
							break;
						}
						default:{
							res.setStatus(400);
							res.getWriter().write("Method Not Supported");
							break;
						}
					}
					break;
				}
				case "/receipt": {
					switch (req.getMethod()) {
						case "GET":{
							res.setStatus(400);
							res.getWriter().write("Method Not Supported");
							break;
						}
						case "POST":{

							receiptsController.handleReceiptRequest(req, res);
							break;
						}
						case "PUT":{
							res.setStatus(400);
							res.getWriter().write("Method Not Supported");
							break;
						}
						case "DELETE":{
							res.setStatus(400);
							res.getWriter().write("Method Not Supported");
							break;
						}
						default:{
							res.setStatus(400);
							res.getWriter().write("Method Not Supported");
							break;
						}
					}
					break;
				}
				case "/logout": {
					switch (req.getMethod()) {
						case "GET":{
				
							logoutController.handleLogout(req, res);
							break;
						}
						case "POST":{
							res.setStatus(400);
							res.getWriter().write("Method Not Supported");
							break;
						}
						case "PUT":{
							res.setStatus(400);
							res.getWriter().write("Method Not Supported");
							break;
						}
						case "DELETE":{
							res.setStatus(400);
							res.getWriter().write("Method Not Supported");
							break;
						}
						default:{
							res.setStatus(400);
							res.getWriter().write("Method Not Supported");
							break;
						}
					}
					break;
				}
				
				default:{
					res.setStatus(404);
					res.getWriter().write("No Such Resource");
					break;
				}
				
			}
			
			
			
		}
		
		
		protected void directControl(HttpServletRequest request, HttpServletResponse response) throws IOException {
			//to handle all internal errors/exceptions
			try {
				directControlRouter(request, response);
			}catch (Throwable t) {
				//errorController.handle(request, response, t);//go to the error controller
			}
		}

		/**
		 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
		 *      response)
		 */
		protected void doGet(HttpServletRequest request, HttpServletResponse response)
				throws ServletException, IOException {
			directControl(request, response);
		}

		/**
		 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
		 *      response)
		 */
		protected void doPost(HttpServletRequest request, HttpServletResponse response)
				throws ServletException, IOException {
			directControl(request, response);
		}

		/**
		 * @see HttpServlet#doPut(HttpServletRequest, HttpServletResponse)
		 */
		protected void doPut(HttpServletRequest request, HttpServletResponse response)
				throws ServletException, IOException {
			directControl(request, response);
		}

		/**
		 * @see HttpServlet#doDelete(HttpServletRequest, HttpServletResponse)
		 */
		protected void doDelete(HttpServletRequest request, HttpServletResponse response)
				throws ServletException, IOException {
			directControl(request, response);
		}

	}
	
