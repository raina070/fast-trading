/**
 * 
 */
package es.us.lsi.tdg.fast.core.shell.command;

import es.us.lsi.tdg.fast.core.shell.InvalidCommandException;
import es.us.lsi.tdg.fast.core.shell.ShellRender;
import es.us.lsi.tdg.fast.core.shell.UnknownCommandException;

/**
 * @author José Antonio
 *
 */
public class HelpCommand extends BaseCommand {
	
	String commandName;
	public HelpCommand()
	{
		super("help","Shows information about other commands and usage of the system. Use help <<command>> to show specific help");
		commandName=null;
	}
	
	public void configure(String[] arguments){
		if(arguments.length>1)
			commandName=arguments[1];
		else
			commandName=null;
		
	}

	public void execute(ShellRender shellRender){
		if(commandName==null)
		{
			shellRender.println(getHelp());
			showValidCommands(shellRender);
		}else{
			String[] args={commandName};
			Command command;
			try {
				command = commandFactory.getCommand(args);
				shellRender.println(command.getHelp());
			} catch (UnknownCommandException e) {
				shellRender.println("ERROR: "+commandName+" is not a valid command.");
				showValidCommands(shellRender);
			} catch (InvalidCommandException e) {
				shellRender.println("ERROR: "+commandName+" is not a valid command.");
				showValidCommands(shellRender);
			}			
			
		}
	}

	private void showValidCommands(ShellRender shellRender)
	{
		shellRender.print("Valid commands are: ");
		for(String cname:commandFactory.getActiveCommands())					
			shellRender.print(cname+" ");
		shellRender.println("");
	}
	
}
