package es.us.lsi.tdg.fast.core.shell;

public class BaseExitCommand extends BaseCommand implements ExitCommand {

	public BaseExitCommand() {
		super("exit","Close FAST and exit.");
	}
	
	public void execute(ShellRender shellRender){
		System.out.println("Hasta luego lucas!");
	}
}
