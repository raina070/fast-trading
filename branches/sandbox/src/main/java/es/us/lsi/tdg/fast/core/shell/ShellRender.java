package es.us.lsi.tdg.fast.core.shell;
public interface ShellRender {

	public Command getCommand();

	public String getPrompt();

	public void setPrompt(String prompt);

}