package es.us.lsi.tdg.fast.components.information;

import es.us.lsi.tdg.fast.core.roles.ControllableProcess;
import es.us.lsi.tdg.fast.core.roles.information.Inquirer;
import es.us.lsi.tdg.fast.core.roles.information.inquirer.InquirerProposalBuilderAdaptor;
import es.us.lsi.tdg.fast.core.roles.information.inquirer.InquirerTrackerAdaptor;

public class InformationComponent {

	// Adapters for the offered roles: 
	protected InquirerTrackerAdaptor inquirerTrackerAdaptor;
	protected InquirerProposalBuilderAdaptor inquirerProposalBuilderAdaptor;
	
	public InformationComponent(InquirerTrackerAdaptor inquirerTrackerAdaptor,
			InquirerProposalBuilderAdaptor inquirerProposalBuilderAdaptor,
			ControllableProcess inquirerProcess,
			ControllableProcess informantProcess) {
		super();
		this.inquirerTrackerAdaptor = inquirerTrackerAdaptor;
		this.inquirerProposalBuilderAdaptor = inquirerProposalBuilderAdaptor;
		this.inquirerProcess = inquirerProcess;
		this.informantProcess = informantProcess;
	}
	
	// Processes associated to the offered roles:
	protected ControllableProcess inquirerProcess;
	protected ControllableProcess informantProcess;
	
	
	// Offered Roles
	public Inquirer getInquirer()
	{
		return inquirerTrackerAdaptor;
	}
	
	// Controllable Processes of the offered roles:
	public ControllableProcess getInquirerProcess()
	{
		return inquirerProcess;
	}
	
	public ControllableProcess getInformantProcess()
	{
		return informantProcess;
	}

	
	
}
