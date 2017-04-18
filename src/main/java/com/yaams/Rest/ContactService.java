/**
 * @author Gopikrishna Putti - Apr 14, 2017
 * Developed by Gopi for personal use.
 *
 */
package com.yaams.Rest;


import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.*;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.log4j.Logger;

import com.yaams.dataobj.Contact;

@Path("/contacts")
public class ContactService {

	private static Logger logger = Logger.getLogger(ContactService.class);

	public static Set<Contact> set = new HashSet<Contact>();
	
	@GET
    @Path("test")
    @Produces(MediaType.TEXT_PLAIN)
    public String test() {
        return "Test service - powered by Jetty web server & jersey webservices";
    }
	
	@GET
    @Path("list")
    @Produces(MediaType.TEXT_PLAIN)
    public String listContacts() {
		logger.info("list service called. ");
		StringBuffer sb = new StringBuffer();
		for(Contact con : set) {
			sb.append(con.toString()).append("\n");
		}
		return sb.toString();
    }

	
	
	@POST
	@Path("/add")
	@Produces(MediaType.TEXT_HTML)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public Response add(@FormParam("email") String val) {
		logger.info("received email: " + val);
		if (val == null) return Response.status(200).entity("got null\n").build();
		Contact newcontact = Contact.create(val);
		set.add(newcontact);
		return Response.status(200).entity("added: " + val).build();
	}

	
}
