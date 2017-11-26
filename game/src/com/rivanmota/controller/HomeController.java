package com.rivanmota.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;

@Path("/")
public class HomeController {
	
	@Context
	private HttpServletResponse response;
	@Context
	private HttpServletRequest request;
	
//	@GET
//	@Path("/")
//	public void index(){
//			try {
//				request.getRequestDispatcher("/index.jsp").forward(request, response);
////				response.sendRedirect(request.getContextPath() + "/index.jsp");
//			} catch (IOException | ServletException e) {
//				e.printStackTrace();
//			}
//	}

}
