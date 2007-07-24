/**
 * 
 */
package es.us.lsi.tdg.fast.components.information.inquirer;

import es.us.lsi.tdg.fast.core.dataModel.CounterParty;
import es.us.lsi.tdg.fast.core.dataModel.CounterPartyKnowledge;
import es.us.lsi.tdg.fast.core.roles.information.Informant;

/**
 * 
 * @author Pablo Fernandez Montes
 * @author José Antonio Parejo Maestre
 * 
 *
 */
public interface InquirerInformantAdaptor extends Informant {
	public CounterPartyKnowledge getKnowledge(CounterParty counterParty);	
}
