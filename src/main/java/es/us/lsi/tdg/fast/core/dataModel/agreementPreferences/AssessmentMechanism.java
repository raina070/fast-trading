/**
 * 
 */
package es.us.lsi.tdg.fast.core.dataModel.agreementPreferences;

import es.us.lsi.tdg.fast.core.dataModel.agreement.Agreement;

/**
 * @author Pablo Fernández Montes
 * @author José Antonio Parejo Maestre
 *
 */
public interface AssessmentMechanism {
	public Assessment assess(AgreementPreferences prefs,Agreement agreements);
}
