package org.ancit.examples.pde.preferences;

import org.ancit.examples.pde.Activator;
import org.eclipse.jface.preference.PreferencePage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Button;

public class AddressBookPreferencePage extends PreferencePage implements
		IWorkbenchPreferencePage {

	private Button btnGoogle;
	private Button btnYahoo;

	/**
	 * Create the preference page.
	 */
	public AddressBookPreferencePage() {
	}

	/**
	 * Create contents of the preference page.
	 * @param parent
	 */
	@Override
	public Control createContents(Composite parent) {
		Composite container = new Composite(parent, SWT.NULL);
		container.setLayout(new GridLayout(1, false));
		
		Group grpLocationMapSite = new Group(container, SWT.NONE);
		grpLocationMapSite.setLayout(new GridLayout(2, false));
		grpLocationMapSite.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		grpLocationMapSite.setText("Location Map Site");
		
		btnGoogle = new Button(grpLocationMapSite, SWT.RADIO);
		btnGoogle.setText("Google");
		
		btnYahoo = new Button(grpLocationMapSite, SWT.RADIO);
		btnYahoo.setText("Yahoo");
		
		String mapSite = Activator.getDefault().getPreferenceStore().getString("MAP_SITE");
		if(mapSite.equals("Google")) {
			btnGoogle.setSelection(true);
//			btnYahoo.setSelection(false);
		} else {
			btnYahoo.setSelection(true);
//			btnGoogle.setSelection(false);
		}

		return container;
	}

	/**
	 * Initialize the preference page.
	 */
	public void init(IWorkbench workbench) {
		// Initialize the preference page
	}
	
	@Override
	protected void performApply() {
		if(btnGoogle.getSelection()) {
			Activator.getDefault().getPreferenceStore().setValue("MAP_SITE", "Google");
		} else {
			Activator.getDefault().getPreferenceStore().setValue("MAP_SITE", "Yahoo");
		}
	}
	
	@Override
	protected void performDefaults() {
		String defaultString = Activator.getDefault().getPreferenceStore().getDefaultString("MAP_SITE");
		if(defaultString.equals("Google")) {
		btnGoogle.setSelection(true);
		}
	}

}
