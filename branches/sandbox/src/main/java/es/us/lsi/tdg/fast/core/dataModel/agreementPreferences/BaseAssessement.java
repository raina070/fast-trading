package es.us.lsi.tdg.fast.core.dataModel.agreementPreferences;


public class BaseAssessement implements Assessment {

	private Double value;
	
	public BaseAssessement(double value)
	{
		this.value=new Double(value);
	}
	
	public int compareTo(Object o) {
		int result=0;
		if(o instanceof Double)
			result=value.compareTo((Double)o);
		else if(o instanceof BaseAssessement)
		{
			result=this.value.compareTo(((BaseAssessement)o).value);
		}else			
			throw new IllegalArgumentException();
		return result;
	}

	
	
	
}
