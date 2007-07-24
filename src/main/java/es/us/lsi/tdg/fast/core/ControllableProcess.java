package es.us.lsi.tdg.fast.core;
/**
 * @author Pablo Fernandez Montes
 * @author Jos� Antonio Parejo Maestre
 */

public interface ControllableProcess {
	public void start();
	public void pause();
	public void resume();
	public void stop();
}
