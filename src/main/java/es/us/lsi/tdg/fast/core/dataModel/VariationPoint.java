/**
 * 
 */
package es.us.lsi.tdg.fast.core.dataModel;

/**
 * A VariationPoint is an expression to indicate ranges of 
 * acceptable variations of the Terms specified in a proposal. 
 * These VariationPoints can be used to relax Constraints specified 
 * in those terms or to express possible trade-offs amongst terms. 
 * For instance, I accept a response time higher than 20 ms but 
 * only if the price is lower than 50 cents per execution. A 
 * VariationPoint provides guidelines to facilitate the process of 
 * finding an agreement through the exchange of proposals. Each 
 * variation point is applied to a collection of terms specified in 
 * the proposal. They can be used, for instance, to relax constraints
 * specified in those terms, to express possible trade-offs amongst 
 * terms (e.g. I accept a response time higher than 20 ms but only if 
 * the price is lower than 50 cents per execution), to indicate whether 
 * a term is negotiable, or to provide the other party with partial 
 * information about our utility function.
 * 
 * @author Pablo Fernández Montes
 * @author José Antonio Parejo Maestre
 */
public interface VariationPoint {
	/***
	 * 
	 * 
	 */
	public Term getTerm();
}
