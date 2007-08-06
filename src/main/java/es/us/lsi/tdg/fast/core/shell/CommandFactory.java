package es.us.lsi.tdg.fast.core.shell;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import es.us.lsi.tdg.fast.core.shell.command.Command;

public class CommandFactory {

	private static Map<String,Class> commandRegistry;
	private Map<String,Command> commandInstances;
	static {
          commandRegistry = new HashMap<String,Class>();          
    }	
	
	private Set<String> activeCommands;
	
	public void loadCommand(String commandName, Command command)
	{
		Class commandClass=command.getClass();
		commandInstances.put(commandName,command);
		loadCommand(commandName,commandClass.getCanonicalName());
	}
	
	public static void loadCommand(String commandName, String className)
	{
		Class commandClass;
		try {
		
			commandClass = Class.forName(className);
			
			commandRegistry.put(commandName,commandClass);
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
	
	
	public static void loadCommand(String className){
		
		Class commandClass;
		try {
		
			commandClass = Class.forName(className);
			
			Command command;
	
			command = (Command) commandClass.newInstance();
			
			commandRegistry.put(command.getName(),commandClass);
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		
	}
	
	public CommandFactory(String[] commandNames) throws UnknownCommandException  {
		commandInstances = new HashMap<String,Command>();
		activeCommands = new HashSet<String>();
		for (String commandName: commandNames) {
			if (commandRegistry.containsKey(commandName)){
				activeCommands.add(commandName);	
			}else throw new UnknownCommandException();
		}
	}


	public Set<String> getActiveCommands() {
		return activeCommands;
	}		
	
	public void addCommand(String commandName) throws UnknownCommandException  {
		if (commandRegistry.containsKey(commandName)|| commandInstances.containsKey(commandName) ){
			activeCommands.add(commandName);	
		}else throw new UnknownCommandException();
	}

	public void removeCommand(String commandName) throws UnknownCommandException,InvalidCommandException  {
		if (commandRegistry.containsKey(commandName) || commandInstances.containsKey(commandName)){
			if (activeCommands.contains(commandName)){
				activeCommands.remove(commandName);
				commandInstances.remove(commandName);				
			}else throw new InvalidCommandException();
		}else throw new UnknownCommandException();
	}

	public Command getCommand(String[] arguments) throws UnknownCommandException,InvalidCommandException {
		
		String commandName = null;
		Command command =null;
		if(arguments != null)
			if(arguments.length > 0)
			   commandName = arguments[0];
					
		if (activeCommands.contains(commandName)){
			if (commandRegistry.containsKey(commandName)){		
				if(commandInstances.containsKey(commandName))
					command=commandInstances.get(commandName);
				else{								
					Class commandClass = commandRegistry.get(commandName);
					
					try{					 					
						 command = (Command) commandClass.newInstance();
						command.setCommandFactory(this);						
						commandInstances.put(commandName, command);
					}catch(IllegalAccessException e){
						e.printStackTrace();
					}catch(InstantiationException e){
						e.printStackTrace();
					}
				}
			
			}else throw new UnknownCommandException();
		}else throw new InvalidCommandException();
		if(command!=null)
			command.configure(arguments);
		return command;
	}

}
