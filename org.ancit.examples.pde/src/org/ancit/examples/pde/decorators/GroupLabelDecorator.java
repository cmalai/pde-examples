package org.ancit.examples.pde.decorators;

import org.ancit.examples.model.Group;
import org.ancit.examples.pde.Activator;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.IDecoration;
import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.jface.viewers.ILightweightLabelDecorator;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.PlatformUI;

public class GroupLabelDecorator implements ILightweightLabelDecorator {

	@Override
	public void addListener(ILabelProviderListener listener) {
		// TODO Auto-generated method stub

	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean isLabelProperty(Object element, String property) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void removeListener(ILabelProviderListener listener) {
		// TODO Auto-generated method stub

	}

	@Override
	public void decorate(Object element, IDecoration decoration) {
		if (element instanceof Group) {
			Group group = (Group) element;
			if (group.getContacts().size() == 0) {
				ImageDescriptor overlay = PlatformUI.getWorkbench()
						.getSharedImages()
						.getImageDescriptor(ISharedImages.IMG_DEC_FIELD_ERROR);
				decoration.addOverlay(overlay, IDecoration.TOP_LEFT);
			}
			decoration.addSuffix(" {"+group.getContacts().size()+"}");
		}

	}

}
