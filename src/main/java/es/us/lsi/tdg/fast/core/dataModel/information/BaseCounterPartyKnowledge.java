package es.us.lsi.tdg.fast.core.dataModel.information;

import es.us.lsi.tdg.fast.core.dataModel.agreement.CounterParty;

public class BaseCounterPartyKnowledge implements CounterPartyKnowledge{
	
	private Information serviceInformation;
	private Information counterPartyInformation;
	private Information tradingPartyInformation;
	private CounterParty counterParty;
	
	public BaseCounterPartyKnowledge(Information serviceInformation,CounterParty counterParty){
		this.serviceInformation = serviceInformation; 
		this.counterParty = counterParty;
	}
	
	public Information getCounterPartyInformation() {
		// TODO Auto-generated method stub
		return counterPartyInformation;
	}

	public Information getServiceInformation() {
		// TODO Auto-generated method stub
		return serviceInformation;
	}

	public Information getTradingInformation() {
		// TODO Auto-generated method stub
		return tradingPartyInformation;
	}

	public CounterParty getCounterParty() {
		// TODO Auto-generated method stub
		return counterParty;
	}
	

}
