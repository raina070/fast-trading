/**
 * 
 */
package es.us.lsi.tdg.fast.core.dataModel.statement;

/**
 * @author Pablo Fernandez Montes
 * @author Jos� Antonio Parejo Maestre
 *
 */
public interface SortedDomainConstraint extends DomainConstraint {
	public ComparableValue getMin();
	public ComparableValue getMax();
}
