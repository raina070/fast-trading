/**
 * 
 */
package es.us.lsi.tdg.fast.core.dataModel.statement;

import java.util.Date;

/**
 * @author Pablo Fernandez Montes
 * @author José Antonio Parejo Maestre
 *
 */
public class DateValue implements ComparableValue {

	private Domain domain;
	private Date value;
	
	public DateValue(Date value)
	{
		this.domain=DateDomain.getInstance();
		this.value=value;
	}
	
	/* (non-Javadoc)
	 * @see es.us.lsi.tdg.fast.core.dataModel.statement.Value#getDomain()
	 */
	public Domain getDomain() {
		// TODO Auto-generated method stub
		return domain;
	}

	/* (non-Javadoc)
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	public int compareTo(Object o) {
		int result=0;
		if(o instanceof DateValue)
		{
			DateValue dval=(DateValue)o;
			result=value.compareTo(dval.value);
		}else
			throw new IllegalArgumentException();
		return result;		
	}

}
