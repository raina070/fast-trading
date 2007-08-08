package es.us.lsi.tdg.fast.core.choreographies;

import java.util.HashSet;
import java.util.Set;


public class AbstractChoreography implements Choreography {

	protected String name;
	protected String type;
	protected Set<String> participants;

	public AbstractChoreography() {
		this.name = "AbstractChoreography";
		this.type = "AbstractChoreography";
		this.participants = new HashSet<String>();
	}
	
	public AbstractChoreography(String name, String type) {
		this.name = name;
		this.type = type;
		this.participants = new HashSet<String>();
	}

	/* (non-Javadoc)
	 * @see es.us.lsi.tdg.fast.core.wiring.choreographies.Choreography#getName()
	 */
	public String getName() {
		return name;
	}

	/* (non-Javadoc)
	 * @see es.us.lsi.tdg.fast.core.wiring.choreographies.Choreography#getType()
	 */
	public String getType() {
		return type;
	}

	/* (non-Javadoc)
	 * @see es.us.lsi.tdg.fast.core.wiring.choreographies.Choreography#getParticipants()
	 */
	public Set<String> getParticipants() {
		return participants;
	}
	
	
}
