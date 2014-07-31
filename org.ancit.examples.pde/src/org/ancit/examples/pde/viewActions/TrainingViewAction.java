package org.ancit.examples.pde.viewActions;

import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.ui.IViewActionDelegate;
import org.eclipse.ui.IViewPart;

public class TrainingViewAction implements IViewActionDelegate {

	@Override
	public void run(IAction action) {
		System.out.println("This is a ViewAction thru plugin.xml");

	}

	@Override
	public void selectionChanged(IAction action, ISelection selection) {
		// TODO Auto-generated method stub

	}

	@Override
	public void init(IViewPart view) {
		// TODO Auto-generated method stub

	}

}
