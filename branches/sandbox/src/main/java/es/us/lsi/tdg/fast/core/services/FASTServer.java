package es.us.lsi.tdg.fast.core.services;


public interface FASTServer {

	public void publishService(FASTService service);

	public void stop();

	public void unpublishService(FASTService service);

}