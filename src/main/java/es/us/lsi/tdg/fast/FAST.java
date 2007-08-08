package es.us.lsi.tdg.fast;

import java.util.logging.ConsoleHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

import es.us.lsi.tdg.fast.components.GenericComponentsLoader;
import es.us.lsi.tdg.fast.core.component.BaseComponentFactory;
import es.us.lsi.tdg.fast.core.component.ComponentFactory;
import es.us.lsi.tdg.fast.core.domainRegistry.BaseDomainRegistry;
import es.us.lsi.tdg.fast.core.domainRegistry.DomainManifest;
import es.us.lsi.tdg.fast.core.domainRegistry.DomainRegistry;
import es.us.lsi.tdg.fast.core.domainRegistry.DomainRole;
import es.us.lsi.tdg.fast.core.preferenceRegistry.BasePreferenceRegistry;
import es.us.lsi.tdg.fast.core.preferenceRegistry.PreferenceRegistry;
import es.us.lsi.tdg.fast.core.shell.FASTShell;
import es.us.lsi.tdg.fast.core.shell.SimpleFASTShell;

/**
 * Hello world!
 *
 */
public class FAST 
{
	public static String version = "0.1";
	public static String releaseName = "Bilbo";
	
	public static Logger log=Logger.getLogger("FAST");
	
	public static DomainRegistry domainRegistry=null;
	public static DomainManifest currentDomain=null;
	public static DomainRole currentDomainRole=null;
	public static ComponentFactory componentFactory=null;
	public static PreferenceRegistry preferenceRegistry=null;
	public static FASTShell shell = null;
	
    public static void main( String[] args )
    {
		log.addHandler(new ConsoleHandler());
    	log.setLevel(Level.OFF);
    	
    	shell = new SimpleFASTShell();

    	domainRegistry=new BaseDomainRegistry();

    	componentFactory= BaseComponentFactory.getInstance();
        GenericComponentsLoader.loadComponents(componentFactory);
        
        preferenceRegistry=new BasePreferenceRegistry();
        shell.run();
        
    }
}
