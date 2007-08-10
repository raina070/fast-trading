package es.us.lsi.tdg.fast.core.services;

import es.us.lsi.tdg.fast.core.trading.TradingProcess;

public interface FASTServer {

	public void publishService(FASTService service);

	public void stop();

}