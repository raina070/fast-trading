/**
 * 
 */
package es.us.lsi.tdg.fast.components.agreementMaking;

import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import es.us.lsi.tdg.fast.core.choreographies.IllegalChoreographyMethodCallException;
import es.us.lsi.tdg.fast.core.dataModel.agreement.Agreement;
import es.us.lsi.tdg.fast.core.dataModel.agreement.Proposal;
import es.us.lsi.tdg.fast.core.roles.ProcessingModel;
import es.us.lsi.tdg.fast.core.roles.agreementMaking.AgreementMaker;
import es.us.lsi.tdg.fast.core.roles.agreementMaking.agreementMaker.AgreementMakerProposalDispatcherAdaptor;

public class PushAgreementMakerProposalDispatcherAdaptor implements
		AgreementMakerProposalDispatcherAdaptor {
	private List<Proposal> proposals;
	
	public PushAgreementMakerProposalDispatcherAdaptor()
	{
		proposals=new LinkedList<Proposal>();
	}
	
	
	public ProcessingModel getProcessingModel() {
		return ProcessingModel.PUSH;
	}

	public void createAgreement(Proposal proposal) {
		proposals.add(proposal);		
	}

	public Set<Agreement> createdAgreements() {
		throw new IllegalChoreographyMethodCallException();
	}

	public void dispatch(List<Proposal> proposals) {
		throw new IllegalChoreographyMethodCallException();
		
	}

	public List<Proposal> getProposalsDispatched(AgreementMaker agreementMaker) {
		return proposals;
	}
}
