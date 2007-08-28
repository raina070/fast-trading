package es.us.lsi.tdg.fast.core.component;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import es.us.lsi.tdg.fast.FAST;
import es.us.lsi.tdg.fast.core.choreographies.Choreography;
import es.us.lsi.tdg.fast.core.choreographies.wiring.BaseWiringChoreographyFactory;
import es.us.lsi.tdg.fast.core.choreographies.wiring.ChoreographyFactory;
import es.us.lsi.tdg.fast.core.trading.TradingProcess;


public class BaseComponentFactory implements ComponentFactory {

	private Map<String,Class<Component>> componentRegistry;
	private static ComponentFactory instance = null;
	private ChoreographyFactory wiringChoreographyFactory = null;

	private BaseComponentFactory(){
          componentRegistry = new HashMap<String,Class<Component>>();
          wiringChoreographyFactory = BaseWiringChoreographyFactory.getInstance();
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
			// 
			e.printStackTrace();
		} catch (InstantiationException e) {
			// 
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// 
			e.printStackTrace();
		}
		
		
	}
	
	@SuppressWarnings("unchecked")
	public void loadComponent(Class componentClass){

		try {
		
			Component component;
	
			component = (Component) componentClass.newInstance();
			
			componentRegistry.put(component.getName(),componentClass);

			FAST.shell.showMessage("    Component <"+componentClass.getName()+"> loaded.");
			
		} catch (InstantiationException e) {
			// 
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// 
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
				FAST.shell.showMessage("    Component "+component.getName()+" instantianted.");
				return component;
				
			}catch(IllegalAccessException e){
				e.printStackTrace();
			}catch(InstantiationException e){
				e.printStackTrace();
			}
			
		
		}else throw new UnknownComponentException();
		
		return null;
	}

	
	public Component getByName(String componentName,TradingProcess tradingProcess) 
		throws UnknownComponentException{
	
		Component component = getByName(componentName);	
		component.setTradingProcess(tradingProcess);
		
		return component;
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
				
				if(component.getType().equals(type)){
					FAST.shell.showMessage("    Component "+component.getName()+" instantianted.");
					return component;
				}
			}
		
		}catch(IllegalAccessException e){
			e.printStackTrace();
		}catch(InstantiationException e){
			e.printStackTrace();
		}
		
		
		return null;
	}

	public void bind(String wiringChoreographyName, Component comp1, Component comp2) {
		Choreography wiringChoreography = this.wiringChoreographyFactory.getByName(wiringChoreographyName);

		comp1.setWiringChoreography(wiringChoreography);
		comp2.setWiringChoreography(wiringChoreography);
		
		FAST.shell.showMessage("    Wiring done: "+comp1.getName()+" <"+wiringChoreographyName+"> "+comp2.getName());
	}

}