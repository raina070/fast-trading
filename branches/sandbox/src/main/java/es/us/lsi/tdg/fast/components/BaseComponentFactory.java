package es.us.lsi.tdg.fast.components;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import es.us.lsi.tdg.fast.FAST;


public class BaseComponentFactory implements ComponentFactory {

	private Map<String,Class<Component>> componentRegistry;
	private static ComponentFactory instance = null;

	private BaseComponentFactory(){
          componentRegistry = new HashMap<String,Class<Component>>();
          FAST.shell.showMessage("Loading generic components...");
          //loadComponent("es.us.lsi.tdg.fast.components.trading.BaseTradingComponent");  
    }
		
	@SuppressWarnings("unchecked")
	public void loadComponent(String className){
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
	
	public void loadComponent(Class componentClass){

		try {
		
			Component component;
	
			component = (Component) componentClass.newInstance();
			
			componentRegistry.put(component.getName(),componentClass);

			FAST.shell.showMessage("    Component <"+componentClass.getName()+"> loaded.");
			
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
	public Component getByName(String componentName) throws UnknownComponentException{
		

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

	public static ComponentFactory getInstance() {
		if(instance==null){
			instance = new BaseComponentFactory();
		}
		return instance;
	}


	public void loadComponent(Component component) {
		this.loadComponent(component.getClass());
	}

	public Component getByType(String type) {

		try{
		
			Collection<Class<Component>> components = componentRegistry.values();
			
			Iterator<Class<Component>> iterator = components.iterator();
			
			while(iterator.hasNext()){
				Class<Component> componentClass =  iterator.next();
				Component component = (Component) componentClass.newInstance();
				
				if(component.getType().equals(type))
					return component;
				
			}
		
		}catch(IllegalAccessException e){
			e.printStackTrace();
		}catch(InstantiationException e){
			e.printStackTrace();
		}
		
		
		return null;
	}
}