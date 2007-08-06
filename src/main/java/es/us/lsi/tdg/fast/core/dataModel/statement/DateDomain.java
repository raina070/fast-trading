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
public class DateDomain implements Domain {

	private static DateDomain __instance=null;
	
	private DateDomain(){	}
	
	public static DateDomain getInstance()
	{
		if(__instance==null)
			__instance=new DateDomain();
		return __instance;
	}
	
	/* (non-Javadoc)
	 * @see es.us.lsi.tdg.fast.core.dataModel.statement.Domain#belongs(es.us.lsi.tdg.fast.core.dataModel.statement.Value)
	 */
	public boolean belongs(Value val) {
		return this.equals(val.getDomain());
	}

	/* (non-Javadoc)
	 * @see es.us.lsi.tdg.fast.core.dataModel.statement.Domain#getInfinite()
	 */
	public Value getInfinite() {
		return null;
	}

	@SuppressWarnings("deprecation")
	public Value parseValue(String value) {
		// TODO Auto-generated method stub
		Date date=new Date(value);
		return new DateValue(date);
	}

}
