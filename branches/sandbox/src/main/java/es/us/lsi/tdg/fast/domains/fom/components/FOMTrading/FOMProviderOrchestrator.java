package es.us.lsi.tdg.fast.domains.fom.components.FOMTrading;

import es.us.lsi.tdg.fast.FAST;
import es.us.lsi.tdg.fast.core.component.UnknownComponentException;
import es.us.lsi.tdg.fast.core.roles.AbstractControllableProcess;
import es.us.lsi.tdg.fast.core.roles.ControllableProcess;
import es.us.lsi.tdg.fast.core.shell.command.BaseExitCommand;
import es.us.lsi.tdg.fast.core.shell.command.ExitCommand;
import es.us.lsi.tdg.fast.core.trading.TradingOrchestrator;
import es.us.lsi.tdg.fast.core.trading.TradingProcess;
import es.us.lsi.tdg.fast.domains.fom.components.FOMAgreementMaking.FOMAgreementMaking;
import es.us.lsi.tdg.fast.domains.fom.components.FOMDiscovery.FOMDiscovery;
import es.us.lsi.tdg.fast.domains.fom.components.FOMInformation.FOMInformation;
import es.us.lsi.tdg.fast.domains.fom.components.FOMSelection.FOMSelection;

public class FOMProviderOrchestrator 
			extends AbstractControllableProcess 
			implements TradingOrchestrator {

	TradingProcess tradingProcess;

	ControllableProcess advertiser;

	ControllableProcess informant;
	

	
	ControllableProcess proposalDispatcher;
	ControllableProcess proposalCollector;
	
	ControllableProcess agreementMaker;
	
	
	public FOMProviderOrchestrator(TradingProcess tradingProcess) {
		super("FOMProviderOrchestrator");
		this.tradingProcess = tradingProcess;
		this.tradingProcess.setOrchestrator(this);
		// TODO Instantiate Components and Wire them
	}

	@Override
	protected void run() {
		// TODO Start Components
		
	}

	public void event(String event) {
		// TODO Auto-generated method stub
		if (event.equals("SLA_REACHED")){
			FAST.shell.showMessage("Stoping Trading Process.");
			stop();
		}
		
	}
	
	public void stop(){
		FAST.shell.showMessage("Stoping ProviderOrchestration for PID "+tradingProcess.getPID());
		ExitCommand exitCommand = new BaseExitCommand();
		//FAST.server.stop();
		informant.stop();
		advertiser.stop();
		proposalDispatcher.stop();
		proposalCollector.stop();
		agreementMaker.stop();
		exitCommand.execute(FAST.shell.getShellRender());
		System.exit(0);
	}

	public void start(){

		
		try {
			FOMDiscovery disco = (FOMDiscovery) FAST.componentFactory.getByName("FOMDiscovery");
			FOMInformation info = (FOMInformation) FAST.componentFactory.getByName("FOMInformation");
			FOMSelection select = (FOMSelection) FAST.componentFactory.getByName("FOMSelection");
			FOMAgreementMaking am = (FOMAgreementMaking) FAST.componentFactory.getByName("FOMAgreementMaking");

			disco.setTradingProcess(tradingProcess);
			info.setTradingProcess(tradingProcess);
			select.setTradingProcess(tradingProcess);
			am.setTradingProcess(tradingProcess);
			
			FAST.componentFactory.bind("PushProposalSelectionNotification", select, am);

			advertiser = disco.getAdvertiserProcess();
			
			informant = info.getInformantProcess();
		
			proposalDispatcher = select.getProposalDispatcherProcess();
			proposalCollector = select.getProposalCollectorProcess();
			
			agreementMaker = am.getAgreementMakerProcess();
	
			advertiser.start();
						
			informant.start();

			proposalDispatcher.start();

			proposalCollector.start();

			agreementMaker.start();

		} catch (UnknownComponentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
		
	}
	
}
