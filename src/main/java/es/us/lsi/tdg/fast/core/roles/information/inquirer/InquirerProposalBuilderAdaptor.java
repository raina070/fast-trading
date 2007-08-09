/**
 * 
 */
package es.us.lsi.tdg.fast.core.roles.information.inquirer;

import es.us.lsi.tdg.fast.core.roles.RoleAdaptor;
import es.us.lsi.tdg.fast.core.roles.selection.proposalBuilder.ProposalBuilder;
import es.us.lsi.tdg.fast.core.roles.information.Inquirer;
/**
 * 
 * 
 * 
 * @author Pablo Fernandez Montes
 * @author José Antonio Parejo Maestre
 *
 */
public interface InquirerProposalBuilderAdaptor extends ProposalBuilder, Inquirer, RoleAdaptor {
	public void setProposalBuilder(ProposalBuilder propBuilder);
}
