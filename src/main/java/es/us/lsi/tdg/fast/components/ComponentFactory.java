package es.us.lsi.tdg.fast.components;

public interface ComponentFactory {

	public Component getByName(String componentName)
			throws UnknownComponentException;

	public void loadComponent(String className);

	@SuppressWarnings("unchecked")
	public void loadComponent(Class componentClass);

	public void loadComponent(Component component);

	public Component getByType(String string);
}