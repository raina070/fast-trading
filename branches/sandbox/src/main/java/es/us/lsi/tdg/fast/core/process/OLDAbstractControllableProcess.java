/**
 * 
 */
package es.us.lsi.tdg.fast.core.process;



/**
 * @author Pablo Fernandez Montes
 * @author José Antonio Parejo Maestre
 *
 */
public abstract class OLDAbstractControllableProcess implements ControllableProcess {

	private Thread controlledThread;
	private boolean pauseSignal;
	private String threadName;
	
	public OLDAbstractControllableProcess()
	{
		this("");
	}
	
	public OLDAbstractControllableProcess(String threadName)
	{
		pauseSignal=false;
		this.threadName=threadName;
		Runnable myRunnable=new Runnable() {
			public void run() {
				execute();
			}
		};
		controlledThread=new Thread(myRunnable,threadName);
	}
	
	public synchronized void pause() {
		pauseSignal=true;		
	}

	public synchronized void resume() { 
		pauseSignal=false;
		notify();
	}

	public synchronized void start() {
		if(!controlledThread.isAlive())
		{
			if(controlledThread==null){			
				Runnable myRunnable=new Runnable() {
					public void run() {
						execute();
					}
				};
				controlledThread=new Thread(myRunnable,threadName);
			}
			controlledThread.start();
		}
	}

	public synchronized void stop() {
		controlledThread.interrupt();
		
		// We wait the execution thread to stop
		while(controlledThread.isAlive());
		
		controlledThread=null;
	}
	
	private void execute()
	{
		try {
			while(true)
			{
				if(pauseSignal)
					wait();			
				run();
			}
		}catch (InterruptedException e) {
		}					
	}
	
	protected abstract void  run();

	public boolean isPaused() {
		return pauseSignal;
	}
	

}
