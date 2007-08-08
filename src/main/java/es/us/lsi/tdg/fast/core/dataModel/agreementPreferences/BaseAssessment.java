package es.us.lsi.tdg.fast.core.dataModel.agreementPreferences;


public class BaseAssessment implements Assessment {

	private Double value;
	
	public BaseAssessment(double value)
	{
		this.value=new Double(value);
	}
	
	public int compareTo(Object o) {
		int result=0;
		if(o instanceof Double)
			result=value.compareTo((Double)o);
		else if(o instanceof BaseAssessment)
		{
			result=this.value.compareTo(((BaseAssessment)o).value);
		}else			
			throw new IllegalArgumentException();
		return result;
	}

	
	
	
}
