package es.us.lsi.tdg.fast.domains.fom;

public class FOMAgreement {

	public int time;
	public double cost;
	
	public FOMAgreement(){
		this.time = 0;
		this.cost = 0;
	}
	
	public FOMAgreement(int time, double cost){
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
		result = result + "(" + cost + ",";
		result = result + time + ")";
		return result;
	}
}
