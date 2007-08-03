/**
 * 
 */
package es.us.lsi.tdg.fast.components.information.inquirer;

import es.us.lsi.tdg.fast.core.ProcessingModel;
import es.us.lsi.tdg.fast.core.dataModel.information.CounterPartyKnowledge;
import es.us.lsi.tdg.fast.core.roles.information.inquirer.InquirerProposalBuilderAdaptor;
import es.us.lsi.tdg.fast.core.roles.selection.ProposalBuilder;

/**
 * @author Pablo Fernandez Montes
 * @author José Antonio Parejo Maestre
 *
 */
public class PushInquirerProposalBuilderAdaptor implements InquirerProposalBuilderAdaptor {

	protected ProposalBuilder proposalBuilder;
	
	public PushInquirerProposalBuilderAdaptor(ProposalBuilder proposalBuilder)
	{
		this.proposalBuilder=proposalBuilder;
	}
	
	
	public void newInformation(CounterPartyKnowledge info) {
		proposalBuilder.newInformation(info);		
	}

	public ProcessingModel getProcessingModel() {		
		return ProcessingModel.PUSH;
	}
}
