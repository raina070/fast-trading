/**
 * 
 */
package es.us.lsi.tdg.fast.core.shell;
import es.us.lsi.tdg.fast.FAST;
import es.us.lsi.tdg.fast.core.dataModel.statement.AttributeCatalog;
/**
 * @author Pablo Fern�ndez Montes
 * @author Jos� Antonio Parejo Maestre
 *
 */
public class LoadDomainCommand extends BaseCommand {

	String domain;
	public LoadDomainCommand() {
		super("domain");		
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
				// TODO generate specific domain commands based on the AttributeCatalog of this domain:
			}else
				shellRenderer.println("ERROR: "+domain+" is not a valid domain name.");
		}else
			shellRenderer.println("ERROR: You must specify a domain.");
	}

}
