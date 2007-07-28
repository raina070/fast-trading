/**
 * 
 */
package es.us.lsi.tdg.fast.core.dataModel.statement;

/**
 * 
 * This structure groups different types of expressions over 
 * a certain party or the trading process itself:
 * 
 * <ul>
 * 	<li>
 * 		On the one hand, statements are the basic building block 
 * 		used to construct the set of features and requirements 
 * 		of the party; in this way, statements would be rules or 
 * 		constraints.
 * 	</li>
 * 	<li>
 * 		On the other hand, statements can describe the 
 * 		characteristics of a particular trading process creating 
 * 		what is called as trading protocol.
 * 	</li>
 * </ul>
 * 
 * @author Pablo Fernández Montes
 * @author José Antonio Parejo Maestre
 *
 */
public interface Statement {
	public StatementType getType();
}
