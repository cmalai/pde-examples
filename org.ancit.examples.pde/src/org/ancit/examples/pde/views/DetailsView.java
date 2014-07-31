package org.ancit.examples.pde.views;

import org.ancit.examples.model.Contact;
import org.ancit.examples.pde.extensionpt.IActionHandler;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtensionRegistry;
import org.eclipse.core.runtime.Platform;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.fieldassist.ControlDecoration;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.ISelectionListener;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.part.ViewPart;

public class DetailsView extends ViewPart implements ISelectionListener {

	public static final String ID = "org.ancit.examples.pde.views.DetailsView"; //$NON-NLS-1$
	private Text txtName;
	private Text txtAddress;
	private Text txtMobileno;
	private Text txtEmailid;
	private Contact contact;

	public DetailsView() {
	}

	/**
	 * Create contents of the view part.
	 * @param parent
	 */
	@Override
	public void createPartControl(Composite parent) {
		
		
		
		Composite container = new Composite(parent, SWT.NONE);
		container.setLayout(new GridLayout(2, false));
		{
			Label lblName = new Label(container, SWT.NONE);
			lblName.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
			lblName.setText("Name");
		}
		{
			txtName = new Text(container, SWT.BORDER);
			txtName.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
			
			final ControlDecoration controlDecoration = new ControlDecoration(txtName, SWT.LEFT | SWT.TOP);
			controlDecoration.setImage(PlatformUI.getWorkbench().getSharedImages().getImage(ISharedImages.IMG_DEC_FIELD_ERROR));
			controlDecoration.setDescriptionText("Please Enter some valid name for the Contact");
			controlDecoration.hide();

			txtName.addModifyListener(new ModifyListener() {
				public void modifyText(ModifyEvent e) {
					if(txtName.getText().isEmpty()) {
						controlDecoration.show();
					} else {
						controlDecoration.hide();
						if (contact != null) {
							contact.setName(txtName.getText());
						}
					}
				}
			});
		}
			
		{
			Label lblAddress = new Label(container, SWT.NONE);
			lblAddress.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
			lblAddress.setText("Address");
		}
		{
			txtAddress = new Text(container, SWT.BORDER);
			txtAddress.addModifyListener(new ModifyListener() {
				public void modifyText(ModifyEvent e) {
					contact.setAddress(txtAddress.getText());
				}
			});
			
			
			txtAddress.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		}
		{
			Label lblMobileNo = new Label(container, SWT.NONE);
			lblMobileNo.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
			lblMobileNo.setText("Mobile No");
		}
		{
			txtMobileno = new Text(container, SWT.BORDER);
			txtMobileno.addModifyListener(new ModifyListener() {
				public void modifyText(ModifyEvent e) {
					contact.setMobileno(txtMobileno.getText());
				}
			});
			txtMobileno.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		}
		{
			Label lblEmailId = new Label(container, SWT.NONE);
			lblEmailId.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
			lblEmailId.setText("Email ID");
		}
		{
			txtEmailid = new Text(container, SWT.BORDER);
			txtEmailid.addModifyListener(new ModifyListener() {
				public void modifyText(ModifyEvent e) {
					contact.setEmailid(txtEmailid.getText());
				}
			});
			txtEmailid.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		}

		createActions();
		initializeToolBar();
		initializeMenu();
		
		getSite().getPage().addSelectionListener(this);
	}

	/**
	 * Create the actions.
	 */
	private void createActions() {
		
		IExtensionRegistry extensionRegistry = Platform.getExtensionRegistry();
		IConfigurationElement[] configurationElements = extensionRegistry
				.getConfigurationElementsFor("org.ancit.examples.pde.detailsViewAction");
		
		for (IConfigurationElement iConfigurationElement : configurationElements) {
			String attribute = iConfigurationElement.getAttribute("label");
			try {
				final IActionHandler handler = (IActionHandler)iConfigurationElement.createExecutableExtension("class");
				Action action = new Action(attribute) {

					@Override
					public void run() {
						handler.run();
					}
								
				};

				getViewSite().getActionBars().getToolBarManager().add(action);
			} catch (CoreException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
	}

	/**
	 * Initialize the toolbar.
	 */
	private void initializeToolBar() {
		IToolBarManager toolbarManager = getViewSite().getActionBars()
				.getToolBarManager();
	}

	/**
	 * Initialize the menu.
	 */
	private void initializeMenu() {
		IMenuManager menuManager = getViewSite().getActionBars()
				.getMenuManager();
	}

	@Override
	public void setFocus() {
		// Set the focus
	}

	@Override
	public void selectionChanged(IWorkbenchPart part, ISelection selection) {
		if (selection instanceof IStructuredSelection) {
			IStructuredSelection sSelection = (IStructuredSelection) selection;
			Object firstElement = sSelection.getFirstElement();
			if (firstElement instanceof Contact) {
				contact = (Contact) firstElement;
				txtName.setText(contact.getName() != null ? contact.getName() : "");
				txtAddress.setText(contact.getAddress() != null ? contact.getAddress(): "");
				txtMobileno.setText(contact.getMobileno() != null ? contact.getMobileno() : "");
				txtEmailid.setText(contact.getEmailid() != null ? contact.getEmailid() : "");				
			}			
		}
		
	}

}
