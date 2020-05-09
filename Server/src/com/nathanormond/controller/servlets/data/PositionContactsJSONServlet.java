package com.nathanormond.controller.servlets.data;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.GsonBuilder;
import com.nathanormond.controller.listeners.ServerStartedContextListener;
import com.nathanormondmodel.data.DataModelSingleton;

/**
 * Servlet implementation class PositionalContactsJSONServlet
 */
@WebServlet("/this_position_contacts_data")
public class PositionContactsJSONServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public PositionContactsJSONServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		if (request.getParameter("bounds") != null) {
			response.setContentType("application/json;charset=UTF-8");
			PrintWriter out = response.getWriter();
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
			String json = getContactJSON(request.getParameter("bounds"));
			out.print(json);
			out.flush();

		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	
	
	private String getContactJSON(String jsonString) { 
		return new GsonBuilder().setPrettyPrinting().create().toJson(DataModelSingleton.getInstance().getContactsAtTimeLatLng(LocalDateTime.ofInstant(ServerStartedContextListener.spoofedTime.instant(), ZoneOffset.UTC), jsonString));
	}

}
