package es.us.lsi.tdg.fast.domains.fom.dataModel;

public class FOMProposal {

	public int time;
	public double cost;
	
	public FOMProposal(){
		this.time = 0;
		this.cost = 0;
	}
	
	public FOMProposal(int time, double cost){
		this.time	= time;
		this.cost 	= cost;	
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
		result = result + "(Cost " + cost + ",";
		result = result + "Time " + time + ")";
		return result;
	}
}
