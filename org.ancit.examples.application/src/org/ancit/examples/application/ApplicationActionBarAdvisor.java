package org.ancit.examples.application;

import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.actions.ActionFactory;
import org.eclipse.ui.actions.ActionFactory.IWorkbenchAction;
import org.eclipse.ui.application.ActionBarAdvisor;
import org.eclipse.ui.application.IActionBarConfigurer;

public class ApplicationActionBarAdvisor extends ActionBarAdvisor {

    private IWorkbenchAction exitAction;
	private IWorkbenchAction importAction;
	private IWorkbenchAction preferencesAction;
	private IWorkbenchAction propertiesAction;
	private IWorkbenchAction aboutAction;

	public ApplicationActionBarAdvisor(IActionBarConfigurer configurer) {
        super(configurer);
    }

    protected void makeActions(IWorkbenchWindow window) {
    	
    	importAction = ActionFactory.IMPORT.create(window);    	
    	exitAction = ActionFactory.QUIT.create(window);
    	preferencesAction = ActionFactory.PREFERENCES.create(window);
    	propertiesAction = ActionFactory.PROPERTIES.create(window);
    	aboutAction = ActionFactory.ABOUT.create(window);
    }

    protected void fillMenuBar(IMenuManager menuBar) {
    	
    	MenuManager fileMenu = new MenuManager("File");
    	fileMenu.add(importAction);
    	fileMenu.add(propertiesAction);
    	fileMenu.add(exitAction);
    	
    	MenuManager windowMenu = new MenuManager("Window");
    	windowMenu.add(preferencesAction);
    	
    	MenuManager helpMenu = new MenuManager("Help");
    	helpMenu.add(aboutAction);
    	
    	menuBar.add(fileMenu);
    	menuBar.add(windowMenu);
    	menuBar.add(helpMenu);
    	
    }
    
}
