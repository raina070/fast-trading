/**
 * 
 */
package es.us.lsi.tdg.fast.core.shell;
import java.util.Set;

import es.us.lsi.tdg.fast.FAST;
import es.us.lsi.tdg.fast.core.dataModel.statement.AttributeCatalog;
/**
 * @author Pablo Fernández Montes
 * @author José Antonio Parejo Maestre
 *
 */
public class LoadDomainCommand extends BaseCommand {

	String domain;
	public LoadDomainCommand() {
		super("domain","Load a specific domain attribute catalog. You moust load a domain previously to the specification of command preferences.");		
	}
	
	public void configure(String[] arguments){
		if(arguments.length>1)
			domain=arguments[1];
		else
			domain=null;
	}

	public void execute(ShellRender shellRenderer){
		if(domain!=null)
		{
			FAST.domainRegistry.loadDomain(domain);
			if(FAST.domainRegistry.searchDomain(domain))
			{
				FAST.currentDomain = FAST.domainRegistry.getManifest(domain);
				AttributeCatalog acatalog = FAST.currentDomain.getAttributeCatalog();
				
				shellRenderer.setPrompt(domain+"-"+shellRenderer.getPrompt());
				shellRenderer.println("OK: "+domain+" domain loaded.");
				
			}else
				shellRenderer.println("ERROR: "+domain+" is not a valid domain name.");
		}else
			shellRenderer.println("ERROR: You must specify a domain.");
	}

}
