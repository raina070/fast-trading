/**
 * 
 */
package es.us.lsi.tdg.fast.core.dataModel.agreement;

import java.util.Set;

import es.us.lsi.tdg.fast.core.dataModel.statement.Constraint;

/**
 * This interface represents an Agreement, that defines a 
 * dynamically-established and dynamically managed relationship 
 * between parties. 
 * The goal of the agreement is to establish the guarantees that 
 * must be observed during the execution of a service.
 * 
 * 
 * @author Jos� Antonio Parejo Maestre
 * @author Pablo Fern�ndez Montes
 *
 */
public interface Agreement {
	public Set<CounterParty> getCounterParties();
	public Set<Term> getTerms();
	public Term addTerm(Constraint constraint,CounterParty counterParty);
	public void removeTerm(Term term);
}
