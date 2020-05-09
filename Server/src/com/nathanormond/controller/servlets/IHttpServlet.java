package com.nathanormond.controller.servlets;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

public interface IHttpServlet {
	
	public abstract String getPOSTPageURL();
	
	public abstract String getGETPageURL();
	
	public abstract String getPUTPageURL();
	
	public abstract String getDELETEPageURL();
	
	public abstract HttpServletRequest createPOSTServletRequest();
	
	public abstract HttpServletRequest createGETServletRequest();
	
	public abstract HttpServletRequest createPUTServletRequest();
	
	public abstract HttpServletRequest createDELETEServletRequest();

	public RequestDispatcher createRequestDispatcher(HttpServletRequest request, String pageURL);
	
	public RequestDispatcher createPOSTRequestDispatcher(HttpServletRequest request);
	
	public RequestDispatcher createGETRequestDispatcher(HttpServletRequest request);
	
	public RequestDispatcher createPUTRequestDispatcher(HttpServletRequest request);
	
	public RequestDispatcher createDELETERequestDispatcher(HttpServletRequest request);
	
}
