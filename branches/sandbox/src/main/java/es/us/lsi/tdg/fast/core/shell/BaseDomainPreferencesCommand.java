/**
 * 
 */
package es.us.lsi.tdg.fast.core.shell;

import java.util.HashSet;
import java.util.Set;

import es.us.lsi.tdg.fast.FAST;
import es.us.lsi.tdg.fast.core.dataModel.statement.Attribute;
import es.us.lsi.tdg.fast.core.dataModel.statement.AttributeCatalog;

/**
 * @author Pablo Fernández Montes
 * @author José Antonio Parejo Maestre
 *
 */
public class BaseDomainPreferencesCommand extends BaseCommand implements
		DomainPreferencesCommand {
		
	private String IDC;
	private String commandName;
	private String attributeName;
	private String [] preferenceDefinition;
	
	private Set<String> activeCommansdBackup;
	private Set<String> preservedCommands;
	
	public BaseDomainPreferencesCommand(AttributeCatalog attributeCatalog) {
		super("preferences","Definition of trading preferences for a pecific domain. Type \"preferences help\" for more information.");
		activeCommansdBackup=new HashSet<String>();
		preservedCommands=new HashSet<String>();
		preservedCommands.add("exit");
		preservedCommands.add("help");
	}

	/* (non-Javadoc)
	 * @see es.us.lsi.tdg.fast.core.shell.DomainPreferencesCommand#getAttribute()
	 */
	public AttributeCatalog getAttributeCatalog() {
		return FAST.currentDomain.getAttributeCatalog();
	}

	public void configure(String[] arguments)
	{
		commandName=arguments[0];
		attributeName="";
		preferenceDefinition=null;
		if(arguments.length==1)
		{
			if(commandName=="endpreferences")
			{				
				closePreferencesMode();
			}
		}else if(arguments.length==2)
		{
			if(commandName.equals("preferences"))
			{
				IDC=arguments[1];
				initPreferencesMode();
			}else
			{
				attributeName=arguments[1];
				preferenceDefinition=new String[arguments.length-1];
				for(int i=1;i<arguments.length;i++)
					preferenceDefinition[i-1]=arguments[i];
			}
		}else{
			if(commandName.equals("preferences"))
			{
				IDC=arguments[1];
				attributeName=arguments[2];
				preferenceDefinition=new String[arguments.length-2];
				for(int i=2;i<arguments.length;i++)
					preferenceDefinition[i-2]=arguments[i];
			}
		}
			
	}	

	private void initPreferencesMode() {
		activeCommansdBackup.addAll(commandFactory.getActiveCommands());
		for(String command:activeCommansdBackup){
			if(!preservedCommands.contains(command))
				try {
					commandFactory.removeCommand(command);
				} catch (UnknownCommandException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (InvalidCommandException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}			
		}
	}
	
	private void closePreferencesMode()
	{
		for(String command:activeCommansdBackup){
			if(!preservedCommands.contains(command))
				try {
					commandFactory.addCommand(command);
				} catch (UnknownCommandException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}			
		}				
		activeCommansdBackup.clear();
	}

	public void execute(ShellRender shellRender)
	{
		if(commandName.equals("preferences"))
		{
			// TODO
		}else if(!commandName.equals("endpreferences"))
		{
			// TODO
		}else
		{
			// TODO
			shellRender.println("Preferences specification mode closed for id="+IDC+".");
		}
	}
	
	
	private void ShowHelp(ShellRender shellRender)
	{
		
	}
}
