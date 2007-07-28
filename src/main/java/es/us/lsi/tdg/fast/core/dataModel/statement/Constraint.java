package es.us.lsi.tdg.fast.core.dataModel.statement;

/**
 * 
 *   
 * @author Pablo Fernández Montes
 * @author José Antonio Parejo Maestre
 *
 */

public interface Constraint extends Statement {
	public boolean evaluate(Value val);
	public Attribute getAttribute();
}
