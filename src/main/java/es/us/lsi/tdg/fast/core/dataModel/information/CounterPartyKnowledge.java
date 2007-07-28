/**
 * 
 */
package es.us.lsi.tdg.fast.core.dataModel.information;
import java.util.Set;

/**
 * 
 * 
 * 
 * @author Pablo Fernández Montes
 * @author José Antonio Parejo Maestre
 *
 */
public interface CounterPartyKnowledge {
	
	public Information getCounterPartyInformation();
	public Information getTradingInformation();
	public Information getServiceInformation();

}
