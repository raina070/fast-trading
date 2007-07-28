package es.us.lsi.tdg.fast.core.dataModel.agreement;

import java.util.Set;

import es.us.lsi.tdg.fast.core.dataModel.statement.Constraint;

public class BaseTerm implements Term {

	Set<Constraint> constraints;
	CounterParty counterParty;
	
	public BaseTerm(Set<Constraint> constraints, CounterParty counterParty) {
		super();
		this.constraints = constraints;
		this.counterParty = counterParty;
	}

	public BaseTerm(Constraint constraint, CounterParty counterParty) {
		super();
		this.constraints.add(constraint);
	}

	public Set<Constraint> getConstraints() {
		// TODO Auto-generated method stub
		return null;
	}

	public CounterParty getCounterParty() {
		// TODO Auto-generated method stub
		return null;
	}

}
