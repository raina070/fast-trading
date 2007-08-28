package es.us.lsi.tdg.fast.core.shell;

import es.us.lsi.tdg.fast.core.shell.command.Command;

public interface ShellRender {

	public Command getCommand();

	public String getPrompt();

	public void setPrompt(String prompt);
	
	public void print(String s);
	
	public void println(String s);

	public void printWellcome();

	public void showMessage(String message);

}