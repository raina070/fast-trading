/**
 * 
 */
package es.us.lsi.tdg.fast.core.preferenceRegistry;

import java.util.Set;

import es.us.lsi.tdg.fast.core.dataModel.agreementPreferences.AgreementPreferences;

/**
 * @author Pablo Fern�ndez Montes
 * @author Jos� Antonio Parejo Maestre
 *
 */
public interface PreferenceRegistry {
	public void setPreferences(String IDC, AgreementPreferences preferences);	
	AgreementPreferences getPreferences(String IDC);
	public Set<String> getRegisteredIdentifiers();
}
