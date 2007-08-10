package es.us.lsi.tdg.fast;

import java.util.logging.ConsoleHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

import es.us.lsi.tdg.fast.components.GenericComponentsLoader;
import es.us.lsi.tdg.fast.core.agreementRegistry.AgreementRegistry;
import es.us.lsi.tdg.fast.core.agreementRegistry.BaseAgreementRegistry;
import es.us.lsi.tdg.fast.core.component.BaseComponentFactory;
import es.us.lsi.tdg.fast.core.component.ComponentFactory;
import es.us.lsi.tdg.fast.core.domainRegistry.BaseDomainRegistry;
import es.us.lsi.tdg.fast.core.domainRegistry.DomainManifest;
import es.us.lsi.tdg.fast.core.domainRegistry.DomainRegistry;
import es.us.lsi.tdg.fast.core.domainRegistry.DomainRole;
import es.us.lsi.tdg.fast.core.preferenceRegistry.BasePreferenceRegistry;
import es.us.lsi.tdg.fast.core.preferenceRegistry.PreferenceRegistry;
import es.us.lsi.tdg.fast.core.services.BaseFASTServer;
import es.us.lsi.tdg.fast.core.services.FASTServer;
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
	
	public static FASTServer server=null;
	public static int serverPort=1607;
	
	public static DomainRegistry domainRegistry=null;
	public static DomainManifest currentDomain=null;
	public static DomainRole currentDomainRole=null;
	public static ComponentFactory componentFactory=null;
	public static PreferenceRegistry preferenceRegistry=null;
	public static AgreementRegistry agreementRegistry=null;
	public static FASTShell shell = null;
	
    public static void main( String[] args )
    {
    	if(args.length>1){
    		try{
    			int portCandidate = Integer.parseInt(args[1]);
    			serverPort = portCandidate;
    		}catch(NumberFormatException e){
    		}
    	}
    		
		log.addHandler(new ConsoleHandler());
    	log.setLevel(Level.OFF);
    	
    	shell = new SimpleFASTShell();

    	server = BaseFASTServer.getInstance();
    	
    	domainRegistry=new BaseDomainRegistry();

    	componentFactory= BaseComponentFactory.getInstance();
        GenericComponentsLoader.loadComponents(componentFactory);
        
        preferenceRegistry=new BasePreferenceRegistry();
        agreementRegistry=new BaseAgreementRegistry();
        shell.run();

        if(server != null){
        	server.stop();
        	System.out.println(".");
        }
    }
}
