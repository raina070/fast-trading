/**
 * 
 */
package es.us.lsi.tdg.fast.core.shell.command;

import java.util.HashSet;
import java.util.Set;

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
		
	private String IDC;			
	private AttributeCatalog attributeCatalog;
	public BaseDomainPreferencesCommand(AttributeCatalog attributeCatalog) {
		super("preferences","Definition of trading preferences for a pecific domain. Type \"preferencesmodehelp\" in preferences mode for domain specific information.");
		this.attributeCatalog=attributeCatalog;
		Command clearCommand=new BaseCommand("clearall","Remove all actual counter party preferences"){
			public void execute(ShellRender shellRenderer){
				clearallPreferences(shellRenderer);
			}
		};
		clearCommand.setCommandFactory(commandFactory);
		Command showCommand=new BaseCommand("show","Shows all actual conterparty specified preferences"){
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
				if(!arguments[1].equals("help"))
				{
					String candidateIDC=arguments[1];
					if(validateCounterpartyID(candidateIDC))
					{
						IDC=candidateIDC;
						AgreementPreferences preferences=FAST.preferenceRegistry.getPreferences(IDC);
						// TODO IMPORTANTEEEE!!!! COMENTAR  CON PABLO. ¿Que assesement mechanism usamos?: --> Opciones: Carga dinámica, factoría global, submodo de especificación de assessement mechanism.
						if(preferences==null)
							preferences=new BaseAgreementPreferences(null);						
						FAST.preferenceRegistry.setPreferences(IDC, preferences);
						registersSpecificCommands(IDC);
						super.execute(shellRenderer);						
					}else{
						shellRenderer.println("ERROR: "+candidateIDC+" is not a valid CounterParty ID.");
					}
				}else{
					showHelp(shellRenderer);
				}
			}else
				shellRenderer.println("ERROR: You must specify the counterparty ID (IDC) for which you want specify the preferences.");
		}else
			super.execute(shellRenderer);		
	}
	
	private void registersSpecificCommands(String idc2) {		
		for(Attribute attribute:attributeCatalog.getAttributes())
		{
			 Command attributeCommand=new AttributePreferenceCommand(attribute,IDC);
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
		AgreementPreferences preferences=FAST.preferenceRegistry.getPreferences(IDC);
		if(preferences==null)
		{
			shellRenderer.println("ERROR: Invalid counter party ID \""+IDC+"\", the counter party is not registered.");
		}else{
			shellRenderer.println("Actual preferences of "+IDC+":");
			shellRenderer.println(preferences.toString());
		}
	}
	
	private void clearallPreferences(ShellRender shellRenderer)
	{
		AgreementPreferences preferences=FAST.preferenceRegistry.getPreferences(IDC);
		if(preferences==null)
		{
			shellRenderer.println("ERROR: Invalid counter party ID \""+IDC+"\", the counter party is not registered.");
		}else{
			preferences.getFeatures().clear();
			preferences.getRequirements().clear();
			shellRenderer.println("Actual preferences of "+IDC+":");
			shellRenderer.println(preferences.toString());
		}
	}	
}
