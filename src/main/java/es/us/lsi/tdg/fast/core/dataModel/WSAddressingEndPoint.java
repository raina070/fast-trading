package es.us.lsi.tdg.fast.core.dataModel;

import java.net.URISyntaxException;

/**
 * @author  Pablo Fernández Montes
 * @author  José Antonio Parejo Maestre
 */
public final class WSAddressingEndPoint implements EndPoint{
	private java.net.URI address;
	AbstractDataComponent parameters;
	AbstractDataComponent properties;	
	public  WSAddressingEndPoint(String uri) throws URISyntaxException
	{
		address=new java.net.URI(uri);		
	}
	
	public java.net.URI getAddress()
	{
		return address;
	}
	
}
