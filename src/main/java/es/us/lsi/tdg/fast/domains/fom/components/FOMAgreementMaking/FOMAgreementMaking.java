package es.us.lsi.tdg.fast.domains.fom.components.FOMAgreementMaking;

import es.us.lsi.tdg.fast.core.choreographies.Choreography;
import es.us.lsi.tdg.fast.core.choreographies.wiring.ProposalSelectionNotification;
import es.us.lsi.tdg.fast.core.component.UnwiredComponent;
import es.us.lsi.tdg.fast.core.component.Component;
import es.us.lsi.tdg.fast.core.roles.ControllableProcess;
import es.us.lsi.tdg.fast.core.roles.agreementMaking.AgreementMaker;
import es.us.lsi.tdg.fast.core.roles.agreementMaking.agreementMaker.AgreementMakerProposalDispatcherAdaptor;
import es.us.lsi.tdg.fast.core.trading.TradingProcess;
import es.us.lsi.tdg.fast.domains.fom.components.FOMAgreementMaking.process.FOMAgreementMakerProcess;

public class FOMAgreementMaking implements Component{
 
		protected AgreementMakerProposalDispatcherAdaptor agreementMaker=null;
		
		// Processes associated to the offered roles:
		protected ControllableProcess agreementMakerProcess=null;
		
		private String name="FOMAgreementMaking";
		private String type="AgreementMaking";
		TradingProcess tradingProcess = null;
		
		public String getName() {
			return name;
		}

		public AgreementMaker getAgreementMaker() {
			return agreementMaker;
		}

		public ControllableProcess getAgreementMakerProcess() {
			
			if(agreementMaker == null){
				throw new UnwiredComponent("agreementMakerProposalDispatcherAdaptor");
			}else
				this.agreementMakerProcess = new FOMAgreementMakerProcess(this.agreementMaker);			
			return agreementMakerProcess;
		}

		public String getType() {
			return type;
		}

		public void setWiringChoreography(Choreography wiringChoreography) {
			String woType = wiringChoreography.getType();
			
			if (woType.equals("ProposalSelectionNotification"))
				this.agreementMaker = ((ProposalSelectionNotification) wiringChoreography).getAgreementMakerProposalDispatcherAdaptor();
			
			// TODO Auto-generated method stub
		}

		public void setTradingProcess(TradingProcess tradingProcess) {
			this.tradingProcess = tradingProcess;
		} 
		

}
