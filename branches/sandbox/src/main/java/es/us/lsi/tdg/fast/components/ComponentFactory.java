package es.us.lsi.tdg.fast.components;

public interface ComponentFactory {

	public Component getComponentByName(String componentName)
			throws UnknownComponentException;

}