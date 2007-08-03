package es.us.lsi.tdg.fast.components.information;

import es.us.lsi.tdg.fast.core.AbstractControllableProcess;
import es.us.lsi.tdg.fast.core.dataModel.agreement.CounterParty;
import es.us.lsi.tdg.fast.core.roles.information.inquirer.InquirerInformantAdaptor;
import es.us.lsi.tdg.fast.core.roles.information.inquirer.InquirerProposalBuilderAdaptor;
import es.us.lsi.tdg.fast.core.roles.information.inquirer.InquirerTrackerAdaptor;

import java.util.Set;

/**
 * 
 * @author Pablo Fernandez Montes
 * @author José Antonio Parejo Maestre
 *
 */
public class InquirerProcess extends AbstractControllableProcess{
	
	private InquirerTrackerAdaptor trackerAdaptor;
	private InquirerProposalBuilderAdaptor proposalBuilderAdaptor;
	private InquirerInformantAdaptor informantAdaptor;
	
	public InquirerProcess(InquirerTrackerAdaptor trackerAdaptor, InquirerProposalBuilderAdaptor proposalBuilderAdaptor,InquirerInformantAdaptor informantAdaptor) {
		this("",trackerAdaptor,proposalBuilderAdaptor,informantAdaptor);				
	}

	public InquirerProcess(String threadName,InquirerTrackerAdaptor trackerAdaptor, InquirerProposalBuilderAdaptor proposalBuilderAdaptor,InquirerInformantAdaptor informantAdaptor)
	{
		super(threadName);
		this.trackerAdaptor=trackerAdaptor;
		this.proposalBuilderAdaptor=proposalBuilderAdaptor;
		this.informantAdaptor=informantAdaptor;
	}
		
	protected  void  doStep()
	{
		Set<CounterParty> counterParties=trackerAdaptor.getNewCounterParties();
		for(CounterParty counterParty:counterParties)
		{
			proposalBuilderAdaptor.newInformation(informantAdaptor.getKnowledge(counterParty));
		}
		
	}
}
