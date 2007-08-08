package es.us.lsi.tdg.fast.core.shell.command;

import es.us.lsi.tdg.fast.core.shell.ShellRender;

public class BaseExitCommand extends BaseCommand implements ExitCommand {

	public BaseExitCommand() {
		super("exit","Close FAST and exit.");
	}
	
	public void execute(ShellRender shellRender){
		System.out.println("Namárië!");
	}
}
