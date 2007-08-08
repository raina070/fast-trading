/**
 * 
 */
package es.us.lsi.tdg.fast.core.shell;

import es.us.lsi.tdg.fast.core.dataModel.statement.AttributeCatalog;
import es.us.lsi.tdg.fast.core.shell.command.Command;

/**
 * @author Pablo Fernández Montes
 * @author José Antonio Parejo Maestre
 *
 */
public interface DomainPreferencesCommand extends Command {
	public AttributeCatalog getAttributeCatalog();	
}
