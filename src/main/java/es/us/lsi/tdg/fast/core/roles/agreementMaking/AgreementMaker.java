/**
 * 
 */
package es.us.lsi.tdg.fast.core.roles.agreementMaking;

import java.util.Set;

import es.us.lsi.tdg.fast.core.dataModel.agreement.Agreement;
import es.us.lsi.tdg.fast.core.dataModel.agreement.Proposal;

/**
 * 
 * This interface has the responsibility of to come up with 
 * created and signed agreements. To create those agreements, 
 * a negotiation process may be followed.
 * Therefore, this is the role that implements our Agreement 
 * creation mechanism and it must understand an Agreement format 
 * and support at least one protocol to create agreements.
 * 
 * @author José Antonio Parejo Maestre
 * @author Pablo Fernández Montes
 *
 */
public interface AgreementMaker {
	public void createAgreement(Proposal proposal);
	public Set<Agreement> createdAgreements();
}
