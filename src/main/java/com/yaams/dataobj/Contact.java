/**
 * @author Gopikrishna Putti - Apr 14, 2017
 * Developed by Gopi for personal use.
 * 
 * This is the most fundamental object contains 
 * all contact info.
 *
 */
package com.yaams.dataobj;

public class Contact {
	
	public enum Type {
		EMAIL,
		MOBILEPHONE,
		LANDPHONE,
		WHATSAPP,
		SKYPE,
		ADDRESS		
	}
	
	public String value = null;
	public Type type = Type.EMAIL;
	
	public Contact(String value ) {
		this(value, Type.EMAIL);
	}
	
	public Contact(String value, Type type ) {
		this.value = value;
		this.type = type;
	}
	
	public static Contact create(String val) {
		Contact c = null;
		if( val.contains("@")  || val.contains("\\.") ) {
			c = new Contact(val, Type.EMAIL);
		} else {
			c = new Contact(val, Type.MOBILEPHONE);
		}
		return c;
	}
	
	@Override
	public String toString( ) {
		return type.name() + " : " + value;
	}

}
