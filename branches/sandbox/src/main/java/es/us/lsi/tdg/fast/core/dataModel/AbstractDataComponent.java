package es.us.lsi.tdg.fast.core.dataModel;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class AbstractDataComponent {
	
	protected Map<String,Object> properties;
	
	public AbstractDataComponent()
	{
		properties=new HashMap<String,Object>();
	}
	
	public int propertiesCount(){
		return properties.size();
	}
	
	public Set<String> propertyNames()
	{
		return properties.keySet();
	}
	
	public boolean isProperty(String propertyName)
	{
		return properties.containsKey(propertyName);
	}
	
	public Object get(String propertyName)
	{
		return properties.get(propertyName);
	}
	
	public String getAsString(String propertyName)
	{
		return properties.get(propertyName).toString();
	}
	
	public void set(String propertyName, Object propertyValue)
	{
		properties.put(propertyName, propertyValue);
	}
	
	public boolean delete(String propertyName)
	{
		return properties.remove(propertyName)!=null;
	}	
	
}
