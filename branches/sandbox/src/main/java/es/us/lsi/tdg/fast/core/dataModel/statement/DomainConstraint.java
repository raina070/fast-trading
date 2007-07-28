/**
 * 
 */
package es.us.lsi.tdg.fast.core.dataModel.statement;

/**
 * @author Pablo Fernandez Montes
 * @author José Antonio Parejo Maestre
 *
 */
public interface DomainConstraint extends Constraint {
	public DomainConstraint intersect(Constraint constraint);
	public boolean conform(Constraint constraint) throws IncompatibleAttributeException;
	public boolean isEmpty();
}
