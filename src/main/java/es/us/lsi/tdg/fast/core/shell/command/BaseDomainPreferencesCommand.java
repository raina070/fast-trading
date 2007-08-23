/**
 * 
 */
package es.us.lsi.tdg.fast.core.shell.command;

import es.us.lsi.tdg.fast.FAST;
import es.us.lsi.tdg.fast.core.dataModel.agreementPreferences.AgreementPreferences;
import es.us.lsi.tdg.fast.core.dataModel.agreementPreferences.BaseAgreementPreferences;
import es.us.lsi.tdg.fast.core.dataModel.statement.Attribute;
import es.us.lsi.tdg.fast.core.dataModel.statement.AttributeCatalog;
import es.us.lsi.tdg.fast.core.shell.DomainPreferencesCommand;
import es.us.lsi.tdg.fast.core.shell.ShellRender;

/**
 * @author Pablo Fernández Montes
 * @author José Antonio Parejo Maestre
 *
 */
public class BaseDomainPreferencesCommand extends BaseModeCommand implements
		DomainPreferencesCommand {
		
	private String PID;			
	private AttributeCatalog attributeCatalog;
	public BaseDomainPreferencesCommand(AttributeCatalog attributeCatalog) {
		super("preferences","Definition of trading preferences for a specific domain. Type \"preferencesmodehelp\" in preferences mode for domain specific information.");
		this.attributeCatalog=attributeCatalog;
		Command clearCommand=new BaseCommand("clearall","Remove all actual client preferences"){
			public void execute(ShellRender shellRenderer){
				clearallPreferences(shellRenderer);
			}
		};
		clearCommand.setCommandFactory(commandFactory);
		Command showCommand=new BaseCommand("show","Shows all actual client specified preferences"){
			public void execute(ShellRender shellRenderer){
				showPreferences(shellRenderer);
			}
		};
		showCommand.setCommandFactory(commandFactory);				
		addSubCommand(showCommand.getName(),showCommand);
		addSubCommand(clearCommand.getName(),clearCommand);				
	}

	/* (non-Javadoc)
	 * @see es.us.lsi.tdg.fast.core.shell.DomainPreferencesCommand#getAttribute()
	 */
	public AttributeCatalog getAttributeCatalog() {
		return FAST.currentDomain.getAttributeCatalog();
	}

	protected void initMode(ShellRender shellRenderer) {
		super.initMode(shellRenderer);		
	}
	
	public void execute(ShellRender shellRenderer)
	{	
		
		if(arguments[0].equals(getName()))
		{
			if(arguments.length>1){
				if(arguments[1].equals("help"))
				{
					showHelp(shellRenderer);
				}else{
					String candidatePID=arguments[1];
					if(validateCounterpartyID(candidatePID))
					{
						PID=candidatePID;
						AgreementPreferences preferences=FAST.preferenceRegistry.getPreferences(PID);
						// TODO IMPORTANTEEEE!!!! COMENTAR  CON PABLO. ¿Que assesement mechanism usamos?: --> Opciones: Carga dinámica, factoría global, submodo de especificación de assessement mechanism.
						if(preferences==null)
							preferences=new BaseAgreementPreferences(FAST.currentDomain.getAssessmentMechanism());						
						FAST.preferenceRegistry.setPreferences(PID, preferences);
						registersSpecificCommands(PID);
						super.execute(shellRenderer);						
					}else{
						shellRenderer.println("ERROR: "+candidatePID+" is not a valid client ID.");
					}	
				}
			}else
				shellRenderer.println("ERROR: You must specify the party ID (PID) for which you want specify the preferences.");
		}else
			super.execute(shellRenderer);		
	}
	
	private void registersSpecificCommands(String idc2) {		
		for(Attribute attribute:attributeCatalog.getAttributes())
		{
			 Command attributeCommand=new AttributePreferenceCommand(attribute,PID);
			 attributeCommand.setCommandFactory(commandFactory);
			 addSubCommand(attributeCommand.getName(),attributeCommand);			 
		}
	}

	private boolean validateCounterpartyID(String CID)
	{
		boolean result=false;
		try{
			int value=Integer.parseInt(CID);			
			result=value>0;
		}catch(NumberFormatException nfe){}
		return result;
	}
	
	private void showPreferences(ShellRender shellRenderer)
	{
		AgreementPreferences preferences=FAST.preferenceRegistry.getPreferences(PID);
		if(preferences==null)
		{
			shellRenderer.println("ERROR: Invalid party ID \""+PID+"\", the counter party is not registered.");
		}else{
			shellRenderer.println("Actual preferences of "+PID+":");
			shellRenderer.println(preferences.toString());
		}
	}
	
	private void clearallPreferences(ShellRender shellRenderer)
	{
		AgreementPreferences preferences=FAST.preferenceRegistry.getPreferences(PID);
		if(preferences==null)
		{
			shellRenderer.println("ERROR: Invalid party ID \""+PID+"\", the counter party is not registered.");
		}else{
			preferences.getFeatures().clear();
			preferences.getRequirements().clear();
			shellRenderer.println("Actual preferences of "+PID+":");
			shellRenderer.println(preferences.toString());
		}
	}	
}
