package es.us.lsi.tdg.fast;

import es.us.lsi.tdg.fast.core.dataModel.statement.AttributeCatalog;
import es.us.lsi.tdg.fast.core.domainRegistry.BaseDomainRegistry;
import es.us.lsi.tdg.fast.core.domainRegistry.DomainRegistry;
import es.us.lsi.tdg.fast.core.shell.FASTShell;
import es.us.lsi.tdg.fast.core.shell.SimpleFASTShell;

/**
 * Hello world!
 *
 */
public class FAST 
{
	public static DomainRegistry domainRegistry=null;
	public static AttributeCatalog currentDomain=null;
    public static void main( String[] args )
    {
        domainRegistry=new BaseDomainRegistry();        
    	FASTShell shell = new SimpleFASTShell();
        shell.run();
        
    }
}
