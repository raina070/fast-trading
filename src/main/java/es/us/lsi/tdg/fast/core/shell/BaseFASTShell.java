package es.us.lsi.tdg.fast.core.shell;

import es.us.lsi.tdg.fast.core.shell.command.Command;
import es.us.lsi.tdg.fast.core.shell.command.ExitCommand;

public class BaseFASTShell implements FASTShell {

	private ShellRender shellRender;

	public BaseFASTShell(ShellRender shellRender){
		this.shellRender = shellRender;
		shellRender.printWellcome();
	}
	
	public void run() {
		Command command = null;
	
		do{
			command = shellRender.getCommand();
			command.execute(shellRender);	
		}while( !(command instanceof ExitCommand)); 
	}

	public void showMessage(String message) {
		shellRender.showMessage(message);
	}
	
	public ShellRender getShellRender(){
		return shellRender;
	}
}
