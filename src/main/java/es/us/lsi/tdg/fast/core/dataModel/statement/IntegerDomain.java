/**
 * 
 */
package es.us.lsi.tdg.fast.core.dataModel.statement;

/**
 * @author Pablo Fernandez Montes
 * @author José Antonio Parejo Maestre
 *
 */
public class IntegerDomain implements Domain {

	public static IntegerDomain __instance=null;
	
	private IntegerDomain()
	{	
	}
	
	public static IntegerDomain getInstance()
	{
		if(__instance==null)
			__instance=new IntegerDomain();
		return __instance;
	}
	
	/* (non-Javadoc)
	 * @see es.us.lsi.tdg.fast.core.dataModel.statement.Domain#belongs(es.us.lsi.tdg.fast.core.dataModel.statement.Value)
	 */
	public boolean belongs(Value val) {		
		return (val.getDomain()).equals(this);
	}

	/* (non-Javadoc)
	 * @see es.us.lsi.tdg.fast.core.dataModel.statement.Domain#getInfinite()
	 */
	public Value getInfinite() {		
		return null;
	}

	public Value parseValue(String value) {
		// TODO Auto-generated method stub
		return new IntegerValue(Integer.parseInt(value));
	}

}
