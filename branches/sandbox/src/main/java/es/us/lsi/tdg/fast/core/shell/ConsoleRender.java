package es.us.lsi.tdg.fast.core.shell;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;


public class ConsoleRender implements ShellRender {

	private String prompt;
	private CommandFactory commandFactory;
	
	
	public ConsoleRender(String prompt, CommandFactory commandFactory) {
		super();
		this.prompt = prompt;
		this.commandFactory = commandFactory;
	}

	/**
	 * Prints current Prompt
	 */
	private void printPrompt(){
		System.out.print(prompt+">");
	}
	
	/**
	 * Gets line entered by User in the console
	 */
	private String getCommandLine(){
		String commandLine = null;
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		try {
			commandLine = in.readLine();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return commandLine;
	}
	
	/**
	 * Parses the string commandLine and divides it in an array of strings 
	 * @param commandLine Command line to be parsed
	 * @return The array of strings in which the commandLine is divided
	 * @throws UnknownCommandException This exception is launched if the command (first 
	 * string of the commandLine is not present in the activeCommands.
	 */
	private String[] parseCommandLine(String commandLine){
	    if (commandLine == null)
	        return new String[0];
	    
	    char separator = ' ';

	    List<String> strings = new ArrayList<String>();
	
	    int startx = 0;
	    int cursor = 0;
	    int length = commandLine.length();
	
	    while (cursor < length)
	    {
	        if (commandLine.charAt(cursor) == separator)
	        {
	            String item = commandLine.substring(startx, cursor);
	            strings.add(item);
	            startx = cursor + 1;
	            cursor++;
	            while((commandLine.charAt(cursor) == separator) && (cursor < length)){
	            	startx = cursor + 1;
	            	cursor++;
	            }
	        }else{	
	        	cursor++;
	        }
	    }
	
	    if (startx < length)
	        strings.add(commandLine.substring(startx));
	
	    return (String[]) strings.toArray(new String[strings.size()]);		
	}

	/**
	 * Deals with the error (Invalid or unknown) and shows an appropriate message
	 */
	private void showError(String error){
		System.out.println(error);
		System.out.println("Valid Commands are "+commandFactory.getActiveCommands());
	}
	
	
	/* (non-Javadoc)
	 * @see ShellRender#getCommand(CommandFactory)
	 */
	
	public Command getCommand(){
		Command command = null;
		String commandLine = null;
		String[] parsedCommandLine = null;
		
		do{
			printPrompt();
			commandLine = getCommandLine();
			parsedCommandLine = parseCommandLine(commandLine);
			
			try{
				command = commandFactory.getCommand(parsedCommandLine);
			}catch(UnknownCommandException e){
				showError("FATAL ERROR: Command is valid but is not registered");
				command = null;
			} catch (InvalidCommandException e) {
				showError("ERROR: Invalid Command.");
				command = null;	
			}
			
		}while(command == null);
		
		return command;
	}

	/* (non-Javadoc)
	 * @see ShellRender#getPrompt()
	 */
	public String getPrompt() {
		return prompt;
	}

	/* (non-Javadoc)
	 * @see ShellRender#setPrompt(java.lang.String)
	 */
	public void setPrompt(String prompt) {
		this.prompt = prompt;
	}

	public void print(String s) {
		System.out.print(s);		
	}

	public void println(String s) {
		System.out.println(s);		
	}

}
