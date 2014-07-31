package org.ancit.examples.pde.views;

import org.ancit.examples.model.Contact;
import org.ancit.examples.pde.Activator;
import org.eclipse.jface.util.IPropertyChangeListener;
import org.eclipse.jface.util.PropertyChangeEvent;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.browser.Browser;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.ISelectionListener;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.part.ViewPart;

public class LocationView extends ViewPart implements ISelectionListener, IPropertyChangeListener {

	private Browser browser;
	private Contact contact;

	public LocationView() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void createPartControl(Composite parent) {
		
		browser = new Browser(parent, SWT.NONE);
		browser.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
		browser.setUrl("http://maps.google.com");
		
		//Registering as SelectionListener
		getSite().getPage().addSelectionListener(this);
		
		//Register as a Listener to Preferences
		Activator.getDefault().getPreferenceStore().addPropertyChangeListener(this);

	}

	@Override
	public void setFocus() {
		// TODO Auto-generated method stub

	}

	@Override
	public void selectionChanged(IWorkbenchPart part, ISelection selection) {
		
		if (selection instanceof IStructuredSelection) {
			IStructuredSelection sSelection = (IStructuredSelection) selection;
			if(!sSelection.isEmpty()) {
				Object object = sSelection.getFirstElement();
				if (object instanceof Contact) {
					contact = (Contact) object;
					System.out.println(contact.getName() + ":" + contact.getAddress());
					
					String mapSite = Activator.getDefault().getPreferenceStore().getString("MAP_SITE");
					if (mapSite.equals("Google")) {
						browser.setUrl("http://maps.google.com/maps/place/"
								+ contact.getAddress());
					} else {
						browser.setUrl("http://maps.yahoo.com/place/"+contact.getAddress());
					}
				}
			}		
			
		}		
	}

	@Override
	public void propertyChange(PropertyChangeEvent event) {
		String mapSite = Activator.getDefault().getPreferenceStore().getString("MAP_SITE");
		if (mapSite.equals("Google")) {
			browser.setUrl("http://maps.google.com/maps/place/"
					+ contact.getAddress());
		} else {
			browser.setUrl("http://maps.yahoo.com/place/"+contact.getAddress());
		}
		
	}

}
