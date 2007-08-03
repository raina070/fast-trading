package es.us.lsi.tdg.fast.core.shell;


public class BaseCommand implements Command {
	private String name;
	protected CommandFactory commandFactory;
	
	public BaseCommand(String name) {
		super();
		this.name = name;
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

}
