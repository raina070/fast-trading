package es.us.lsi.tdg.fast.core.shell;
public class BaseFASTShell implements FASTShell {

	private ShellRender shellRender;

	public BaseFASTShell(ShellRender shellRender){
		this.shellRender = shellRender;
	}

	public void run() {
		Command command = null;
		do{
			command = shellRender.getCommand();
			command.execute(shellRender);	
		}while( !(command instanceof ExitCommand)); 
	}

}
