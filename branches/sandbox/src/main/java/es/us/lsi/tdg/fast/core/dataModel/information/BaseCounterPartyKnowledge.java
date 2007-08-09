package es.us.lsi.tdg.fast.core.dataModel.information;

public class BaseCounterPartyKnowledge implements CounterPartyKnowledge{
	
	private Information serviceInformation;
	private Information counterPartyInformation;
	private Information tradingPartyInformation;
	
	public BaseCounterPartyKnowledge(Information serviceInformation){
		this.serviceInformation = serviceInformation; 
	}
	
	public Information getCounterPartyInformation() {
		// TODO Auto-generated method stub
		return serviceInformation;
	}

	public Information getServiceInformation() {
		// TODO Auto-generated method stub
		return counterPartyInformation;
	}

	public Information getTradingInformation() {
		// TODO Auto-generated method stub
		return tradingPartyInformation;
	}
	

}
