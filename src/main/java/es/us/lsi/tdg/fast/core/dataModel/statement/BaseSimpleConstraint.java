package es.us.lsi.tdg.fast.core.dataModel.statement;

public class BaseSimpleConstraint implements SimpleConstraint {

	private Value value;
	private Attribute attribute;
	private StatementType type;
	public BaseSimpleConstraint(Value value,Attribute attribute, StatementType type) throws IncompatibleAttributeException {
		super();		
		this.attribute = attribute;
		if(attribute.getDomain() == value.getDomain())
			this.value = value;
		else
			throw new IncompatibleAttributeException();
		this.type=type;
	}

	public Value getValue() {
		return value;
	}

	public boolean evaluate(Value val) {
		return (val==value);
	}

	public Attribute getAttribute() {
		return attribute;
	}

	public StatementType getType() {
		return  type;
	}
	
	public String toString()
	{
		return attribute.getName()+" = "+value.toString();
	}

}
