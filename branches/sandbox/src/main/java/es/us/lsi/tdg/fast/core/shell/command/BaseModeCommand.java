/**
 * 
 */
package es.us.lsi.tdg.fast.core.shell.command;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import es.us.lsi.tdg.fast.core.shell.InvalidCommandException;
import es.us.lsi.tdg.fast.core.shell.ShellRender;
import es.us.lsi.tdg.fast.core.shell.UnknownCommandException;

/**
 * @author Pablo Fernández Montes
 * @author José Antonio Parejo Maestre
 *
 */
public class BaseModeCommand extends BaseCommand
{
	protected Set<String> activeCommansdBackup;
	protected String promptBackup;
	protected Set<String> preservedCommands;
	protected boolean modeInited;
	protected String canNotCloseModeMessage;
	protected String canNotInitModeMessage;

	protected Map<String,Command> subCommands;
	
	protected String[] arguments;

	public BaseModeCommand(String name, String help)
	{
		super(name,help);
		activeCommansdBackup=new HashSet<String>();
		preservedCommands=new HashSet<String>();
		subCommands=new HashMap<String,Command>();
		canNotCloseModeMessage="WARNING: You must init the "+getName()+"mode berfore close.";
		canNotInitModeMessage="WARNING: You are in "+getName()+"mode now, close it before init again.";
		modeInited=false;
		preservedCommands.add("exit");
		preservedCommands.add("help");
		Command endmodeCommand=new BaseCommand("close"+name+"mode","Closes the "+name+" mode")
		{
			public void execute(ShellRender shellRenderer){
				closeMode(shellRenderer);
			}
		};
		endmodeCommand.setCommandFactory(commandFactory);
		addSubCommand(endmodeCommand.getName(), endmodeCommand);
		Command specificHelpCommand=new BaseCommand(name+"modehelp","Shows help about mode specific commands.")
		{
			public void execute(ShellRender shellRenderer){
				showHelp(shellRenderer);
			}
		};
		specificHelpCommand.setCommandFactory(commandFactory);
		addSubCommand(specificHelpCommand.getName(), specificHelpCommand);
	}
	
	public void configure(String[] arguments)
	{		
		this.arguments=new String[arguments.length];
		System.arraycopy(arguments, 0, this.arguments, 0, arguments.length);		
	}
	
	protected void initMode(ShellRender shellRenderer)
	{
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
		promptBackup=shellRenderer.getPrompt();
		shellRenderer.setPrompt(promptBackup+"-"+getName()+"mode");
		for(Command command:subCommands.values())
		{			
			commandFactory.loadCommand(command.getName(), this);			
			try {
				commandFactory.addCommand(command.getName());
			} catch (UnknownCommandException e) {}
		}
		modeInited=true;
	}
	
	
	protected void closeMode(ShellRender shellRenderer)
	{
		for(Command command:subCommands.values())
		{			
			try {
				commandFactory.removeCommand(command.getName());
			} 
			catch (UnknownCommandException e) {} 
			catch (InvalidCommandException e) {}
		}
		
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
		shellRenderer.setPrompt(promptBackup);
		modeInited=false;
	}
	
	public void execute(ShellRender shellRenderer)
	{
		if(arguments[0].equals(getName()))
		{
			if(canInitMode())
				initMode(shellRenderer);
			else
				shellRenderer.println(canNotInitModeMessage);
		}else if(arguments[0].equals("close"+getName()+"mode"))
		{
			if(canCloseMode())
				closeMode(shellRenderer);
			else
				shellRenderer.println(canNotCloseModeMessage);
		}else
		{
			Command command=subCommands.get(arguments[0]);
			if(command!=null)
			{
				command.configure(arguments);
				command.execute(shellRenderer);
			}else
			{
				// TODO Preguntar a PABLO si hay que lanzar la excepción o se muestra el mensaje.
				shellRenderer.println("ERROR: "+getName()+"mode has not a "+arguments[0]+" valid subcommand.");
			}
				
		}
	}
	
	protected void showHelp(ShellRender shellRenderer)
	{
		for(Command command:subCommands.values())
		{
			shellRenderer.println("  - "+command.getName()+": "+command.getHelp());			
		}
		shellRenderer.println(" - close"+getName()+"mode: Closes the actual shell mode.");
	}
	
	protected boolean canCloseMode()
	{
		return modeInited;
	}
	
	protected boolean canInitMode()
	{
		return !modeInited;
	}
	
	public void addSubCommand(String commandName, Command command)
	{
		subCommands.put(commandName, command);
		// TODO should we assign the command factory of this object to the subcommand?
	}
	
	public void loadSubCommand(Command command){
		commandFactory.loadCommand(command.getName(), this);			
		try {
			commandFactory.addCommand(command.getName());
		} catch (UnknownCommandException e) {}
	}
	
	public void unloadSubCommand(String commandName){
			try {
				commandFactory.removeCommand(commandName);
			} catch (UnknownCommandException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InvalidCommandException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
	
	public void removeSubCommand(String commandName) throws UnknownCommandException
	{
		if(subCommands.containsKey(commandName))
			subCommands.remove(commandName);
		else
			throw new UnknownCommandException();
	}
	
}
