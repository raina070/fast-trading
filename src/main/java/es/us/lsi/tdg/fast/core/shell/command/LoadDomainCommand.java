/**
 * 
 */
package es.us.lsi.tdg.fast.core.shell.command;
import java.util.Set;

import es.us.lsi.tdg.fast.FAST;
import es.us.lsi.tdg.fast.core.component.Component;
import es.us.lsi.tdg.fast.core.dataModel.statement.AttributeCatalog;
import es.us.lsi.tdg.fast.core.domainRegistry.DomainManifest;
import es.us.lsi.tdg.fast.core.domainRegistry.DomainRole;
import es.us.lsi.tdg.fast.core.shell.ShellRender;
import es.us.lsi.tdg.fast.core.shell.UnknownCommandException;
/**
 * @author Pablo Fernández Montes
 * @author José Antonio Parejo Maestre
 *
 */
public class LoadDomainCommand extends BaseCommand {

	String domain,domainRole;
	String usageHelp="Usage: domain domainName domainRole";
	
	public LoadDomainCommand() {
		super("domain","Load the given domain with an specific role. You moust load a domain previously to the specification of trading preferences.");		
	}
	
	public void configure(String[] arguments){
		
		if(arguments.length==3){
			domain=arguments[1];
			domainRole=arguments[2];
		}else{
			domain=null;
		}
	}

	public void execute(ShellRender shellRenderer){
		if(domain!=null)
		{
			FAST.domainRegistry.loadDomain(domain);
			if(FAST.domainRegistry.searchDomain(domain))
			{
				DomainManifest domainManifest = FAST.domainRegistry.getManifest(domain);
				Set<DomainRole> domainRoles = domainManifest.getDomainRoles();
				DomainRole selectedRole=null;
				for(DomainRole searchDomainRole:domainRoles)
				{
					if(searchDomainRole.getName().equals(domainRole))
						selectedRole=searchDomainRole;
				}
				if(selectedRole!=null){
					FAST.currentDomain = domainManifest;
					FAST.currentDomainRole = selectedRole;
					/**
					 * <PROPERTIES>
					 */
					Command propertiesCommand=new BaseDomainPropertiesCommand();
					propertiesCommand.setCommandFactory(commandFactory);
					commandFactory.loadCommand(propertiesCommand.getName(), propertiesCommand);
					/**
					 * </PROPERTIES>
					 */
					
					AttributeCatalog acatalog = FAST.currentDomain.getAttributeCatalog();
					Command preferencesCommand=new BaseDomainPreferencesCommand(acatalog);
					preferencesCommand.setCommandFactory(commandFactory);
					commandFactory.loadCommand(preferencesCommand.getName(), preferencesCommand);
				
					// Load specific components
					Set<Component> domainComponents = FAST.currentDomain.getComponents();
					for(Component component : domainComponents){
						FAST.componentFactory.loadComponent(component);
					}
					
					try {
						commandFactory.addCommand(preferencesCommand.getName());
					} catch (UnknownCommandException e) {}
					try {
						commandFactory.addCommand(propertiesCommand.getName());
					} catch (UnknownCommandException e) {}
					shellRenderer.setPrompt(domainRole+"@"+domain+"-"+shellRenderer.getPrompt());
					
					shellRenderer.println("OK: "+domain+" domain loaded.");
				}else{
					shellRenderer.println("ERROR: "+domainRole+" is not a valid role for domain "+domain+".");
					shellRenderer.println("Valid roles are:"+domainRoles);
				}
			}else{
				shellRenderer.println("ERROR: "+domain+" is not a valid domain name.");
			}
		}else
			shellRenderer.println("ERROR: Wrong number of parameters.\n"+usageHelp);
	}

}
