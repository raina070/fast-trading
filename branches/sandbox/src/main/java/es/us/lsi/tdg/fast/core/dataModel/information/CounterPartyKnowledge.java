/**
 * 
 */
package es.us.lsi.tdg.fast.core.dataModel.information;

import es.us.lsi.tdg.fast.core.dataModel.agreement.CounterParty;

/**
 * 
 * 
 * 
 * @author Pablo Fern�ndez Montes
 * @author Jos� Antonio Parejo Maestre
 *
 */
public interface CounterPartyKnowledge {
	public CounterParty getCounterParty();
	public Information getCounterPartyInformation();
	public Information getTradingInformation();
	public Information getServiceInformation();

}
