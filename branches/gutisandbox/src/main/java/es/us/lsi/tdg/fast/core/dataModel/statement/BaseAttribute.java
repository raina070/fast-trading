/**
 * 
 */
package es.us.lsi.tdg.fast.core.dataModel.statement;

/**
 * @author Pablo Fernandez Montes
 * @author José Antonio Parejo Maestre
 *
 */
public class BaseAttribute implements Attribute {

	private String name;
	private String description;
	private Domain domain;
	
	public BaseAttribute(String name, Domain domain, String description)
	{
		this.name=name;
		this.domain=domain;
		this.description=description;
	}
	
	/* (non-Javadoc)
	 * @see es.us.lsi.tdg.fast.core.dataModel.statement.Attribute#getDomain()
	 */
	public Domain getDomain() {
		return domain;
	}

	public String getName() {
		return name;
	}

	public String getDescription() {
		// TODO Auto-generated method stub
		return description;
	}

}
