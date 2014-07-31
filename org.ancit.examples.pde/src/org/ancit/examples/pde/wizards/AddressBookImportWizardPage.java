package org.ancit.examples.pde.wizards;

import java.io.File;

import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.ModifyEvent;

public class AddressBookImportWizardPage extends WizardPage {
	private Text txtFilename;

	/**
	 * Create the wizard.
	 */
	public AddressBookImportWizardPage() {
		super("wizardPage");
		setPageComplete(false);
		setTitle("Import AddressBook");
		setDescription("Provide the path of the AddressBook to import");
	}

	/**
	 * Create contents of the wizard.
	 * 
	 * @param parent
	 */
	public void createControl(Composite parent) {
		Composite container = new Composite(parent, SWT.NULL);

		setControl(container);
		container.setLayout(new GridLayout(3, false));

		Label lblFileName = new Label(container, SWT.NONE);
		lblFileName.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false,
				false, 1, 1));
		lblFileName.setText("File Name");

		txtFilename = new Text(container, SWT.BORDER);
		txtFilename.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent e) {

				if (txtFilename.getText().isEmpty()) {
					setPageComplete(false);
					setErrorMessage("File Path cannot be Empty. Enter valid File Path.");
				} else {
					File file = new File(txtFilename.getText());
					if (file.exists() && file.isFile()) {
						setPageComplete(true);
						setErrorMessage(null);
					} else {
						setPageComplete(false);
						setErrorMessage("Entered Path is not a Valid Path.");
					}
				}
			}
		});
		txtFilename.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true,
				false, 1, 1));

		Button btnBrowse = new Button(container, SWT.NONE);
		btnBrowse.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				FileDialog fDialog = new FileDialog(Display.getDefault()
						.getActiveShell());
				String filePath = fDialog.open();
				if (filePath != null && !filePath.isEmpty()) {
					txtFilename.setText(filePath);
				}
			}
		});
		btnBrowse.setText("browse");
	}

	public String getFileLocation() {
		// TODO Auto-generated method stub
		return txtFilename.getText();
	}

}
