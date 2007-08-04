package es.us.lsi.tdg.fast.core.shell;
public interface ShellRender {

	public Command getCommand();

	public String getPrompt();

	public void setPrompt(String prompt);
	
	public void print(String s);
	
	public void println(String s);

	public void printWellcome();

}