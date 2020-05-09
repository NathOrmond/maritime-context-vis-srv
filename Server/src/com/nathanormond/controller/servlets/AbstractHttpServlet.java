package com.nathanormond.controller.servlets;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;

public abstract class AbstractHttpServlet extends HttpServlet implements IHttpServlet {
	
	private static final long serialVersionUID = 908349235659352385L;

	@Override
	public RequestDispatcher createRequestDispatcher(HttpServletRequest request, String pageURL) { 
		return request.getRequestDispatcher(pageURL);
	}
	
	@Override
	public RequestDispatcher createPOSTRequestDispatcher(HttpServletRequest request) { 
		return createRequestDispatcher(request, getPOSTPageURL());
	}
	
	@Override
	public RequestDispatcher createGETRequestDispatcher(HttpServletRequest request) { 
		return createRequestDispatcher(request, getGETPageURL());
	}
	
	@Override
	public RequestDispatcher createPUTRequestDispatcher(HttpServletRequest request) { 
		return createRequestDispatcher(request, getPUTPageURL());
	}
	
	@Override
	public RequestDispatcher createDELETERequestDispatcher(HttpServletRequest request) { 
		return createRequestDispatcher(request, getDELETEPageURL());
	}

}
