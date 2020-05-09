package com.nathanormond.controller.servlets.pages.logincontroller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.nathanormond.controller.servlets.AbstractHttpServlet;
import com.nathanormond.model.data.reference_types.interfaces.IUser;
import com.nathanormondmodel.data.DataModelSingleton;
import com.nathanormond.model.data.reference_types.comparisons.GuaranteedUser;

/**
 * Servlet implementation class LoginController
 */
@WebServlet("/login")
public class LoginController extends AbstractHttpServlet {

	private static final long serialVersionUID = 1L;
	
	
	/**
     * @see HttpServlet#HttpServlet()
     */
    public LoginController() {
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
		createGETRequestDispatcher(request).forward(request, response);	// Forward the request to the view
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		IUser user = new GuaranteedUser().compareUserToUsers(DataModelSingleton.getInstance().getUsers(),request.getParameter("username"));
		
		if(user.isNull()){ 
			//TODO add flag to JavaScript notifying of default value
		}
		
		response.sendRedirect(user.getUsername());
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
