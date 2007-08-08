package es.us.lsi.tdg.fast.core.shell.command;

import es.us.lsi.tdg.fast.FAST;
import es.us.lsi.tdg.fast.core.shell.ShellRender;
import es.us.lsi.tdg.fast.core.trading.BaseTradingProcess;
import es.us.lsi.tdg.fast.core.trading.TradingProcess;

public class StartCommand extends BaseCommand {

	private String orchName;
	private String PID;
	
	private String error=null; 
	private static String usageHelp="Usage: start PID [orchestrationName]";
	
	public StartCommand() {
		super("start", 	"Starts the trading process for a certain party .\n"+usageHelp);
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
		
		if(arguments.length>1 && arguments.length<4){
			
			String PID = arguments[1];
						
			this.PID = validatePID(PID); 
			
			if(PID == null){
				error = "ERROR: Wrong PID.\n"+usageHelp;
			}

			if(arguments.length == 3){
				String orchName = arguments[2];
				this.orchName = validateOrchestration(orchName); 
			
				if(orchName == null){
					error = "ERROR: Wrong Orchestration name.\n"+usageHelp; 
				}
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
		
		TradingProcess tradingProcess;
		
		if (this.orchName == null)
			tradingProcess = new BaseTradingProcess(PID);
		else
			tradingProcess = new BaseTradingProcess(PID,orchName);
								
		tradingProcess.start();
					
	}

}
