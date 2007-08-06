package es.us.lsi.tdg.fast.core.shell.command;

import es.us.lsi.tdg.fast.core.shell.CommandFactory;
import es.us.lsi.tdg.fast.core.shell.ShellRender;

public interface Command {
	public String getName();
	public String getHelp();
	public void configure(String args[]);
	public void execute(ShellRender shellRender);
	public void setCommandFactory(CommandFactory commandFactory);

}