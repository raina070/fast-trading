/**
 * 
 */
package es.us.lsi.tdg.fast.core.dataModel;
import java.util.Set;

/**
 * 
 * 
 * 
 * @author Pablo Fern�ndez Montes
 * @author Jos� Antonio Parejo Maestre
 *
 */
public interface CounterPartyKnowledge {
	
	public Set<CounterPartyInformation> getCounterPartyInformation();
	public Set<TradingInformation> getTradingInformation();
	public Set<ServiceInformation> getServiceInformation();

}
