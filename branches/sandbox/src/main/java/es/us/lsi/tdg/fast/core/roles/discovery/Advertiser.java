/**
 * 
 */
package es.us.lsi.tdg.fast.core.roles.discovery;
import es.us.lsi.tdg.fast.core.dataModel.trading.Controllable;
/**
 * 
 * This interface has the responsibility of is to publish 
 * certain capabilities and requirements from the agreement 
 * preferences. This process is carried out
by using the market mediator as the facilitator for accessing the market
through the creation of the appropriate market events.
 * 
 * @author Pablo Fern�ndez Montes
 * @author Jos� Antonio Parejo Maestre
 *
 */
public interface Advertiser extends Controllable {
	public void advertise();
}
