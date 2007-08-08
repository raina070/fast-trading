/**
 * 
 */
package es.us.lsi.tdg.fast.components.information.inquirer;

import es.us.lsi.tdg.fast.core.roles.RoleAdaptor;
import es.us.lsi.tdg.fast.core.roles.selection.proposalBuilder.ProposalBuilder;

/**
 * 
 * 
 * 
 * @author Pablo Fernandez Montes
 * @author José Antonio Parejo Maestre
 *
 */
public interface InquirerProposalBuilderAdaptor extends ProposalBuilder, RoleAdaptor {
		public void setProposalBuilder(ProposalBuilder propBuilder);		
}
