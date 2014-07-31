package org.ancit.examples.extn;

import org.ancit.examples.pde.extensionpt.IActionHandler;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.widgets.Display;

public class OpenFacebookAction implements IActionHandler {

	public OpenFacebookAction() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void run() {
		MessageDialog
				.openInformation(Display.getDefault().getActiveShell(),
						"Open Facebook...",
						"Thanks for trying the Feature. This is yet to be imlplemented");
	}

}
