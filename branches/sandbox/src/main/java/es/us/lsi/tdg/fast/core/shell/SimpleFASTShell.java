package es.us.lsi.tdg.fast.core.shell;

public class SimpleFASTShell implements FASTShell {
	
	FASTShell shell;
	
	public SimpleFASTShell() {
		CommandFactory commandFactory;
		
		CommandFactory.loadCommand("es.us.lsi.tdg.fast.core.shell.BaseExitCommand");
		CommandFactory.loadCommand("es.us.lsi.tdg.fast.core.shell.LoadDomainCommand");
	    
		String[] initialCommands = {"exit","domain"};
		
		try {
			commandFactory = new CommandFactory(initialCommands);
			ShellRender shellRender = new ConsoleRender("FAST",commandFactory);

			shell = new BaseFASTShell(shellRender);
			
		} catch (UnknownCommandException e) {
			e.printStackTrace();
		}

		
	}

	
	public void run() {
		shell.run();				
	}

	public void showMessage(String message) {
		shell.showMessage(message);
	}

}
