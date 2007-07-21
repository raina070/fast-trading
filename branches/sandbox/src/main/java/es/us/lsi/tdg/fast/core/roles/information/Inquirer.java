/**
 * 
 */
package es.us.lsi.tdg.fast.core.roles.information;
import es.us.lsi.tdg.fast.core.dataModel.trading.Controllable;
/**
 * 
 * This interface has the responsibility of perform
 * obtain service and trading process information by 
 * polling the Informants of the potential counterparties.
 * 
 * After receiving the collection of potential counterparties, 
 * the Inquirer contacts with the Informant role of each 
 * CounterParty and queries it for the information it considers 
 * relevant. To decide which information is relevant, the Inquirer
 *  may use the agreement preferences. In the process of polling 
 *  the informants, the role can select different strategies of 
 *  querying, depending on the interaction standard and the type 
 *  of information needed to match agreement preferences.
 * 
 * @author José Antonio Parejo Maestre
 * @author Pablo Fernández Montes
 *
 */
public interface Inquirer extends Controllable{
	
}
