package es.us.lsi.tdg.fast.domains.fom.dataModel;

public class FOMProposal {

	private int time;
	private double cost;
	private String counterPartyEndPoint;
	
	public FOMProposal(){
		this.time = 0;
		this.cost = 0;
		this.counterPartyEndPoint = "";
	}
	
	public FOMProposal(int time, double cost, String counterPartyEndPoint){
		this.time	= time;
		this.cost 	= cost;	
		this.counterPartyEndPoint = counterPartyEndPoint;
	}

	public int getTime(){
		return time;
	}
	public double getCost(){
		return cost;
	}
	
	public void setTime(int time){
		this.time = time;
	}
	
	public void setCost(double cost){
		this.cost = cost;
	}
	
	public String toString(){
		String result = "";
		result = result + "(Cost " + cost + ", ";
		result = result + "Time " + time + ", ";
		result = result + "cpEndPoint " + counterPartyEndPoint + ")";
		return result;
	}

	public String getCounterPartyEndPoint() {
		return counterPartyEndPoint;
	}

	public void setCounterPartyEndPoint(String counterPartyEndPoint) {
		this.counterPartyEndPoint = counterPartyEndPoint;
	}
}
