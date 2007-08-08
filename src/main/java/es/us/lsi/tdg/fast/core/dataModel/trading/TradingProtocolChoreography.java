/**
 * 
 */
package es.us.lsi.tdg.fast.core.dataModel.trading;

import java.util.Set;

/**
 * @author Pablo Fernández Montes
 * @author José Antonio Parejo Maestre
 * 
 */
public interface TradingProtocolChoreography {
	public Set<TemporalRestriction> getTemporalRestrictions();
}
