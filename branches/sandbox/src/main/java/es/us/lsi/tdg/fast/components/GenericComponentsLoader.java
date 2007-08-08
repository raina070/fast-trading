package es.us.lsi.tdg.fast.components;

import es.us.lsi.tdg.fast.FAST;
import es.us.lsi.tdg.fast.core.component.ComponentFactory;

public class GenericComponentsLoader {

	static String[] componentClassNames= {
		"es.us.lsi.tdg.fast.components.information.BaseInformation"	
	};
	
	public static void loadComponents(ComponentFactory componentFactory){
		
		FAST.shell.showMessage("Loading generic components...");
		for(String componentClassName:componentClassNames){
			componentFactory.loadComponent(componentClassName);
		}
		
	}
	
	
	
}
