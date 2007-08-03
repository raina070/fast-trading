package es.us.lsi.tdg.fast.core.shell;

public class SimpleFASTShell implements FASTShell {

	public void run() {

		CommandFactory commandFactory;
		
		CommandFactory.loadCommand("es.us.lsi.tdg.fast.core.shell.BaseExitCommand");
		CommandFactory.loadCommand("es.us.lsi.tdg.fast.core.shell.LoadDomainCommand");
	    
		String[] initialCommands = {"exit","domain"};
		
		try {
			commandFactory = new CommandFactory(initialCommands);
			ShellRender shellRender = new ConsoleRender("FAST",commandFactory);

			FASTShell shell = new BaseFASTShell(shellRender);
			
			shell.run();
		} catch (UnknownCommandException e) {
			e.printStackTrace();
		}
		
	}

}
