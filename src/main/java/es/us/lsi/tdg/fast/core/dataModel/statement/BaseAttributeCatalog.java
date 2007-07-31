/**
 * 
 */
package es.us.lsi.tdg.fast.core.dataModel.statement;

import java.util.Set;

/**
 * @author Pablo Fernandez Montes
 * @author José Antonio Parejo Maestre
 *
 */
public class BaseAttributeCatalog implements AttributeCatalog {

	private Set<Attribute> attributes;
	private String name;
	private String semanticDomain;
	
	public BaseAttributeCatalog(Set<Attribute> attributes, String name,
			String semanticDomain) {
		super();
		this.attributes = attributes;
		this.name = name;
		this.semanticDomain = semanticDomain;
	}

	/* (non-Javadoc)
	 * @see es.us.lsi.tdg.fast.core.dataModel.statement.AttributeCatalog#getAtributes()
	 */
	public Set<Attribute> getAttributes() {
		return attributes;
	}

	/* (non-Javadoc)
	 * @see es.us.lsi.tdg.fast.core.dataModel.statement.AttributeCatalog#getName()
	 */
	public String getName() {
		return name;
	}

	/* (non-Javadoc)
	 * @see es.us.lsi.tdg.fast.core.dataModel.statement.AttributeCatalog#getSemanticDomain()
	 */
	public String getSemanticDomain() {
		return semanticDomain;
	}

}
