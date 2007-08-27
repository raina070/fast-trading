package es.us.lsi.tdg.fast.core.process.event;

public class FASTProcessEvent extends AbstractFASTEvent {

	FASTProcessEventType processEvent;
	
	public FASTProcessEvent(FASTProcessEventType processEvent, Object sender) {
		super(FASTEventType.COORDINATION, sender, processEvent.name());
		this.processEvent = processEvent;
	}

	public FASTProcessEvent(FASTProcessEventType processEvent, Object sender, String desc) {
		super(FASTEventType.COORDINATION, sender, desc);
		this.processEvent = processEvent;

	}
}
