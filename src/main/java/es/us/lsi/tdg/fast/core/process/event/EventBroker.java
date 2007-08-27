package es.us.lsi.tdg.fast.core.process.event;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import es.us.lsi.tdg.fast.FAST;

public class EventBroker {

	private static Map<Object,Set<EventSubscriber>> subscriptions;
	
	static {
		
		subscriptions = new HashMap<Object,Set<EventSubscriber>>();
		
	}

	
	public static void subscribe(EventSubscriber subscriptor, Object o){
		Set<EventSubscriber> subscriptors;
		if(!subscriptions.containsKey(o)){
			subscriptors = new HashSet<EventSubscriber>();
		}else{
			subscriptors = subscriptions.get(o);
		}
		subscriptors.add(subscriptor);
		subscriptions.put(o, subscriptors);
		
	}

	public static void event(Object o, FASTEvent event){
		Set<EventSubscriber> subscriptors;
		FAST.shell.showMessage("## Event <"+event.getType().name()+"("+event.getDesc()+")> on object <"+o+"> ##");
		
		if(subscriptions.containsKey(o)){
			subscriptors = subscriptions.get(o);
			for(EventSubscriber subscriptor : subscriptors){
				subscriptor.newEvent(event);		
			}
		}		
	}
	
	public static void event(FASTEvent event){
		event(event.getSender(),event);
	}
	
}
