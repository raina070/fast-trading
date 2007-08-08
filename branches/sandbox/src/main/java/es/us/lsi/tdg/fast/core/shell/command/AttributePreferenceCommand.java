/**
 * 
 */
package es.us.lsi.tdg.fast.core.shell.command;

import es.us.lsi.tdg.fast.FAST;
import es.us.lsi.tdg.fast.core.dataModel.agreementPreferences.AgreementPreferences;
import es.us.lsi.tdg.fast.core.dataModel.statement.Attribute;
import es.us.lsi.tdg.fast.core.dataModel.statement.AttributePreferenceBuilder;
import es.us.lsi.tdg.fast.core.dataModel.statement.Statement;
import es.us.lsi.tdg.fast.core.dataModel.statement.StatementFactory;
import es.us.lsi.tdg.fast.core.shell.ShellRender;

/**
 * @author Pablo Fernández Montes
 * @author José Antonio Parejo Maestre
 *
 */
public class AttributePreferenceCommand extends BaseCommand {

	String IDC;
	Attribute attribute;
	String [] preferenceDefinition;
	StatementFactory preferenceFactory;
	
	public AttributePreferenceCommand(Attribute attribute, String IDC) {
		super(attribute.getName(), generateHelp(attribute));
		this.IDC=IDC;
		this.attribute=attribute;
		preferenceFactory=new AttributePreferenceBuilder(attribute);
	}
	
	/* (non-Javadoc)
	 * @see es.us.lsi.tdg.fast.core.shell.command.Command#configure(java.lang.String[])
	 */
	public void configure(String[] args) {		
		preferenceDefinition=new String[args.length-1];
		System.arraycopy(args, 1, preferenceDefinition, 0, args.length-1);
	}

	/* (non-Javadoc)
	 * @see es.us.lsi.tdg.fast.core.shell.command.Command#execute(es.us.lsi.tdg.fast.core.shell.ShellRender)
	 */
	public void execute(ShellRender shellRender) {
		// TODO Auto-generated method stub
		AgreementPreferences preferences=FAST.preferenceRegistry.getPreferences(IDC);
		if(preferences!=null)
		{
			Statement preference=null;					
			try
			{
				preference=preferenceFactory.create(preferenceDefinition);
			}catch(Exception ex){
				shellRender.println(generatePreferenceDefinitionError("\nINFO: "+ex.getMessage()));
			}
			if(preference!=null){
				// TODO IMPORTANTEEEE!!! PREGUNTAR A PABLO: feature o requeriment ¿indicar por usuario?
				preferences.getRequirements().add(preference);
			}else{				
				shellRender.println(generatePreferenceDefinitionError(""));
			}
		}else
			shellRender.println("ERROR: Invalid client ID \""+IDC+"\", the counter party is not registered.");		
	}

	private String generatePreferenceDefinitionError(String info) {
		StringBuffer sb=new StringBuffer("ERROR: Could not build a valid preference for ");
		sb.append(attribute.getName());
		sb.append(" using definition: \"");
		for(String s:preferenceDefinition)
			sb.append(s);		
		sb.append("\".");
		sb.append(info);
		return sb.toString();
	}

	/* (non-Javadoc)
	 * @see es.us.lsi.tdg.fast.core.shell.command.Command#getHelp()
	 */
	
	private static String generateHelp(Attribute attribute2) {		
		StringBuffer sb=new StringBuffer("Allows to define preferences for attribute ");
		sb.append(attribute2.getName());
		sb.append(" that is defined as: \"");	
		sb.append(attribute2.getDescription());
		sb.append("\".");
		return sb.toString();
	}
	
}
