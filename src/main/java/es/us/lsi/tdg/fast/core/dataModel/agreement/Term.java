/**
 * 
 */
package es.us.lsi.tdg.fast.core.dataModel.agreement;

import java.util.Set;

import es.us.lsi.tdg.fast.core.dataModel.statement.Constraint;

/**
 * 
 * This interface represents a Term, that define the content of 
 * an agreement. The terms can be applied to both the service 
 * consumer and the service provider. A term mainly specify some 
 * functional or non-functional guarantee of the service that must 
 * be observed by the parties during its execution. For instance, 
 * a term can express that the service provider guarantees that 
 * the response time will be less than 20 ms, and another term can 
 * express that the service consumer will pay 1 euro for each 
 * service execution and that will not execute the service more than 
 * 10 times perminute.
 * 
 * @author Pablo Fernández Montes
 * @author José Antonio Parejo Maestre
 *
 */
public interface Term {
	/**
	 * 
	 * The counterparty whom the term is applied to. Each Term is to 
	 * be applied to one of the parties involved in the Agreement and 
	 * the CounterParty is obligated to fullfil what it is specified 
	 * in it. Obviously, the CounterParty must be one of those that 
	 * have been designated in the Agreement as one of the parties
	 * that are involved in it.
	 *  
	 */
	public CounterParty getCounterParty();
	/**
	 * The set of constraints of the term specifies functional or 
	 * non-functional descriptions or guarantees of the service. 
	 * It is expected that the content of these constraints will 
	 * be very broad and domain-specific. Some examples of functional 
	 * aspects of the service that can be expressed by using these 
	 * constraints are the service interface by referencing a WSDL 
	 * document, for instance, or the endpoint where the service is 
	 * located. Regarding the non-functional guarantees, some 
	 * examples are the response time will be less than 5 ms.
	 * 
	 */
	public Set<Constraint> getConstraints();

}
