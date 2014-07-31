package org.ancit.examples.pde.perspectives;

import org.ancit.examples.pde.views.TrainingNavigator;
import org.eclipse.ui.IFolderLayout;
import org.eclipse.ui.IPageLayout;
import org.eclipse.ui.IPerspectiveFactory;

public class TrainingPerspective implements IPerspectiveFactory {

	@Override
	public void createInitialLayout(IPageLayout layout) {
		layout.setEditorAreaVisible(false);
		
		String editorArea = layout.getEditorArea();
		
		IFolderLayout leftFolder = layout.createFolder("left", IPageLayout.LEFT, 0.3f, editorArea);
		leftFolder.addView("org.ancit.examples.pde.views.TrainingNavigator");
		
		layout.addView("org.ancit.examples.pde.views.DetailsView", IPageLayout.TOP, 0.3f, IPageLayout.ID_EDITOR_AREA);
		
		IFolderLayout bottomFolder = layout.createFolder("bottom", IPageLayout.BOTTOM, 0.3f, editorArea);
		bottomFolder.addView("org.ancit.examples.pde.views.LocationView");
		
		
	}

}
