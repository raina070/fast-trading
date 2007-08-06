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
	public Statement create(String[] definition) throws Exception {
		// TODO Auto-generated method stub
		Statement result=null;
		if(definition.length==1)
		{
			result=new BaseSimpleConstraint(attribute.getDomain().parseValue(definition[0]),attribute,StatementType.TRADING);
		}else if(definition.length==2)
		{
			result=new BaseSortedDomainConstraint((ComparableValue)attribute.getDomain().parseValue(definition[0]),(ComparableValue)attribute.getDomain().parseValue(definition[1]),attribute,StatementType.TRADING);
		}
		return result;
	}
	
	public Attribute getAttribute(){
		return attribute;
	}

}
