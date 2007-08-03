/**
 * 
 */
package es.us.lsi.tdg.fast.domains.fom;

import java.util.HashSet;

import es.us.lsi.tdg.fast.core.dataModel.statement.Attribute;
import es.us.lsi.tdg.fast.core.dataModel.statement.BaseAttribute;
import es.us.lsi.tdg.fast.core.dataModel.statement.DateDomain;
import es.us.lsi.tdg.fast.core.dataModel.statement.Domain;
import es.us.lsi.tdg.fast.core.dataModel.statement.IntegerDomain;
import es.us.lsi.tdg.fast.core.dataModel.statement.BaseAttributeCatalog;

/**
 * @author Pablo Fernandez Montes
 * @author José Antonio Parejo Maestre
 */
public class FOMAttributeCatalog extends BaseAttributeCatalog {
	public FOMAttributeCatalog()
	{
		super(new HashSet<Attribute>(),"FOMBasicAttributeCatalog","FOM");
		Domain domain=IntegerDomain.getInstance();
		Attribute attribute=new BaseAttribute("Time",domain);
		getAttributes().add(attribute);
		attribute=new BaseAttribute("Cost",domain);
		getAttributes().add(attribute);
		domain=DateDomain.getInstance();
		attribute=new BaseAttribute("InvocationMinDate",domain);
		getAttributes().add(attribute);		
	}
	
}
