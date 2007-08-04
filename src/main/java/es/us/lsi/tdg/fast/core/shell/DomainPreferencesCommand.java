/**
 * 
 */
package es.us.lsi.tdg.fast.core.shell;

import es.us.lsi.tdg.fast.core.dataModel.statement.Attribute;
import es.us.lsi.tdg.fast.core.dataModel.statement.AttributeCatalog;

/**
 * @author Pablo Fern�ndez Montes
 * @author Jos� Antonio Parejo Maestre
 *
 */
public interface DomainPreferencesCommand extends Command {
	public AttributeCatalog getAttributeCatalog();	
}
