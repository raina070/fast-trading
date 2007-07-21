/**
 * 
 */
package es.us.lsi.tdg.fast.core.dataModel.statement;

import es.us.lsi.tdg.fast.core.dataModel.CounterParty;

/**
 * In this case, an expression about the party is stated.
 * This statements can express either features or 
 * requirement over a given party. 
 * Examples of this can be: Party Z is located in Iran 
 * or Party X has a low reputation on service Y.
 * @author Pablo Fernández Montes
 * @author José Antonio Parejo Maestre
 *
 */
public interface PartyStatement extends Statement {
	public CounterParty getCounterParty();
}
