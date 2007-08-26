package es.us.lsi.tdg.fast;

import java.util.logging.ConsoleHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.Map;
import java.util.HashMap;

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
import es.us.lsi.tdg.fast.domains.fom.dataModel.FOMProposal;
import es.us.lsi.tdg.fast.domains.fom.dataModel.FOMProposalTranslator;

/**
 * Hello world!
 *
 */
public class FAST 
{
	
	public static String version = "0.2";
	public static String releaseName = "Bilbo";
	
	public static Logger log=Logger.getLogger("FAST");
	
	public static FASTServer server=null;
	public final static int DEFAULT_PORT=1607;
	public final static String DEFAULT_IP="localhost";
	public static Map<String,String>properties = new HashMap<String,String>();
	
	public static DomainRegistry domainRegistry=null;
	public static DomainManifest currentDomain=null;
	public static DomainRole currentDomainRole=null;
	public static ComponentFactory componentFactory=null;
	public static PreferenceRegistry preferenceRegistry=null;
	public static AgreementRegistry agreementRegistry=null;
	public static FASTShell shell = null;
	
    public static void main( String[] args )
    {
    	properties.put("serverPort", Integer.toString(DEFAULT_PORT));
    	properties.put("serverIP", DEFAULT_IP);
    	if(args.length>0){
    		try{
    			int portCandidate = Integer.parseInt(args[0]);
    			FAST.properties.put("serverPort", Integer.toString(portCandidate));
    			
    		}catch(NumberFormatException e){
    		}
    	}
    	FAST.properties.put("testslatime", Integer.toString(10));
    	
    	/*
    	 * This should be moved to the domain load
    	 */
    	properties.put("discoveryEndPoint", "http://localhost:1607/customer/DiscoveryServiceImplementation?wsdl");	
    	properties.put("fullTime", "60");
    	
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
