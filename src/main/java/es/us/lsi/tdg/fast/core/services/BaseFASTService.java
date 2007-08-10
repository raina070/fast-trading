package es.us.lsi.tdg.fast.core.services;

import javax.xml.ws.Endpoint;

import es.us.lsi.tdg.fast.core.component.Component;
import es.us.lsi.tdg.fast.core.trading.TradingProcess;


public class BaseFASTService implements FASTService{
		
	private TradingProcess tradingProcess = null;
	private Component component = null;
	private Endpoint endpoint = null;
	private Class serviceClass = null;

	public BaseFASTService(Component component) {
		this.tradingProcess = component.getTradingProcess();
		this.component = component;
    }

	public void setImplementation(Class serviceClass) {

		try {
			
			FASTServiceImplementation service = (FASTServiceImplementation) serviceClass.newInstance();
			service.setComponent(component);
	        endpoint = Endpoint.create(service);			
			this.serviceClass = serviceClass;
		} catch (InstantiationException e) {
			// 
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// 
			e.printStackTrace();
		}

    }
	
	public Endpoint getEndpoint() {
		return endpoint;
	}
	public TradingProcess getTradingProcess() {

		return tradingProcess;
	}

	public Class getServiceClass() {
		return serviceClass;
	}	
	
}
