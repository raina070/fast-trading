package es.us.lsi.tdg.fast.domains.fom.dataModel;

public class FOMAgreementPreferences {

	private int timeInit, timeEnd;
	private double cost;
	
	public FOMAgreementPreferences(){
		this.timeInit 	= 0;
		this.timeEnd  	= 0;
		this.cost 		= 0;		
	}
	
	public FOMAgreementPreferences(int timeInit, int timeEnd, double cost){
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
		result = result + "(cost " + cost + ",";
		result = result + "time[" + timeInit + ",";
		result = result + timeEnd + "])";
		return result;
	}
}
