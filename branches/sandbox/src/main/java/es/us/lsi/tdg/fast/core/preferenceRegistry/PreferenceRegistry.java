/**
 * 
 */
package es.us.lsi.tdg.fast.core.preferenceRegistry;

import java.util.Set;

import es.us.lsi.tdg.fast.core.dataModel.agreementPreferences.AgreementPreferences;

/**
 * @author Pablo Fernández Montes
 * @author José Antonio Parejo Maestre
 *
 */
public interface PreferenceRegistry {
	public void setPreferences(String PID, AgreementPreferences preferences);	
	AgreementPreferences getPreferences(String PID);
	public Set<String> getRegisteredIdentifiers();
}
