/**
 * 
 */
package es.us.lsi.tdg.fast.core.dataModel.statement;

/**
 * @author Pablo Fernandez Montes
 * @author José Antonio Parejo Maestre
 *
 */
public class IntegerValue implements ComparableValue {

	private Domain domain;
	private int value;
	
	public IntegerValue(int value)
	{
		domain=IntegerDomain.getInstance();
		this.value=value;
	}
	
	/* (non-Javadoc)
	 * @see es.us.lsi.tdg.fast.core.dataModel.statement.Value#getDomain()
	 */
	public Domain getDomain() {
		return domain;
	}
	
	public int getValue(){
		return value;
	}

	public int compareTo(Object o) {
		int result=0;
		// TODO Change implementation, we should test all numeric compatible values, and throw a Exception in other case 
		if(o instanceof IntegerValue)
		{
			IntegerValue val=(IntegerValue)o;
			result=value-val.value;
		}else
			throw new IllegalArgumentException();
		return result;
	}
	
}
