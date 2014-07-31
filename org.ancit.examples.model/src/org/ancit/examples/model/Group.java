package org.ancit.examples.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Group implements Serializable{
	
	List<Contact> contacts;
	private String name;
	
	public List<Contact> getContacts() {
		if(contacts == null) {
			contacts = new ArrayList<>();
		}
		return contacts;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
