package com.nathanormond.controller.listeners;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import com.nathanormond.meta.date_time.ClockTimeTravel;



/**
 * @author Nathan Ormond
 */
@WebListener
public class ServerStartedContextListener implements Runnable, ServletContextListener {
	
	private Thread thread;
	public static ClockTimeTravel spoofedTime;
	
	/***************************************
	 * RUN ON SERVLET INITIALISATION
	 */
	
	@Override
	public void contextInitialized(ServletContextEvent e) {
		System.out.println("SERVER CONTETXT LISTENER INIT");	
		startThread();
	}
	
	/**
	 * Starts new thread 
	 */
	private void startThread() { 
		System.out.println("ASYNC TASK: SPOOF CLOCK FOR DEVELOPMENT");
		thread = new Thread(this);
		thread.start();
	}
	
	/***************************************
	 * UPDATE DATA CACHE ON NEW THREAD
	 */
	
	/**
	 * Asynchronous task
	 */
	@Override
	public void run() {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
		LocalDateTime dateTime = LocalDateTime.parse("2019-06-10T00:00:00", formatter);
		ServerStartedContextListener.spoofedTime = new ClockTimeTravel(dateTime, ZoneOffset.of("-00:00"));
	    ClockTimeTravel.log(ServerStartedContextListener.spoofedTime.instant());
	}
	
	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		// not used
	}
	
}