package com.nathanormond.controller.servlets.pages.homecontroller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.nathanormond.controller.servlets.AbstractHttpServlet;


@WebServlet("")
public class HomeController extends AbstractHttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public HomeController() {
        super();
    }
    
    
	/********************************************************************************************************************************************
	 * SERVER BEHAVIOURS
	 */
    
    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 * 
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		createGETRequestDispatcher(request).forward(request, response);		// Forward the request to the view
	}

	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// not really used on home page
		doGet(request, response);
	}
	
	
	/****************************
	 * PAGE URLS
	 */

	@Override
	public String getGETPageURL() {
		return "views/default_login.jsp";
	}
	
	
	@Override
	public String getPOSTPageURL() {
		return null;
	}


	@Override
	public String getPUTPageURL() {
		return null;
	}


	@Override
	public String getDELETEPageURL() {
		return null;
	}
	
	
	/********************************************************************************************************************************************
	 * PAGE REQUESTS
	 */

	@Override
	public HttpServletRequest createPOSTServletRequest() {
		return null;
	}

	
	@Override
	public HttpServletRequest createGETServletRequest() {
		return null;
	}

	
	@Override
	public HttpServletRequest createPUTServletRequest() {
		return null;
	}

	
	@Override
	public HttpServletRequest createDELETEServletRequest() {
		return null;
	}

}
