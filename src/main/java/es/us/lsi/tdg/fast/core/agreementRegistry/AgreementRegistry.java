/**
 * 
 */
package es.us.lsi.tdg.fast.core.agreementRegistry;

import java.util.Set;

import es.us.lsi.tdg.fast.core.dataModel.agreement.Agreement;

/**
 * @author Pablo Fernández Montes
 * @author José Antonio Parejo Maestre
 *
 */
public interface AgreementRegistry {
	public Set<Agreement> getAgreements(String PID);
	public void addAgreement(String PID,Agreement agreement);
	public Set<String> getRegisteredIdentifiers();
}
