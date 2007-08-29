package es.us.lsi.tdg.fast.core.shell.command;

import java.util.HashSet;
import java.util.Set;

import es.us.lsi.tdg.fast.FAST;
import es.us.lsi.tdg.fast.core.shell.ShellRender;
import es.us.lsi.tdg.fast.core.shell.SystemPropertiesCommand;
import es.us.lsi.tdg.fast.core.shell.UnknownCommandException;

public class BaseDomainPropertiesCommand extends BaseModeCommand implements SystemPropertiesCommand {

	Set<String> propertyKeySet;
	String usageHelp="Usage: properties";
	
	public BaseDomainPropertiesCommand() {
		super("properties","Definition of system properties for a specific domain. Type \"propertiesmodehelp\" in properties mode for domain specific information");
		
		Command showCommand=new BaseCommand("show","Shows all actual client specified preferences"){
			public void execute(ShellRender shellRenderer){
				showProperties(shellRenderer);
			}
		};
		showCommand.setCommandFactory(commandFactory);				
		Command addPropertyCommand=new BaseCommand("addProperty","Set a new or existing Property"){
			public void execute(ShellRender shellRenderer){
				addProperty();
			}
		};
		addPropertyCommand.setCommandFactory(commandFactory);
		Command delPropertyCommand=new BaseCommand("delProperty","Set a new or existing Property"){
			public void execute(ShellRender shellRenderer){
				delProperty();
			}
		};
		delPropertyCommand.setCommandFactory(commandFactory);
		addSubCommand(showCommand.getName(),showCommand);
		addSubCommand(addPropertyCommand.getName(),addPropertyCommand);
		addSubCommand(delPropertyCommand.getName(),delPropertyCommand);
		
		
		propertyKeySet = new HashSet<String>();
	}
	
	public void addProperty(){
		if (arguments.length>1){
			String property = arguments[1];
			FAST.properties.put(property, "");
			Command propertyCommand=new PropertyCommand(property,"Set the " + property + " new value");
			propertyCommand.setCommandFactory(commandFactory);
			addSubCommand(property,propertyCommand);
			loadSubCommand(propertyCommand);
		}
	}
	
	public void delProperty(){
		if (arguments.length>1){
			try {
				FAST.properties.remove(arguments[1]);
				removeSubCommand(arguments[1]);
				unloadSubCommand(arguments[1]);
			} catch (UnknownCommandException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	
	
	
	
	public void showProperties(ShellRender shellRenderer){
		shellRenderer.println("Actual system properties");
		for (String property:FAST.properties.keySet()){
			shellRenderer.println(property+": "+FAST.properties.get(property));
		}
	}
	
		
	protected void initMode(ShellRender shellRenderer) {
		super.initMode(shellRenderer);		
	}
	
	public void execute(ShellRender shellRenderer){	
		if(arguments[0].equals(getName()))
		{
			if(arguments.length>1){
				if(arguments[1].equals("help"))
				{
					showHelp(shellRenderer);
				}else
					shellRenderer.println("ERROR: This mode has not arguments.");
			}else
			{
					registersSpecificCommands();
					super.execute(shellRenderer);							
				
			}
		}else
			super.execute(shellRenderer);
	}
	
	public void registersSpecificCommands(){
		Set<String>allPropertySet = new HashSet<String>();
		allPropertySet = FAST.properties.keySet();
		for(String property:allPropertySet)
		{
			 Command propertyCommand=new PropertyCommand(property,"Set the " + property + " new value");
			 propertyCommand.setCommandFactory(commandFactory);
			 addSubCommand(property,propertyCommand);
		}
	}
	
/*
	public void execute(ShellRender shellRenderer){
		if(propertyKey!=null)
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
*/
}
