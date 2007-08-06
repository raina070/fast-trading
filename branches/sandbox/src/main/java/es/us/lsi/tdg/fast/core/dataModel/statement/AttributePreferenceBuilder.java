/**
 * 
 */
package es.us.lsi.tdg.fast.core.dataModel.statement;

/**
 * @author Pablo Fernández Montes
 * @author José Antonio Parejo Maestre
 *
 */
public class AttributePreferenceBuilder implements StatementFactory {

	private Attribute attribute;
	
	public AttributePreferenceBuilder(Attribute attribute)
	{
		this.attribute=attribute;
	}
	
	/* (non-Javadoc)
	 * @see es.us.lsi.tdg.fast.core.dataModel.statement.StatementFactory#create()
	 */
	public Statement create() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see es.us.lsi.tdg.fast.core.dataModel.statement.StatementFactory#create(java.lang.String)
	 */
	public Statement create(String definition) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see es.us.lsi.tdg.fast.core.dataModel.statement.StatementFactory#create(java.lang.String[])
	 */
	public Statement create(String[] definition) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public Attribute getAttribute(){
		return attribute;
	}

}
