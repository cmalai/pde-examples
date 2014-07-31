package org.ancit.examples.pde.wizards;

import org.ancit.examples.model.ModelManager;
import org.ancit.examples.pde.views.TrainingNavigator;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.ui.IImportWizard;
import org.eclipse.ui.IViewPart;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;

public class AddressBookImportWizard extends Wizard implements IImportWizard {

	public AddressBookImportWizard() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void init(IWorkbench workbench, IStructuredSelection selection) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean performFinish() {
		
		AddressBookImportWizardPage page = (AddressBookImportWizardPage) getPage("wizardPage");
		String filePath = page.getFileLocation();
		
		ModelManager.getInstance().load(filePath);
		
		try {
			TrainingNavigator view = (TrainingNavigator) PlatformUI
					.getWorkbench().getActiveWorkbenchWindow().getActivePage()
					.showView("org.ancit.examples.pde.views.TrainingNavigator");
			view.refresh();
		} catch (PartInitException e) {
			e.printStackTrace();
		}
		
		return true;
	}
	
	@Override
	public void addPages() {
		addPage(new AddressBookImportWizardPage());
	}

}
