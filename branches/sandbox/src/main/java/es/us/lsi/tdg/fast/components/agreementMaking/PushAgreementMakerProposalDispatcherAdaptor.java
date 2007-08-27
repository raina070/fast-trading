/**
 * 
 */
package es.us.lsi.tdg.fast.components.agreementMaking;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import es.us.lsi.tdg.fast.core.choreographies.IllegalChoreographyMethodCallException;
import es.us.lsi.tdg.fast.core.dataModel.agreement.Agreement;
import es.us.lsi.tdg.fast.core.dataModel.agreement.Proposal;
import es.us.lsi.tdg.fast.core.roles.InteractionModel;
import es.us.lsi.tdg.fast.core.roles.agreementMaking.AgreementMaker;
import es.us.lsi.tdg.fast.core.roles.agreementMaking.agreementMaker.AgreementMakerProposalDispatcherAdaptor;
import es.us.lsi.tdg.fast.FAST;

public class PushAgreementMakerProposalDispatcherAdaptor implements
		AgreementMakerProposalDispatcherAdaptor {
	private List<Proposal> proposals;
	
	public PushAgreementMakerProposalDispatcherAdaptor()
	{
		proposals=new LinkedList<Proposal>();
	}
	
	
	public InteractionModel getInteractionModel() {
		return InteractionModel.PUSH;
	}

	public void createAgreement(Proposal proposal) {
		synchronized(proposals)
		{
			proposals.add(proposal);
		}
	}

	public Set<Agreement> createdAgreements() {
		throw new IllegalChoreographyMethodCallException();
	}

	public void dispatch(List<Proposal> proposals) {
		throw new IllegalChoreographyMethodCallException();
		
	}

	public List<Proposal> getProposalsDispatched() {
		List<Proposal> result;
		synchronized (proposals) {
			result=new LinkedList<Proposal>(proposals);
			proposals.clear();
		}
		return result;
	}
}
