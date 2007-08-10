package es.us.lsi.tdg.fast.core.shell;

public interface FASTShell {

	public void run();
	
	public void showMessage(String message);
	
	public ShellRender getShellRender();
	
}
