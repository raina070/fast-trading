/**
 * 
 */
package es.us.lsi.tdg.fast.core.dataModel.agreementPreferences;

import java.util.Set;

import es.us.lsi.tdg.fast.core.dataModel.statement.Statement;

/**
 * @author Pablo Fernández Montes
 * @author José Antonio Parejo Maestre
 *
 */
public interface AgreementPreferences {
	Set<Statement> getRequirements();
	Set<Statement> getFeatures();
	AssessmentMechanism getAssessmentMechanism();
}
