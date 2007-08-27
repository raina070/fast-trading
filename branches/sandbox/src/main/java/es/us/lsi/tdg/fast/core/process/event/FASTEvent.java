package es.us.lsi.tdg.fast.core.process.event;

public interface FASTEvent {

	public FASTEventType getType();

	public void setType(FASTEventType type);

	public Object getSender();

	public void setSender(Object sender);

	public String getDesc();

	public void setDesc(String desc);

}