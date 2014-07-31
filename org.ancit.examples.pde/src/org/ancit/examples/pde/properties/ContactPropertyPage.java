package org.ancit.examples.pde.properties;

import org.ancit.examples.model.Contact;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.ui.IWorkbenchPropertyPage;
import org.eclipse.ui.dialogs.PropertyPage;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.layout.GridData;

public class ContactPropertyPage extends PropertyPage implements IWorkbenchPropertyPage{
	private Text txtName;

	/**
	 * Create the property page.
	 */
	public ContactPropertyPage() {
	}

	/**
	 * Create contents of the property page.
	 * @param parent
	 */
	@Override
	public Control createContents(Composite parent) {
		Composite container = new Composite(parent, SWT.NULL);
		container.setLayout(new GridLayout(2, false));
		
		//Retrieve the Object for which Properties are to be shown.
		Contact contact = (Contact)getElement().getAdapter(Contact.class);
		
		Label lblName = new Label(container, SWT.NONE);
		lblName.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblName.setText("Name");
		
		txtName = new Text(container, SWT.BORDER);
		txtName.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		txtName.setText(contact.getName());

		return container;
	}

}
