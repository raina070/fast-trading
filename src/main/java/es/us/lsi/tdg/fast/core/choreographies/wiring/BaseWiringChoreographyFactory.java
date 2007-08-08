package es.us.lsi.tdg.fast.core.choreographies.wiring;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import es.us.lsi.tdg.fast.FAST;
import es.us.lsi.tdg.fast.core.choreographies.Choreography;
import es.us.lsi.tdg.fast.core.choreographies.UnknownChoreographyException;


public class BaseWiringChoreographyFactory implements ChoreographyFactory{

	private Map<String,Class<Choreography>> wiringChoreographyRegistry;
	private static ChoreographyFactory instance = null;

	private BaseWiringChoreographyFactory(){
          wiringChoreographyRegistry = new HashMap<String,Class<Choreography>>();
          FAST.shell.showMessage("Loading generic wiring choreographies...");
          loadChoreography("es.us.lsi.tdg.fast.core.choreographies.wiring.PullPotentialCounterPartyNotification");  
    }
		
	/* (non-Javadoc)
	 * @see es.us.lsi.tdg.fast.core.choreographies.wiring.ChoreographyFactory#loadChoreography(java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	public void loadChoreography(String className){
		Class<Choreography> wiringChoreographyClass;
		try {
		
			wiringChoreographyClass = (Class<Choreography>) Class.forName(className);	
			Choreography wiringChoreography;
	
			wiringChoreography = (Choreography) wiringChoreographyClass.newInstance();
			
			wiringChoreographyRegistry.put(wiringChoreography.getName(),wiringChoreographyClass);

			FAST.shell.showMessage("    Choreography <"+className+"> loaded.");
			
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
	 * @see es.us.lsi.tdg.fast.core.choreographies.wiring.ChoreographyFactory#loadChoreography(java.lang.Class)
	 */
	@SuppressWarnings("unchecked")
	public void loadChoreography(Class wiringChoreographyClass){

		try {
		
			Choreography wiringChoreography;
	
			wiringChoreography = (Choreography) wiringChoreographyClass.newInstance();
			
			wiringChoreographyRegistry.put(wiringChoreography.getName(),wiringChoreographyClass);

			FAST.shell.showMessage("    Choreography <"+wiringChoreographyClass.getName()+"> loaded.");
			
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

	/* (non-Javadoc)
	 * @see es.us.lsi.tdg.fast.wiringChoreographys.ChoreographyFactory#getChoreographyByName(java.lang.String)
	 */
	/* (non-Javadoc)
	 * @see es.us.lsi.tdg.fast.core.choreographies.wiring.ChoreographyFactory#getByName(java.lang.String)
	 */
	public Choreography getByName(String wiringChoreographyName) throws UnknownChoreographyException{
		

		if (wiringChoreographyRegistry.containsKey(wiringChoreographyName)){
	
			Class<Choreography> wiringChoreographyClass = wiringChoreographyRegistry.get(wiringChoreographyName);
			
			try{
				
				Choreography wiringChoreography = (Choreography) wiringChoreographyClass.newInstance();
				return wiringChoreography;
				
			}catch(IllegalAccessException e){
				e.printStackTrace();
			}catch(InstantiationException e){
				e.printStackTrace();
			}
			
		
		}else throw new UnknownChoreographyException();
		
		return null;
	}

	public static ChoreographyFactory getInstance() {
		if(instance==null){
			instance = new BaseWiringChoreographyFactory();
		}
		return instance;
	}


	/* (non-Javadoc)
	 * @see es.us.lsi.tdg.fast.core.choreographies.wiring.ChoreographyFactory#loadChoreography(es.us.lsi.tdg.fast.core.choreographies.Choreography)
	 */
	public void loadChoreography(Choreography wiringChoreography) {
		this.loadChoreography(wiringChoreography.getClass());
	}

	/* (non-Javadoc)
	 * @see es.us.lsi.tdg.fast.core.choreographies.wiring.ChoreographyFactory#getByType(java.lang.String)
	 */
	public Choreography getByType(String type) {

		try{
		
			Collection<Class<Choreography>> wiringChoreographys = wiringChoreographyRegistry.values();
			
			Iterator<Class<Choreography>> iterator = wiringChoreographys.iterator();
			
			while(iterator.hasNext()){
				Class<Choreography> wiringChoreographyClass =  iterator.next();
				Choreography wiringChoreography = (Choreography) wiringChoreographyClass.newInstance();
				
				if(wiringChoreography.getType().equals(type))
					return wiringChoreography;
				
			}
		
		}catch(IllegalAccessException e){
			e.printStackTrace();
		}catch(InstantiationException e){
			e.printStackTrace();
		}
		
		
		return null;
	}
}