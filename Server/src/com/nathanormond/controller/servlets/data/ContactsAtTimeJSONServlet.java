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
 * Servlet implementation class MapContactsDataAsJSONServlet
 */
@WebServlet("/this_time_contacts_data")
public class ContactsAtTimeJSONServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
       
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ContactsAtTimeJSONServlet() {
        super();
    }

    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {	
			response.setContentType("application/json;charset=UTF-8");	
			PrintWriter out = response.getWriter();
	        response.setContentType("application/json");
	        response.setCharacterEncoding("UTF-8");
	        String json = getContactJSON();
	        //System.out.println("RESPONDING: \n" + json);
	        out.print(json);
	}

	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	
	
	private String getContactJSON() { 
		return new GsonBuilder().setPrettyPrinting().create().toJson(DataModelSingleton.getInstance().getContactsAtTime(LocalDateTime.ofInstant(ServerStartedContextListener.spoofedTime.instant(), ZoneOffset.UTC)));
	}
		

}
