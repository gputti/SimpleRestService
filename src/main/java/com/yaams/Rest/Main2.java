/**
 * @author Gopikrishna Putti - Apr 15, 2017
 * Developed by Gopi for personal use.
 *
 */
package com.yaams.Rest;

import org.apache.log4j.Logger;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

public class Main2 {
	
	public static final Logger logger = Logger.getLogger(Main2.class);
    
    /**
     * below one works too. 
     * @param args
     * @throws Exception
     */
    public static void main( String[] args ) throws Exception    {
    	logger.info( "Hello World!" );
    	
    	// create jersey servelet context handler 
    	ServletContextHandler servletContextHandler = new ServletContextHandler(ServletContextHandler.SESSIONS);
    	servletContextHandler.setContextPath("/rest");

        // register jersey servlet context  
        ServletHolder jerseyServlet = servletContextHandler.addServlet(org.glassfish.jersey.servlet.ServletContainer.class, "/*");
        jerseyServlet.setInitOrder(0);        
        // Tells the Jersey Servlet which REST service/class to load.
        jerseyServlet.setInitParameter("jersey.config.server.provider.classnames", ContactService.class.getCanonicalName());
        
        Server jettyServer = new Server(8080);
        jettyServer.setHandler(servletContextHandler);        
                
        try {
            jettyServer.start();
            jettyServer.join();
        } finally {
            jettyServer.destroy();
        }
    }    
    
    
}
