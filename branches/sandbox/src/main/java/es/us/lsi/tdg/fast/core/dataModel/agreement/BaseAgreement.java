/**
 * 
 */
package es.us.lsi.tdg.fast.core.dataModel.agreement;

import java.util.Set;

import es.us.lsi.tdg.fast.core.dataModel.statement.Constraint;

/**
 * @author José Antonio
 *
 */
public class BaseAgreement implements Agreement {

	Set<Term> terms;
	Set<CounterParty> counterParties;
	
	
	
	public BaseAgreement(Set<Term> terms, Set<CounterParty> counterParties) {
		super();
		this.terms = terms;
		this.counterParties = counterParties;
	}

	/* (non-Javadoc)
	 * @see es.us.lsi.tdg.fast.core.dataModel.agreement.Agreement#getCounterParties()
	 */
	public Set<CounterParty> getCounterParties() {
		
		return counterParties;
	}

	/* (non-Javadoc)
	 * @see es.us.lsi.tdg.fast.core.dataModel.agreement.Agreement#getTerms()
	 */
	public Set<Term> getTerms() {
		
		return terms;
	}

	public void addTerm(Constraint constraint, CounterParty counterParty) {
		
		if(!counterParties.contains(counterParty))
			throw new IllegalArgumentException();
		
		Term term = new BaseTerm(constraint,counterParty);
		
		terms.add(term);
	}

	public void removeTerm(Term term) {
		// TODO Auto-generated method stub
		
	}

}
