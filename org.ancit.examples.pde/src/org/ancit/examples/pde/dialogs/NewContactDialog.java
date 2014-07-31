package org.ancit.examples.pde.dialogs;

import org.ancit.examples.model.Contact;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.TitleAreaDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

public class NewContactDialog extends TitleAreaDialog {
	private Text txtName;
	private Text txtAddress;
	private Text txtMobileno;
	private Text txtEmailid;
	private Contact contact;

	/**
	 * Create the dialog.
	 * @param parentShell
	 */
	public NewContactDialog(Shell parentShell) {
		super(parentShell);
	}

	/**
	 * Create contents of the dialog.
	 * @param parent
	 */
	@Override
	protected Control createDialogArea(Composite parent) {
		
		
		setMessage("Fill the dialog to create a new Contact");
		setTitle("New Contact");
		Composite area = (Composite) super.createDialogArea(parent);
		Composite container = new Composite(area, SWT.NONE);
		container.setLayout(new GridLayout(2, false));
		container.setLayoutData(new GridData(GridData.FILL_BOTH));
		
		Label lblName = new Label(container, SWT.NONE);
		lblName.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblName.setText("Name");
		
		txtName = new Text(container, SWT.BORDER);
		txtName.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		txtName.addModifyListener(new ModifyListener() {
			
			@Override
			public void modifyText(ModifyEvent e) {
				getButton(OK).setEnabled(!txtName.getText().isEmpty());				
			}
		});
		
		Label lblAddress = new Label(container, SWT.NONE);
		lblAddress.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblAddress.setText("Address");
		
		txtAddress = new Text(container, SWT.BORDER);
		txtAddress.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Label lblMobileNo = new Label(container, SWT.NONE);
		lblMobileNo.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblMobileNo.setText("Mobile No");
		
		txtMobileno = new Text(container, SWT.BORDER);
		txtMobileno.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Label lblEmailId = new Label(container, SWT.NONE);
		lblEmailId.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblEmailId.setText("Email ID");
		
		txtEmailid = new Text(container, SWT.BORDER);
		txtEmailid.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));

		return area;
	}

	/**
	 * Create contents of the button bar.
	 * @param parent
	 */
	@Override
	protected void createButtonsForButtonBar(Composite parent) {
		createButton(parent, IDialogConstants.OK_ID, IDialogConstants.OK_LABEL,
				true);
		createButton(parent, IDialogConstants.CANCEL_ID,
				IDialogConstants.CANCEL_LABEL, false);
		
		getButton(OK).setEnabled(false);
	}

	/**
	 * Return the initial size of the dialog.
	 */
	@Override
	protected Point getInitialSize() {
		return new Point(450, 300);
	}

	public Contact getContact() {
		return contact;
	}
	
	@Override
	protected void okPressed() {		
		contact = new Contact();
		contact.setName(txtName.getText());
		contact.setAddress(txtAddress.getText());
		contact.setMobileno(txtMobileno.getText());
		contact.setEmailid(txtEmailid.getText());		
		super.okPressed();
	}

}
