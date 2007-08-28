package es.us.lsi.tdg.fast.core.process;

import es.us.lsi.tdg.fast.core.process.terminator.ProcessTerminator;

/**
 * @author Pablo Fernandez Montes
 * @author José Antonio Parejo Maestre
 */

public interface ControllableProcess {
	public void start();
	public void stop();
	public void start(ProcessTerminator terminator);
	public void start(ProcessModel model);
}
