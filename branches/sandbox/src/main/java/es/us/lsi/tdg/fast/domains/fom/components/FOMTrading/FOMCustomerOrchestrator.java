package es.us.lsi.tdg.fast.domains.fom.components.FOMTrading;

import es.us.lsi.tdg.fast.core.component.Component;
import es.us.lsi.tdg.fast.core.component.UnknownComponentException;
import es.us.lsi.tdg.fast.core.roles.AbstractControllableProcess;
import es.us.lsi.tdg.fast.core.roles.ControllableProcess;
import es.us.lsi.tdg.fast.core.shell.ShellRender;
import es.us.lsi.tdg.fast.core.shell.command.BaseExitCommand;
import es.us.lsi.tdg.fast.core.shell.command.ExitCommand;
import es.us.lsi.tdg.fast.core.trading.TradingOrchestrator;
import es.us.lsi.tdg.fast.core.trading.TradingProcess;
import es.us.lsi.tdg.fast.domains.fom.components.FOMAgreementMaking.FOMAgreementMaking;
import es.us.lsi.tdg.fast.domains.fom.components.FOMDiscovery.FOMDiscovery;
import es.us.lsi.tdg.fast.domains.fom.components.FOMInformation.FOMInformation;
import es.us.lsi.tdg.fast.domains.fom.components.FOMSelection.FOMSelection;
import es.us.lsi.tdg.fast.FAST;

public class FOMCustomerOrchestrator 
			extends AbstractControllableProcess 
			implements TradingOrchestrator {

	protected TradingProcess tradingProcess;

	ControllableProcess discoveryService;
	ControllableProcess advertiser;
	ControllableProcess tracker;

	ControllableProcess informant;
	ControllableProcess inquirer;

	ControllableProcess proposalBuilder;
	ControllableProcess proposalDispatcher;
	ControllableProcess proposalCollector;
	
	ControllableProcess agreementMaker;

	
	public FOMCustomerOrchestrator(TradingProcess tradingProcess) {
		super("FOMCustomerOrchestrator");
		this.tradingProcess = tradingProcess;
		this.tradingProcess.setOrchestrator(this);
		// TODO Instantiate Components and Wire them
	}

	public void start(){
		FAST.shell.showMessage("  Starting CustomerOrchestration for PID "+tradingProcess.getPID());
		
		try {
		
			FOMDiscovery disco = (FOMDiscovery) FAST.componentFactory.getByName("FOMDiscovery");
			FOMInformation info = (FOMInformation) FAST.componentFactory.getByName("FOMInformation");
			FOMSelection select = (FOMSelection) FAST.componentFactory.getByName("FOMSelection");
			FOMAgreementMaking am = (FOMAgreementMaking) FAST.componentFactory.getByName("FOMAgreementMaking");

			disco.setTradingProcess(tradingProcess);
			info.setTradingProcess(tradingProcess);
			select.setTradingProcess(tradingProcess);
			am.setTradingProcess(tradingProcess);
			
			FAST.componentFactory.bind("PullPotentialCounterPartyNotification", disco, info);
			FAST.componentFactory.bind("PushNewInformationNotification", info, select);
			FAST.componentFactory.bind("PushProposalSelectionNotification", select, am);

			discoveryService = disco.getDiscoveryServiceProcess();
			//advertiser = disco.getAdvertiserProcess();
			tracker = disco.getTrackerProcess();

			//informant = info.getInformantProcess();
			inquirer = info.getInquirerProcess();

			proposalBuilder = select.getProposalBuilderProcess();
			proposalDispatcher = select.getProposalDispatcherProcess();
			proposalCollector = select.getProposalCollectorProcess();
			
			agreementMaker = am.getAgreementMakerProcess();
	
			discoveryService.start();
			//advertiser.start();
			tracker.start();
			
			//informant.start();

			inquirer.start();
			
			proposalBuilder.start();
			proposalDispatcher.start();

			proposalCollector.start();

			agreementMaker.start();
					
		} catch (UnknownComponentException e) {
			e.printStackTrace();
		}
	}
	
	public void stop(){
		/*
		discoveryService.stop();
		advertiser.stop();
		tracker.stop();
		informant.stop();
		inquirer.stop();
		proposalBuilder.stop();
		proposalDispatcher.stop();
		proposalCollector.stop();
		agreementMaker.stop();
		*/
		FAST.shell.showMessage("Stoping CustomerOrchestration for PID "+tradingProcess.getPID());
		ExitCommand exitCommand = new BaseExitCommand();
		//FAST.server.stop();
		exitCommand.execute(FAST.shell.getShellRender());		
		System.exit(0);
	}
	
	public void event(String event) {
		
		if(event.equals("SLA_REACHED")){
			FAST.shell.showMessage("SLA Reached.");
			stop();
		}
	}

	@Override
	protected void run() {
	}

}
