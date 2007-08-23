package es.us.lsi.tdg.fast.domains.fom.dataModel;

public class FOMOffer {

	private int timeInit, timeEnd;
	private double cost;
	private String counterpartyEndPoint;
	

	public String getCounterpartyEndPoint() {
		return counterpartyEndPoint;
	}

	public void setCounterpartyEndPoint(String counterpartyEndPoint) {
		this.counterpartyEndPoint = counterpartyEndPoint;
	}

	public FOMOffer(){
		this.timeInit 	= 0;
		this.timeEnd  	= 0;
		this.cost 		= 0;	
		this.counterpartyEndPoint 		= null;	
		
	}
	
	public FOMOffer(int timeInit, int timeEnd, double cost, String counterPartyEndPoint){
		this.timeInit 	= timeInit;
		this.timeEnd  	= timeEnd;
		this.cost 		= cost;	
		this.counterpartyEndPoint = counterPartyEndPoint; 
	}
	
	public int getTimeInit(){
		return timeInit;
	}
	
	public int getTimeEnd(){
		return timeEnd;
	}
	
	public double getCost(){
		return cost;
	}
	
	public void setTimeInit(int timeInit){
		this.timeInit = timeInit;
	}
	
	public void setTimeEnd(int timeEnd){
		this.timeEnd = timeEnd;
	}
	
	public void setCost(double cost){
		this.cost = cost;
	}
	
	public String toString(){
		String result = "";
		result = result + "(cost " + cost + ",";
		result = result + "time[" + timeInit + ",";
		result = result + timeEnd + "])";
		return result;
	}
}
