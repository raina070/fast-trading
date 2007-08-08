package es.us.lsi.tdg.fast.core.choreographies;

import java.util.Set;

public interface Choreography {

	public String getName();

	public String getType();

	public Set<String> getParticipants();

}