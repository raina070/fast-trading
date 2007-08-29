package es.us.lsi.tdg.fast.core.services;


import javax.xml.ws.Endpoint;
import es.us.lsi.tdg.fast.core.trading.TradingProcess;


@SuppressWarnings("unchecked")
public interface FASTService {

	
	public TradingProcess getTradingProcess();
	public Endpoint getEndpoint();

	public void setImplementation(Class serviceClass);
	public Class getServiceClass();
}
