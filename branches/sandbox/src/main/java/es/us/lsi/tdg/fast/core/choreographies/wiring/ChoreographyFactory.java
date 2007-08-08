package es.us.lsi.tdg.fast.core.choreographies.wiring;

import es.us.lsi.tdg.fast.core.choreographies.Choreography;
import es.us.lsi.tdg.fast.core.choreographies.UnknownChoreographyException;

public interface ChoreographyFactory {

	@SuppressWarnings("unchecked")
	public void loadChoreography(String className);

	@SuppressWarnings("unchecked")
	public void loadChoreography(Class wiringChoreographyClass);

	/* (non-Javadoc)
	 * @see es.us.lsi.tdg.fast.wiringChoreographys.ChoreographyFactory#getChoreographyByName(java.lang.String)
	 */
	public Choreography getByName(String wiringChoreographyName)
			throws UnknownChoreographyException;

	public void loadChoreography(Choreography wiringChoreography);

	public Choreography getByType(String type);

}