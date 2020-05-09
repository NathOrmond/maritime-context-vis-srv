package com.nathanormond.controller.servlets.pages.mapcontroller;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.GsonBuilder;
import com.nathanormond.controller.listeners.ServerStartedContextListener;
import com.nathanormond.controller.servlets.AbstractHttpServlet;
import com.nathanormondmodel.data.DataModelSingleton;

/**
 * Servlet implementation class MapController
 */
@WebServlet("/map")
public class MapController extends AbstractHttpServlet {
	
	private static final long serialVersionUID = 1L;
      
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MapController() {
        super();
    }

    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		addContactsAtTime(request);
		createGETRequestDispatcher(request).forward(request, response);	// Forward the request to the view
	}

	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//TODO
	}
	
	/****************************************
	 * 
	 */
	
	private void addContactsAtTime(HttpServletRequest request) { 
		String json = new GsonBuilder().setPrettyPrinting().create().toJson(DataModelSingleton.getInstance().getContactsAtTime(LocalDateTime.ofInstant(ServerStartedContextListener.spoofedTime.instant(), ZoneOffset.UTC)));
		request.setAttribute("contacts", json);
	}
	
	
	/****************************************
	 * 
	 */

	@Override
	public String getPOSTPageURL() {
		return null;
	}

	
	@Override
	public String getGETPageURL() {
		return "views/map_view.jsp";
	}

	
	@Override
	public String getPUTPageURL() {
		return null;
	}

	@Override
	public String getDELETEPageURL() {
		return null;
	}

	
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
