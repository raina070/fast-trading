/**
 * 
 */
package es.us.lsi.tdg.fast.components.selection;


import es.us.lsi.tdg.fast.core.choreographies.Choreography;
import es.us.lsi.tdg.fast.core.component.Component;
import es.us.lsi.tdg.fast.core.roles.ControllableProcess;
import es.us.lsi.tdg.fast.core.roles.selection.ProposalFilter;
import es.us.lsi.tdg.fast.core.roles.selection.Proponent.ProponentAgreementMakerAdaptor;
import es.us.lsi.tdg.fast.core.roles.selection.Proponent.ProponentProposalCollectorAdaptor;
import es.us.lsi.tdg.fast.core.roles.selection.proposalBuilder.ProposalBuilder;
import es.us.lsi.tdg.fast.core.roles.selection.proposalBuilder.ProposalBuilderInquirerAdaptor;
import es.us.lsi.tdg.fast.core.roles.selection.proposalCollector.ProposalCollector;
import es.us.lsi.tdg.fast.core.roles.selection.proposalCollector.ProposalCollectorProponentAdaptor;
import es.us.lsi.tdg.fast.core.roles.selection.proposalDispatcher.ProposalDispatcher;
import es.us.lsi.tdg.fast.core.roles.selection.proposalDispatcher.ProposalDispatcherAgreementMakerAdaptor;
import es.us.lsi.tdg.fast.core.trading.TradingProcess;

/**
 * @author Pablo Fernández Montes
 * @author José Antonio Parejo Maestre
 *
 */
public class SelectionComponent implements Component {
	
	// from Component
	protected String type;
	protected String name;
	protected Choreography wiringChoreography;	
	protected TradingProcess tradingProcess;
	
	// Selection roles Adaptors
	protected ProposalBuilderInquirerAdaptor proposalBuilderInquirerAdaptor;
	protected ProposalDispatcherAgreementMakerAdaptor proposalDispatcherAgreementMakerAdaptor;
	protected ProposalCollectorProponentAdaptor proposaCollectorProponentAdaptor;
	// Selection roles processes:
	protected ControllableProcess selectionProcess; 
	
	// Participant roles:
	protected ProposalBuilder proposalBuilder;
	protected ProposalCollector proposalCollector;
	protected ProposalDispatcher proposalDispatcher;
	
	
	// Proposition roles adaptors
	protected ProponentProposalCollectorAdaptor proponentProposalCollectorAdaptor;
	protected ProponentAgreementMakerAdaptor proponentAgreementMakerAdaptor;
	
	protected ControllableProcess proponentProcess;
	
			 
	public ControllableProcess getProponentProcess()
	{
		return proponentProcess;
	}
	
	public ControllableProcess getSelectionProcess()
	{
		return selectionProcess;
	}
	
	public ProposalBuilder getProposalBuilder()
	{
		return proposalBuilderInquirerAdaptor;
	}

	public String getName() { 
		return name;
	}

	public String getType() {		
		return type;
	}

	public void setTradingProcess(TradingProcess tradingProcess) {		
		this.tradingProcess=tradingProcess;
	}

	public void setWiringChoreography(Choreography wiringChoreography) {
		this.wiringChoreography=wiringChoreography;		
	}

	public TradingProcess getTradingProcess() {
			return tradingProcess;
	}
}
