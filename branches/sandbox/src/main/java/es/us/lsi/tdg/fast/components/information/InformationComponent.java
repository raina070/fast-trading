package es.us.lsi.tdg.fast.components.information;

import es.us.lsi.tdg.fast.components.information.inquirer.InquirerTrackerAdaptor;
import es.us.lsi.tdg.fast.components.information.inquirer.InquirerProposalBuilderAdaptor;
import es.us.lsi.tdg.fast.core.ControllableProcess;
import es.us.lsi.tdg.fast.core.ControllableProcess;
import es.us.lsi.tdg.fast.core.roles.information.Informant;
import es.us.lsi.tdg.fast.core.roles.information.Inquirer;

public class InformationComponent {

	
	
	
	// Adapters for the offered roles: 
	protected InquirerTrackerAdaptor inquirerTrackerAdaptor;
	protected InquirerProposalBuilderAdaptor inquirerProposalBuilderAdaptor;
	
	// Processes associated to the offered roles:
	protected ControllableProcess inquirerProcess;
	protected ControllableProcess informantProcess;
	
	
	// Offered Roles
	public Inquirer getInquirer()
	{
		return null;
	}
	
	public Informant getInformant()
	{
		return null;
	}

	// Controllable Processes of the ofered roles:
	public ControllableProcess getInquirerProcess()
	{
		return inquirerProcess;
	}
	
	public ControllableProcess getInformantProcess()
	{
		return informantProcess;
	}
	
}
