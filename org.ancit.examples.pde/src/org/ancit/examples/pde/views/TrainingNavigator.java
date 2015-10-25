package org.ancit.examples.pde.views;

import org.ancit.examples.model.AddressBook;
import org.ancit.examples.model.Contact;
import org.ancit.examples.model.Group;
import org.ancit.examples.model.ModelManager;
import org.ancit.examples.pde.Activator;
import org.ancit.examples.pde.dialogs.NewContactDialog;
import org.ancit.examples.pde.views.viewers.AddressBookContentProvider;
import org.ancit.examples.pde.views.viewers.AddressBookLabelProvider;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IMenuListener;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.InputDialog;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.DecoratingLabelProvider;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.ui.IDecoratorManager;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.part.ViewPart;

public class TrainingNavigator extends ViewPart {

	private Action addGroup;
	private Action saveAction;
	private Action addContact;
	private TreeViewer tViewer;
	private Action loadAction;

	public TrainingNavigator() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void createPartControl(Composite parent) {
		
		tViewer = new TreeViewer(parent);
		//Step 2. Set Content Provider
		tViewer.setContentProvider(new AddressBookContentProvider());
		//Step 3. Set Label Provider
		IDecoratorManager decoratorManager = PlatformUI.getWorkbench().getDecoratorManager();
		DecoratingLabelProvider dlp = new DecoratingLabelProvider(new AddressBookLabelProvider(), decoratorManager);
		tViewer.setLabelProvider(dlp);
		//Step 4. Set Input
		AddressBook addressBook = ModelManager.getInstance().getAddressBook();
		tViewer.setInput(addressBook);
		
		tViewer.addSelectionChangedListener(new ISelectionChangedListener() {
			
			@Override
			public void selectionChanged(SelectionChangedEvent event) {
				IStructuredSelection selection = (IStructuredSelection) event.getSelection();
				Object selectedObject = selection.getFirstElement();
				if(selectedObject instanceof Contact){
					addContact.setEnabled(false);
				} else {
					addContact.setEnabled(true);
				}
				
				
			}
		});
		
		//Selection Provider
		getSite().setSelectionProvider(tViewer);
		
		
		makeActions();
		fillMenuBar();
		fillToolBar();
		hookContextMenu();

	}

	private void hookContextMenu() {

		MenuManager mgr = new MenuManager();
		mgr.setRemoveAllWhenShown(true);
		mgr.addMenuListener(new IMenuListener() {
			
			@Override
			public void menuAboutToShow(IMenuManager manager) {
				manager.add(addGroup);
				manager.add(addContact);
			}
		});
		
		Menu menu = mgr.createContextMenu(tViewer.getTree());
		tViewer.getTree().setMenu(menu);
		
	}

	private void fillToolBar() {
		getViewSite().getActionBars().getToolBarManager().add(addGroup);
		getViewSite().getActionBars().getToolBarManager().add(addContact);
		getViewSite().getActionBars().getToolBarManager().add(saveAction);
		getViewSite().getActionBars().getToolBarManager().add(loadAction);
	}

	private void fillMenuBar() {
		
		MenuManager mgr = new MenuManager("New");
		getViewSite().getActionBars().getMenuManager().add(mgr);
		mgr.add(addGroup);
		mgr.add(addContact);
		
		getViewSite().getActionBars().getMenuManager().add(saveAction);
		getViewSite().getActionBars().getMenuManager().add(loadAction);
		
	}

	private void makeActions() {
		addGroup = new Action() {
			
			@Override
			public void run() {					
				boolean result = MessageDialog.openConfirm(
						Display.getDefault().getActiveShell(),
						"Confirmation - Add Group",
						"Are you sure you want to add a Group ?");
				
				if (result) {

					InputDialog iDialog = new InputDialog(Display.getDefault()
							.getActiveShell(), "New Group Dialog", "Enter name of the New Group you want to Add",
							"", null);
					
					int open = iDialog.open();

					if (IDialogConstants.OK_ID == open) {
						Group group = new Group();
						group.setName(iDialog.getValue());
						ModelManager.getInstance().getAddressBook().getGroups()
								.add(group);
						tViewer.refresh();
					}
				}
			}
			
		};
		addGroup.setText("Add Group");
		addGroup.setToolTipText("Create a new Group in your AddressBook");	
		addGroup.setImageDescriptor(Activator.imageDescriptorFromPlugin(Activator.PLUGIN_ID, "icons/tag-macro.gif"));
		
		addContact = new Action() {
			
			@Override
			public void run() {
				ISelection selection = tViewer.getSelection();
				if (selection instanceof IStructuredSelection) {
					IStructuredSelection sSelection = (IStructuredSelection) selection;
					Object object = sSelection.getFirstElement();
					if(object instanceof Group) {
						Group group = (Group)object;

						NewContactDialog dialog = new NewContactDialog(Display.getDefault().getActiveShell());
						int open = dialog.open();
						if (IDialogConstants.OK_ID == open) {
							Contact contact = dialog.getContact();
							group.getContacts().add(contact);
							tViewer.refresh();
						}
					}
				}
			}
			
		};
		addContact.setText("Add Contact");
		addContact.setToolTipText("Create a new Contact in your AddressBook");
		addContact.setImageDescriptor(Activator.imageDescriptorFromPlugin(Activator.PLUGIN_ID, "icons/attribute_obj.gif"));
		
		saveAction = new Action("Save") {
			@Override
			public void run() {
				FileDialog fDialog = new FileDialog(Display.getDefault().getActiveShell(), SWT.SAVE);
				String filePath = fDialog.open();
				if(filePath !=null && !filePath.isEmpty()) {
					ModelManager.getInstance().save(filePath);
				} else {
					MessageDialog.openError(Display.getDefault().getActiveShell(), "Error Dialog", "Empty File Name or File Path.");
				}
			}
		};
		saveAction.setImageDescriptor(PlatformUI.getWorkbench()
				.getSharedImages()
				.getImageDescriptor(ISharedImages.IMG_ETOOL_SAVE_EDIT));
		
		loadAction = new Action("Load") {
			@Override
			public void run() {
				FileDialog fDialog = new FileDialog(Display.getDefault().getActiveShell(), SWT.OPEN);
				String filePath = fDialog.open();
				if(filePath !=null && !filePath.isEmpty()) {
					ModelManager.getInstance().load(filePath);
					tViewer.setInput(ModelManager.getInstance().getAddressBook());
				} else {
					MessageDialog.openError(Display.getDefault().getActiveShell(), "Error Dialog", "Empty File Name or File Path.");
				}
			}
		};
		loadAction.setImageDescriptor(PlatformUI.getWorkbench()
				.getSharedImages()
				.getImageDescriptor(ISharedImages.IMG_TOOL_UP));
	}
	
	

	@Override
	public void setFocus() {
		// TODO Auto-generated method stub

	}

	public void refresh() {
		tViewer.setInput(ModelManager.getInstance().getAddressBook());		
	}

}
