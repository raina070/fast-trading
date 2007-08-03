package es.us.lsi.tdg.fast.core.shell;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class CommandFactory {

	private static Map<String,Class> commandRegistry;
	
	static {
          commandRegistry = new HashMap<String,Class>();
    }
	
	private Set<String> activeCommands;
	
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
		if (commandRegistry.containsKey(commandName)){
			activeCommands.add(commandName);	
		}else throw new UnknownCommandException();
	}

	public void removeCommand(String commandName) throws UnknownCommandException,InvalidCommandException  {
		if (commandRegistry.containsKey(commandName)){
			if (activeCommands.contains(commandName)){
				activeCommands.remove(commandName);
			}else throw new InvalidCommandException();
		}else throw new UnknownCommandException();
	}

	public Command getCommand(String[] parsedCommandLine) throws UnknownCommandException,InvalidCommandException {
		
		String commandName = null;
		
		if(parsedCommandLine != null)
			if(parsedCommandLine.length > 0)
			   commandName = parsedCommandLine[0];
			
		
		
		if (activeCommands.contains(commandName)){
			if (commandRegistry.containsKey(commandName)){
		
				Class commandClass = commandRegistry.get(commandName);
				
				try{
					
					Command command = (Command) commandClass.newInstance();
					command.setCommandFactory(this);
					command.configure(parsedCommandLine);
					return command;
					
				}catch(IllegalAccessException e){
					e.printStackTrace();
				}catch(InstantiationException e){
					e.printStackTrace();
				}
				
			
			}else throw new UnknownCommandException();
		}else throw new InvalidCommandException();
		
		return null;
	}

}
