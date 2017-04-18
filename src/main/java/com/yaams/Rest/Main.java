/**
 * @author Gopikrishna Putti - Apr 15, 2017
 * Developed by Gopi for personal use.
 *
 */
package com.yaams.Rest;

import org.apache.log4j.Logger;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.*;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

public class Main  { 

	private static Logger logger = Logger.getLogger(Main.class);
	ContextHandlerCollection contextsCollection = new ContextHandlerCollection();

	public Main() {
		
	} 
	
	/**
	 * adds web folders 
	 */
	public void addWebFiles() {

		// adding "web" folder
		ResourceHandler handler = new ResourceHandler();
		handler.setDirectoriesListed(true);
		handler.setWelcomeFiles(new String[] { "index.html" });
		handler.setResourceBase("./web");        
		ContextHandler context = new ContextHandler();
		context.setContextPath("/web");
		context.setHandler(handler);    
		contextsCollection.addHandler(context);
		logger.info("added context for web folder.");
		
		// adding "ui" folder
		ResourceHandler handler2 = new ResourceHandler();
		handler2.setDirectoriesListed(true);
		handler2.setWelcomeFiles(new String[] { "home.html", "index.html" });
		handler2.setResourceBase("./web2");        
		ContextHandler context2 = new ContextHandler();
		context2.setContextPath("/ui");
		context2.setHandler(handler2);         
		contextsCollection.addHandler(context2);
		logger.info("added context for web2 folder.");
	}

	/**
	 * adds servlets context 
	 */
	public void addServletHandler() {

		// first add jersey servlet handler
		ServletContextHandler servletContextHandler = new ServletContextHandler(ServletContextHandler.SESSIONS);
		servletContextHandler.setContextPath("/rest");
		ServletHolder jerseyServlet = servletContextHandler.addServlet(org.glassfish.jersey.servlet.ServletContainer.class, "/*");
		jerseyServlet.setInitOrder(0);        
		jerseyServlet.setInitParameter("jersey.config.server.provider.classnames", ContactService.class.getCanonicalName());
		contextsCollection.addHandler(servletContextHandler);
		logger.info("added context jersey servelt (org.glassfish.jersey.servlet.ServletContainer)");

		// now add any of your own servlets  below 
		// don't forget to add to contextCollection list.


	}


	public static void main( String[] args ) throws Exception    {    	

		Main mainProgram = new Main( );
		mainProgram.addWebFiles();
		mainProgram.addServletHandler();
		Server jettyEmbeddedServer = new Server(8080);
		jettyEmbeddedServer.setHandler(mainProgram.contextsCollection);

		try {
			jettyEmbeddedServer.start();
			jettyEmbeddedServer.join();
		} finally {
			jettyEmbeddedServer.destroy();
		}

	}



}
