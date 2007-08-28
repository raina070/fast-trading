package es.us.lsi.tdg.fast.core.component;

import es.us.lsi.tdg.fast.core.trading.TradingProcess;

public interface ComponentFactory {

	public Component getByName(String componentName)
	throws UnknownComponentException;

	public Component getByName(String componentName, TradingProcess tradingProcess)
		throws UnknownComponentException;

	public void loadComponent(String className);

	@SuppressWarnings("unchecked")
	public void loadComponent(Class componentClass);

	public void loadComponent(Component component);

	public Component getByType(String string);

	public void bind(String choreography,Component comp1,Component comp2);
}