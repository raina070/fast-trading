package es.us.lsi.tdg.fast.core.shell;
public interface Command {
	public String getName();
	public void configure(String args[]);
	public void execute(ShellRender shellRender);
	public void setCommandFactory(CommandFactory commandFactory);

}