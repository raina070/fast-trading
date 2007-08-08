package es.us.lsi.tdg.fast.components.information.inquirer.process;

import java.util.Set;

import es.us.lsi.tdg.fast.core.dataModel.agreement.CounterParty;
import es.us.lsi.tdg.fast.core.roles.AbstractControllableProcess;
import es.us.lsi.tdg.fast.core.roles.discovery.Tracker;
import es.us.lsi.tdg.fast.core.roles.information.Informant;
import es.us.lsi.tdg.fast.core.roles.selection.proposalBuilder.ProposalBuilder;

/**
 * 
 * @author Pablo Fernandez Montes
 * @author José Antonio Parejo Maestre
 *
 */
public class InquirerProcess extends AbstractControllableProcess{
	
	private Tracker tracker;
	private ProposalBuilder proposalBuilder;
	private Informant informant;
	
	public InquirerProcess(Tracker tracker, ProposalBuilder proposalBuilder,Informant informant) {
		this("",tracker,proposalBuilder,informant);				
	}

	public InquirerProcess(String threadName,Tracker tracker, ProposalBuilder proposalBuilder,Informant informant)
	{
		super(threadName);
		this.tracker = tracker;
		this.proposalBuilder = proposalBuilder;
		this.informant = informant;
	}
		
	protected  void  run()
	{
		Set<CounterParty> counterParties=tracker.getNewCounterParties();
		for(CounterParty counterParty:counterParties)
		{
			proposalBuilder.newInformation(informant.getKnowledge(counterParty));
		}
		
	}
}
