/**
 * 
 */
package es.us.lsi.tdg.fast.core.dataModel.statement;

/**
 * @author Pablo Fern�ndez Montes
 * @author Jos� Antonio Parejo Maestre
 */
public interface StatementFactory {
	public Statement create();
	public Statement create(String definition);
	public Statement create(String [] definition);
	
}
