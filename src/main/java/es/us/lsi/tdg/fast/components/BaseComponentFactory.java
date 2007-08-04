package es.us.lsi.tdg.fast.components;
import java.util.HashMap;
import java.util.Map;

import es.us.lsi.tdg.fast.FAST;


public class BaseComponentFactory implements ComponentFactory {

	private static Map<String,Class<Component>> componentRegistry;
	
	static {
          componentRegistry = new HashMap<String,Class<Component>>();
          FAST.shell.showMessage("Loading generic components...");
          loadComponent("es.us.lsi.tdg.fast.components.trading.BaseTradingComponent");  
    }
		
	@SuppressWarnings("unchecked")
	public static void loadComponent(String className){
		Class<Component> componentClass;
		try {
		
			componentClass = (Class<Component>) Class.forName(className);
			
			Component component;
	
			component = (Component) componentClass.newInstance();
			
			componentRegistry.put(component.getName(),componentClass);

			FAST.shell.showMessage("    Component <"+className+"> loaded.");
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

	/* (non-Javadoc)
	 * @see es.us.lsi.tdg.fast.components.ComponentFactory#getComponentByName(java.lang.String)
	 */
	public Component getComponentByName(String componentName) throws UnknownComponentException{
		

		if (componentRegistry.containsKey(componentName)){
	
			Class<Component> componentClass = componentRegistry.get(componentName);
			
			try{
				
				Component component = (Component) componentClass.newInstance();
				return component;
				
			}catch(IllegalAccessException e){
				e.printStackTrace();
			}catch(InstantiationException e){
				e.printStackTrace();
			}
			
		
		}else throw new UnknownComponentException();
		
		return null;
	}

}