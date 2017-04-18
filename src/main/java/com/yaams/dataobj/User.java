/**
 * @author Gopikrishna Putti - Apr 14, 2017
 * Developed by Gopi for personal use.
 *
 */
package com.yaams.dataobj;

import static com.yaams.dataobj.CONSTANTS.*;
import java.util.ArrayList;
import java.util.List;

public class User {
	
	String fname = null;
	String mname = null;
	String lname = null; 
	List<Contact> contacts = new ArrayList<Contact>();
	
	public User(String fname, Contact contact) {
		this.fname = fname; 
		contacts.add(contact);
	}
	
	@Override
	public String toString( ) {
		return fname + SINGLE_SPACE + lname + contacts.get(0).toString();
	}
	
	

}
