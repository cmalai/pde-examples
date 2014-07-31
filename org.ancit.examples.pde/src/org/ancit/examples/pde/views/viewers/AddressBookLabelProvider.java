package org.ancit.examples.pde.views.viewers;

import org.ancit.examples.model.Contact;
import org.ancit.examples.model.Group;
import org.ancit.examples.pde.Activator;
import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.jface.viewers.IColorProvider;
import org.eclipse.jface.viewers.IFontProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.PlatformUI;

public class AddressBookLabelProvider extends LabelProvider implements IColorProvider, IFontProvider {
	
	@Override
	public String getText(Object element) {
		if (element instanceof Group) {
			Group group = (Group) element;
			return group.getName();
		} else if (element instanceof Contact) {
			Contact contact = (Contact) element;
			return contact.getName();
		}
		return super.getText(element);
	}
	
	@Override
	public Image getImage(Object element) {

		if (element instanceof Group) {
			return Activator.imageDescriptorFromPlugin(Activator.PLUGIN_ID, "icons/tag-macro.gif").createImage();
		} else if (element instanceof Contact) {
			
//			return Activator.imageDescriptorFromPlugin(Activator.PLUGIN_ID, "icons/attribute_obj.gif").createImage();
//			return PlatformUI.getWorkbench().getSharedImages().getImage(ISharedImages.IMG_OBJ_ADD);
			Image image = JFaceResources.getImageRegistry().get("CONTACT");
			if(image == null) {
				image = Activator.imageDescriptorFromPlugin(Activator.PLUGIN_ID, "icons/attribute_obj.gif").createImage();
				JFaceResources.getImageRegistry().put("CONTACT", image);
			}
			return image;
		}
		
		return super.getImage(element);
	}

	@Override
	public Color getForeground(Object element) {
		if (element instanceof Group) {
			Group group = (Group) element;
			if(group.getContacts().size() == 0) {
				//Type 1 : Worst Way to do it.
//				Color color = new Color(Display.getDefault(), 255,0,0);
//				return color;
				
				//Type 2 : Retrieving System Colors from Display
//				return Display.getDefault().getSystemColor(SWT.COLOR_RED);
				
				//Type 3 : Retrieve from Color Registry
				Color color = JFaceResources.getColorRegistry().get("RED");
				if(color == null) {
					JFaceResources.getColorRegistry().put("RED", new RGB(255, 0, 0));
					color = JFaceResources.getColorRegistry().get("RED");
				}
				return color;
			}
		}
		return null;
	}

	@Override
	public Color getBackground(Object element) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Font getFont(Object element) {
		if (element instanceof Group) {
			Group group = (Group) element;
			if (group.getContacts().size() == 0) {
//				Font font = JFaceResources.getFontRegistry().get("EMPTY_GROUP");
//				if (font == null) {
//					JFaceResources.getFontRegistry().put("EMPTY_GROUP", new FontData[]{new FontData("ARIAL", 10, SWT.BOLD)});
//					font = JFaceResources.getFontRegistry().get("EMPTY_GROUP");
//				}
				Font font =  new Font(Display.getDefault(), "ARIAL", 10, SWT.ITALIC);
				return font;
			}
		}
		return null;
	}

}
