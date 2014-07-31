package org.ancit.examples.pde.handlers;

import org.ancit.examples.pde.wizards.AddressBookImportWizard;
import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.WorkbenchException;

public class TrainingHandler extends AbstractHandler {

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		System.out.println("Command Handler");
		
		WizardDialog wDialog = new WizardDialog(Display.getDefault().getActiveShell(), new AddressBookImportWizard());
		wDialog.open();
		
		return null;
	}

}
