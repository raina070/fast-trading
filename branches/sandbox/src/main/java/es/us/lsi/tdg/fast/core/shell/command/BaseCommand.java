package es.us.lsi.tdg.fast.core.shell.command;

import es.us.lsi.tdg.fast.core.shell.CommandFactory;
import es.us.lsi.tdg.fast.core.shell.ShellRender;


public class BaseCommand implements Command {
	private String name;
	private String help;
	protected CommandFactory commandFactory;	
	
	public BaseCommand(String name, String help) {
		super();
		this.name = name;
		this.help=help;
	}	
	
	public void configure(String[] arguments){
		
		
	}

	public void execute(ShellRender shellRender){
		System.out.println("#Executing command "+this.name+"#");
	}

	public void setCommandFactory(CommandFactory commandFactory) {
		this.commandFactory = commandFactory;
	}

	public String getName() {
		return name;
	}

	public String getHelp() {		
		return help;
	}

}
