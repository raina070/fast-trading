/**
 * 
 */
package es.us.lsi.tdg.fast.core.dataModel.statement;

import java.util.Set;

/**
 * @author Pablo Fernandez Montes
 * @author Jos� Antonio Parejo Maestre
 *
 */
public interface AttributeCatalog {
	Set<Attribute> getAttributes();
	String getSemanticDomain();
	String getName();
}
