package es.us.lsi.tdg.fast.core.shell.command;

import es.us.lsi.tdg.fast.FAST;
import es.us.lsi.tdg.fast.components.BaseTradingProcess;
import es.us.lsi.tdg.fast.components.TradingProcess;
import es.us.lsi.tdg.fast.core.shell.ShellRender;

public class StartCommand extends BaseCommand {

	private String orchName;
	private String PID;
	
	private String error=null; 
	private static String usageHelp="Usage: start orchestration PID";
	
	public StartCommand() {
		super("start", 	"Starts a trading orchestration for a certain party.\n"+usageHelp);
	}
	
	public void configure(String[] arguments){
		if(arguments != null){
			 FAST.log.info("Configuring command "+
							this.getName()+
							" with "+
							arguments.length+
							" arguments: "+
							arguments);
		}
		
		error = null;
		
		if(arguments.length==3){
			
			String orchName = arguments[1];
			String PID = arguments[2];
						
			PID = validatePID(PID); 
			
			if(PID == null){
				error = "ERROR: Wrong PID.\n"+usageHelp;
			}

			orchName = validateOrchestration(orchName); 
			
			if(orchName == null){
				error = "ERROR: Wrong Orchestration name.\n"+usageHelp; 
			}
				
		}else{
			error = "ERROR: Wrong number of arguments.\n"+usageHelp;
		}
	}

	private String validateOrchestration(String orchName) {
		// TODO Check if orchestration is valid
		return orchName;
	}

	private String validatePID(String PID) {
		// TODO Check if Party exists.
		return PID;
	}

	public void execute(ShellRender shellRenderer)
	{		
		if(error != null){
			shellRenderer.println(error);			
			return;
		}
		
		TradingProcess tradingProcess = new BaseTradingProcess(PID,orchName);
								
		tradingProcess.start();
					
	}

}
