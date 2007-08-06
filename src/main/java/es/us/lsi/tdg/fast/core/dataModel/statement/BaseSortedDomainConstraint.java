/**
 * 
 */
package es.us.lsi.tdg.fast.core.dataModel.statement;

/**
 * @author Pablo Fernandez Montes
 * @author José Antonio Parejo Maestre
 *
 */
public class BaseSortedDomainConstraint implements SortedDomainConstraint {

	private ComparableValue min;
	private ComparableValue max;
	private Attribute attribute;
	private StatementType type;
		
	public BaseSortedDomainConstraint(ComparableValue min, ComparableValue max, Attribute attribute, StatementType type) throws IncompatibleAttributeException
	{
		this.attribute = attribute;
		if(attribute.getDomain() == min.getDomain())
			this.min=min;
		else
			throw new IncompatibleAttributeException();
		
		if(attribute.getDomain() == max.getDomain())
			this.max=max;
		else
			throw new IncompatibleAttributeException();
		this.type=type;
	}
	
	/* (non-Javadoc)
	 * @see es.us.lsi.tdg.fast.core.dataModel.statement.SortedDomainConstraint#getMax()
	 */
	public ComparableValue getMax() {
		// TODO Auto-generated method stub
		return max;
	}

	/* (non-Javadoc)
	 * @see es.us.lsi.tdg.fast.core.dataModel.statement.SortedDomainConstraint#getMin()
	 */
	public ComparableValue getMin() {
		// TODO Auto-generated method stub
		return min;
	}

	/* (non-Javadoc)
	 * @see es.us.lsi.tdg.fast.core.dataModel.statement.DomainConstraint#conform(es.us.lsi.tdg.fast.core.dataModel.statement.Constraint)
	 */
	public boolean conform(Constraint constraint) throws IncompatibleAttributeException {
		boolean result=false;
		if(getAttribute()==constraint.getAttribute())
		{
				DomainConstraint intersection=intersect(constraint);
				result=!(intersection.isEmpty());
		}else
			throw new IncompatibleAttributeException();
		return false;
	}

	/* (non-Javadoc)
	 * @see es.us.lsi.tdg.fast.core.dataModel.statement.DomainConstraint#intersect(es.us.lsi.tdg.fast.core.dataModel.statement.Constraint)
	 */
	public DomainConstraint intersect(Constraint constraint) {
		// TODO Auto-generated method stub
		DomainConstraint result=null;
		// Represents the maximum value of the minimums
		ComparableValue minMax=null;
		// Represents the minimum value of the maximums
		ComparableValue maxMin=null;
		if(constraint instanceof SortedDomainConstraint)
		{
			SortedDomainConstraint sdconstraint=(SortedDomainConstraint)constraint;
			if(min.compareTo(sdconstraint.getMin())>0)
					minMax=min;
			else
				minMax=sdconstraint.getMin();
			if(max.compareTo(sdconstraint.getMax())<0)
				maxMin=max;
			else
				maxMin=sdconstraint.getMax();
		}else if(constraint instanceof SimpleConstraint)
		{
			SimpleConstraint simpleconstraint=(SimpleConstraint)constraint;
			if(min.compareTo(simpleconstraint.getValue())>0)
					minMax=min;
			else
				minMax=(ComparableValue) simpleconstraint.getValue();
			if(max.compareTo(simpleconstraint.getValue())<0)
				maxMin=max;
			else
				maxMin=(ComparableValue) simpleconstraint.getValue();
		}
		if(minMax.compareTo(maxMin)>0){
			minMax=null;
			maxMin=null;
		}
		try{
			result= new BaseSortedDomainConstraint(minMax,maxMin,attribute,type);
		}catch(IncompatibleAttributeException e){
			
		}
		return result;
	}

	/* (non-Javadoc)
	 * @see es.us.lsi.tdg.fast.core.dataModel.statement.Constraint#evaluate(es.us.lsi.tdg.fast.core.dataModel.statement.Value)
	 */
	public boolean evaluate(Value val) {
		
		ComparableValue compValue = (ComparableValue) val;
		
		return ((min.compareTo(compValue)<=0) && (max.compareTo(compValue)>=0));
	}

	/* (non-Javadoc)
	 * @see es.us.lsi.tdg.fast.core.dataModel.statement.Constraint#getAttribute()
	 */
	public Attribute getAttribute() {
		return attribute;
	}

	public boolean isEmpty() {
		return min==null && max==null;
	}

	public StatementType getType() {
		// TODO Auto-generated method stub
		return type;
	}
	
	public String toString()
	{
		return attribute.getName()+" in "+"["+min.toString()+","+max.toString()+"]";
	}

}
