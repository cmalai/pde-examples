package org.ancit.examples.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class AddressBook implements Serializable{
	
	List<Group> groups;
	
	public List<Group> getGroups() {
		if(groups == null) {
			groups = new ArrayList<>();
		}
		return groups;
	}

}
