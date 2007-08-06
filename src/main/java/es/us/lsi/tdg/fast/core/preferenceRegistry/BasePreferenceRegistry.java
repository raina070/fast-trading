/**
 * 
 */
package es.us.lsi.tdg.fast.core.preferenceRegistry;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import es.us.lsi.tdg.fast.core.dataModel.agreementPreferences.AgreementPreferences;

/**
 * @author Pablo Fernández Montes
 * @author José Antonio Parejo Maestre
 *
 */
public class BasePreferenceRegistry implements PreferenceRegistry {

	 private Map<String,AgreementPreferences> registry;
	
	public BasePreferenceRegistry(){
		registry=new HashMap<String,AgreementPreferences>();
	}
	 
	/* (non-Javadoc)
	 * @see es.us.lsi.tdg.fast.core.preferencesRegistry.PreferencesRegistry#getPreferences(java.lang.String)
	 */
	public AgreementPreferences getPreferences(String IDC) {		
		return registry.get(IDC);
	}

	/* (non-Javadoc)
	 * @see es.us.lsi.tdg.fast.core.preferencesRegistry.PreferencesRegistry#getRegisteredIdentifiers()
	 */
	public Set<String> getRegisteredIdentifiers() {		
		return registry.keySet();
	}

	/* (non-Javadoc)
	 * @see es.us.lsi.tdg.fast.core.preferencesRegistry.PreferencesRegistry#setPreferences(java.lang.String, es.us.lsi.tdg.fast.core.dataModel.agreementPreferences.AgreementPreferences)
	 */
	public void setPreferences(String IDC, AgreementPreferences preferences) {
		registry.put(IDC, preferences);
	}

}
