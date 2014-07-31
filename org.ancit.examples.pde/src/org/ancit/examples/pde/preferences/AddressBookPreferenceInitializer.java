package org.ancit.examples.pde.preferences;

import org.ancit.examples.pde.Activator;
import org.eclipse.core.runtime.preferences.AbstractPreferenceInitializer;
import org.eclipse.jface.preference.IPreferenceStore;

public class AddressBookPreferenceInitializer extends
		AbstractPreferenceInitializer {

	public AddressBookPreferenceInitializer() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void initializeDefaultPreferences() {
		IPreferenceStore preferenceStore = Activator.getDefault().getPreferenceStore();		
		preferenceStore.setDefault("MAP_SITE", "Google");
	}

}
