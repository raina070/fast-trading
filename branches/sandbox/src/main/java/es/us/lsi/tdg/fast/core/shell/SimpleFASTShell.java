package es.us.lsi.tdg.fast.core.shell;

public class SimpleFASTShell implements FASTShell {
	
	FASTShell shell;
	
	public SimpleFASTShell() {
		CommandFactory commandFactory;
		
		CommandFactory.loadCommand("es.us.lsi.tdg.fast.core.shell.command.BaseExitCommand");
		CommandFactory.loadCommand("es.us.lsi.tdg.fast.core.shell.command.LoadDomainCommand");
	    CommandFactory.loadCommand("es.us.lsi.tdg.fast.core.shell.command.HelpCommand");
	    CommandFactory.loadCommand("es.us.lsi.tdg.fast.core.shell.command.StartCommand");
		String[] initialCommands = {"exit","domain","help","start"};
		
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
