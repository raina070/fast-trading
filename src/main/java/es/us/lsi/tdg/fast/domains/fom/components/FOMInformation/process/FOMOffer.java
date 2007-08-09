package es.us.lsi.tdg.fast.domains.fom.components.FOMInformation.process;

public class FOMOffer {

	public int timeInit, timeEnd;
	public double cost;

	public FOMOffer(){
		this.timeInit 	= 0;
		this.timeEnd  	= 0;
		this.cost 		= 0;	
	}
	
	public FOMOffer(int timeInit, int timeEnd, double cost){
		this.timeInit 	= timeInit;
		this.timeEnd  	= timeEnd;
		this.cost 		= cost;	
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
		result = result + "(" + cost + ",";
		result = result + timeInit + ",";
		result = result + timeEnd + ")";
		return result;
	}
}
