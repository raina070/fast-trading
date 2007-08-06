/**
 * 
 */
package es.us.lsi.tdg.fast.core.dataModel.statement;

/**
 * @author Pablo Fernández Montes
 * @author José Antonio Parejo Maestre
 */
public interface StatementFactory {
	public Statement create();
	public Statement create(String definition);
	public Statement create(String [] definition);
	
}
