/**
 * 
 */
package es.us.lsi.tdg.fast.domains.fom;

import java.util.HashSet;

import es.us.lsi.tdg.fast.core.dataModel.statement.Attribute;
import es.us.lsi.tdg.fast.core.dataModel.statement.BaseAttribute;
import es.us.lsi.tdg.fast.core.dataModel.statement.BaseAttributeCatalog;
import es.us.lsi.tdg.fast.core.dataModel.statement.Domain;
import es.us.lsi.tdg.fast.core.dataModel.statement.IntegerDomain;

/**
 * @author Pablo Fernandez Montes
 * @author José Antonio Parejo Maestre
 */
public class FOMAttributeCatalog extends BaseAttributeCatalog {
	public FOMAttributeCatalog()
	{
		super(new HashSet<Attribute>(),"FOMBasicAttributeCatalog","FOM");
		Domain domain=IntegerDomain.getInstance();
		Attribute attribute=new BaseAttribute("Time",domain,"Computation time to solve the optimization problem, in seconds.");
		getAttributes().add(attribute);
		attribute=new BaseAttribute("Cost",domain,"Total cost of the optimization time in €.");
		getAttributes().add(attribute);
		attribute=new BaseAttribute("InvocationMinDate",domain,"Stabilish a minimum time when the service can be invoked, expressed as time and date.");		
		getAttributes().add(attribute);		
		attribute=new BaseAttribute("CostFactor",domain,"Factor cost in provider offer.");		
		getAttributes().add(attribute);		
	}
	
}
