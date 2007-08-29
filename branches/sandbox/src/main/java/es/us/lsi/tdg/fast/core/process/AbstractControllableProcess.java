/**
 * 
 */
package es.us.lsi.tdg.fast.core.process;

import es.us.lsi.tdg.fast.core.process.event.EventBroker;
import es.us.lsi.tdg.fast.core.process.event.FASTProcessEvent;
import es.us.lsi.tdg.fast.core.process.event.FASTProcessEventType;
import es.us.lsi.tdg.fast.core.process.terminator.ProcessTerminator;



/**
 * @author Pablo Fernandez Montes
 * @author José Antonio Parejo Maestre
 *
 */
public abstract class AbstractControllableProcess implements ControllableProcess {

	private Thread controlledThread;
	private boolean pauseSignal;
	private String threadName;
	private ProcessTerminator terminator;
	private boolean clean;
	
	public AbstractControllableProcess()
	{
		this("");
	}
	
	public AbstractControllableProcess(String threadName)
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

	public void start(ProcessTerminator terminator) {
		this.terminator = terminator;
		start();
	}

	public void start(ProcessModel model) {
		this.terminator = model.getTerminator();
		start();
		
	}
	
	public synchronized void start() {
		
		if(terminator==null)
			start(ProcessModel.SINGLE);
			
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

		synchronized(this){
			if(!clean){
				cleanUp();
				clean = true;
			}
		}
		
		// We wait the execution thread to stop
		while(controlledThread.isAlive());
		
		
		
		controlledThread=null;
		
		EventBroker.event(new FASTProcessEvent(FASTProcessEventType.STOPED,this));
		
	}
	
	private void execute()
	{
	
		setUp();
		clean = false;
		EventBroker.event(new FASTProcessEvent(FASTProcessEventType.STARTED,this));
		
		do {
			run();
			controlledThread.yield();
		} while(!terminator.terminate());
		
		synchronized(this){
			if(!clean){
				cleanUp();
				clean=true;
			}
		}
		
		EventBroker.event(new FASTProcessEvent(FASTProcessEventType.TASK_DONE,this));
			
	}
	
	protected abstract void  run();
	
	protected void cleanUp(){};
	protected void setUp(){};
	

}
