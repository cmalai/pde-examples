package org.ancit.examples.pde.views.viewers;

import org.ancit.examples.model.AddressBook;
import org.ancit.examples.model.Group;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.Viewer;

public class AddressBookContentProvider implements ITreeContentProvider {

	@Override
	public void dispose() {
		// TODO Auto-generated method stub

	}

	@Override
	public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
		// TODO Auto-generated method stub

	}

	@Override
	public Object[] getElements(Object inputElement) {

		if (inputElement instanceof AddressBook) {
			AddressBook aBook = (AddressBook) inputElement;
			return aBook.getGroups().toArray();
		}

		return null;
	}

	@Override
	public boolean hasChildren(Object element) {
		if (element instanceof Group) {
			Group group = (Group) element;
			if (group.getContacts().size() > 0) {
				return true;
			} else {
				return false;
			}
		}
		return false;
	}

	@Override
	public Object[] getChildren(Object parentElement) {
		if (parentElement instanceof Group) {
			Group group = (Group) parentElement;
			return group.getContacts().toArray();
		}
		return null;
	}

	@Override
	public Object getParent(Object element) {
		// TODO Auto-generated method stub
		return null;
	}

}
