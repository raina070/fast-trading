/**
 * 
 */
package es.us.lsi.tdg.fast.core.dataModel.trading;

import java.util.List;
import java.util.Set;

/** 
 * 
 * This structure express some parts (or the whole) of the
 * trading process. To understand what a trading protocol 
 * is, we can focus on the following real-life example: A 
 * public bidding where an institution looks for a service 
 * provider and devises a trading protocol that consists of 
 * the following stages: the announce of the bidding, a 
 * deadline for the submission of proposals, a period of 
 * resolution and, finally, the communication of results. The 
 * trading protocol also states temporal constraints for 
 * each stages.
 * In an abstract level, the trading protocol is defined as a 
 * set of stages (e.g. advertisement, proposal submission, 
 * negotiation, resolution, etc...) cross-linked in accordance 
 * to some temporal constraints and bounded to some choreographies.
 * 
 * @author Pablo Fernández Montes
 * @author José Antonio Parejo Maestre
 */
public interface TradingProtocol {

	public List<TradingProtocolStage> getStages();
	public Set<TemporalRestriction> getTemporalRestrictions();
	
}
