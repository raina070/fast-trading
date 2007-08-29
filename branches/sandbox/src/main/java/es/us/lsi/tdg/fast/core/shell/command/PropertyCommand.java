package es.us.lsi.tdg.fast.core.shell.command;

import es.us.lsi.tdg.fast.FAST;
import es.us.lsi.tdg.fast.core.shell.ShellRender;

public class PropertyCommand extends BaseCommand {
	String value;
	String key;
	
	public PropertyCommand(String name, String help) {
		super(name, generateHelp(help));	
	}
	
	/* (non-Javadoc)
	 * @see es.us.lsi.tdg.fast.core.shell.command.Command#configure(java.lang.String[])
	 */
	public void configure(String[] arguments) {		
		if(arguments.length==2){
			key=arguments[0];
			value=arguments[1];
		}else{
			key=null;
		}
	}

	/* (non-Javadoc)
	 * @see es.us.lsi.tdg.fast.core.shell.command.Command#execute(es.us.lsi.tdg.fast.core.shell.ShellRender)
	 */
	public void execute(ShellRender shellRenderer) {
		// TODO Auto-generated method stub
		if (FAST.properties.containsKey(key))
			FAST.properties.put(key, value);
		else
			shellRenderer.println("ERROR: Property " + key + " does not exist.\n");
	}

	
	/* (non-Javadoc)
	 * @see es.us.lsi.tdg.fast.core.shell.command.Command#getHelp()
	 */
	
	private static String generateHelp(String description) {		
		StringBuffer sb=new StringBuffer("Allows to define system  ");
		sb.append(description);
		sb.append("\".");
		return sb.toString();
	}

}
