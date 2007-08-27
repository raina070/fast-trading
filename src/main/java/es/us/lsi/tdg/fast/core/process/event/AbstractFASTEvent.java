package es.us.lsi.tdg.fast.core.process.event;

public abstract class AbstractFASTEvent implements FASTEvent {
	private FASTEventType type;
	private Object sender;
	private String desc;
	
	public AbstractFASTEvent(FASTEventType type, Object sender, String desc) {
		super();
		this.type = type;
		this.sender = sender;
		this.desc = desc;
	}

	/* (non-Javadoc)
	 * @see es.us.lsi.tdg.fast.core.process.event.FASTEventf#getType()
	 */
	public FASTEventType getType() {
		return type;
	}

	/* (non-Javadoc)
	 * @see es.us.lsi.tdg.fast.core.process.event.FASTEventf#setType(es.us.lsi.tdg.fast.core.process.event.FASTEventType)
	 */
	public void setType(FASTEventType type) {
		this.type = type;
	}

	/* (non-Javadoc)
	 * @see es.us.lsi.tdg.fast.core.process.event.FASTEventf#getSender()
	 */
	public Object getSender() {
		return sender;
	}

	/* (non-Javadoc)
	 * @see es.us.lsi.tdg.fast.core.process.event.FASTEventf#setSender(java.lang.Object)
	 */
	public void setSender(Object sender) {
		this.sender = sender;
	}

	/* (non-Javadoc)
	 * @see es.us.lsi.tdg.fast.core.process.event.FASTEventf#getDesc()
	 */
	public String getDesc() {
		return desc;
	}

	/* (non-Javadoc)
	 * @see es.us.lsi.tdg.fast.core.process.event.FASTEventf#setDesc(java.lang.String)
	 */
	public void setDesc(String desc) {
		this.desc = desc;
	}
	
}
