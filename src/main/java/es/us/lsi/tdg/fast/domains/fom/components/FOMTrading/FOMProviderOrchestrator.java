package es.us.lsi.tdg.fast.domains.fom.components.FOMTrading;

import es.us.lsi.tdg.fast.FAST;
import es.us.lsi.tdg.fast.core.component.UnknownComponentException;
import es.us.lsi.tdg.fast.core.process.AbstractControllableProcess;
import es.us.lsi.tdg.fast.core.process.OLDAbstractControllableProcess;
import es.us.lsi.tdg.fast.core.process.ControllableProcess;
import es.us.lsi.tdg.fast.core.process.ProcessModel;
import es.us.lsi.tdg.fast.core.process.terminator.ProcessTerminator;
import es.us.lsi.tdg.fast.core.process.terminator.TimeOutTerminator;
import es.us.lsi.tdg.fast.core.shell.command.BaseExitCommand;
import es.us.lsi.tdg.fast.core.shell.command.ExitCommand;
import es.us.lsi.tdg.fast.core.trading.TradingOrchestrator;
import es.us.lsi.tdg.fast.core.trading.TradingProcess;
import es.us.lsi.tdg.fast.domains.fom.components.FOMAgreementMaking.FOMAgreementMaking;
import es.us.lsi.tdg.fast.domains.fom.components.FOMDiscovery.FOMDiscovery;
import es.us.lsi.tdg.fast.domains.fom.components.FOMInformation.FOMInformation;
import es.us.lsi.tdg.fast.domains.fom.components.FOMSelection.FOMSelection;
import es.us.lsi.tdg.fast.domains.fom.dataModel.FOMProposal;
import es.us.lsi.tdg.fast.domains.fom.dataModel.FOMProposalTranslator;

public class FOMProviderOrchestrator 
			extends AbstractControllableProcess 
			implements TradingOrchestrator {

	TradingProcess tradingProcess;

	FOMDiscovery disco;
	FOMInformation info;
	FOMSelection select;
	FOMAgreementMaking am;
	
	public FOMProviderOrchestrator(TradingProcess tradingProcess) {
		super("FOMProviderOrchestrator");
		this.tradingProcess = tradingProcess;
		this.tradingProcess.setOrchestrator(this);
	}

	public FOMProviderOrchestrator(TradingProcess tradingProcess,
			ProcessModel model) {
		super("FOMProviderOrchestrator",model.getTerminator());
		this.tradingProcess = tradingProcess;
		this.tradingProcess.setOrchestrator(this);
	}

	@Override
	protected void run() {
		FAST.shell.showMessage("  Starting CustomerOrchestration for PID "+tradingProcess.getPID());
		
		try {
		
			disco = (FOMDiscovery) FAST.componentFactory.getByName("FOMDiscovery",tradingProcess);
			info = (FOMInformation) FAST.componentFactory.getByName("FOMInformation",tradingProcess);
			select = (FOMSelection) FAST.componentFactory.getByName("FOMSelection",tradingProcess);
			am = (FOMAgreementMaking) FAST.componentFactory.getByName("FOMAgreementMaking",tradingProcess);
			
			FAST.componentFactory.bind("PushProposalSelectionNotification", select, am);
			
			
			ProcessTerminator timeOut = new TimeOutTerminator(100000);
	
			
			disco.getAdvertiserProcess().start(ProcessModel.SINGLE);
			
			info.getInformantProcess().start(timeOut);
		
			select.getProposalDispatcherProcess().start(timeOut);
			select.getProposalCollectorProcess().start(timeOut);
			
			am.getAgreementMakerProcess().start(timeOut);
			Thread.sleep(100000);			
			
		} catch (UnknownComponentException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			FAST.shell.showMessage(" CustomerOrchestration for PID "+tradingProcess.getPID()+" INTERRUPTED.");					
		}		
	}

	public void OLD_event(String event) {
		if (event.equals("SLA_REACHED")){
			stop();
		}
		
	}
	public void cleanUp(){
		
		FAST.shell.showMessage("Stoping ProviderOrchestration for PID "+tradingProcess.getPID());

		disco.getAdvertiserProcess().stop();
		info.getInformantProcess().stop();
		select.getProposalDispatcherProcess().stop();
		select.getProposalCollectorProcess().stop();
		
		
	}


	
}
