package es.us.lsi.tdg.fast;

import es.us.lsi.tdg.fast.components.BaseComponentFactory;
import es.us.lsi.tdg.fast.components.ComponentFactory;
import es.us.lsi.tdg.fast.core.domainRegistry.BaseDomainRegistry;
import es.us.lsi.tdg.fast.core.domainRegistry.DomainManifest;
import es.us.lsi.tdg.fast.core.domainRegistry.DomainRegistry;
import es.us.lsi.tdg.fast.core.shell.FASTShell;
import es.us.lsi.tdg.fast.core.shell.SimpleFASTShell;

/**
 * Hello world!
 *
 */
public class FAST 
{
	public static String version = "0.1";
	public static String releaseName = "Mapashito";
	
	public static DomainRegistry domainRegistry=null;
	public static DomainManifest currentDomain=null;
	public static ComponentFactory componentFactory=null;
	public static FASTShell shell = null;
	
    public static void main( String[] args )
    {
    	shell = new SimpleFASTShell();
        domainRegistry=new BaseDomainRegistry();
        componentFactory=new BaseComponentFactory();
        
        shell.run();
        
    }
}
