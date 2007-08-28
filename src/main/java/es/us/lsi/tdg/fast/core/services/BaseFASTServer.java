package es.us.lsi.tdg.fast.core.services;

import java.io.IOException;
import java.net.BindException;
import java.net.InetSocketAddress;
import java.util.HashMap;
import java.util.Map;

import javax.xml.ws.Endpoint;


import com.sun.net.httpserver.HttpContext;
import com.sun.net.httpserver.HttpServer;

import es.us.lsi.tdg.fast.FAST;
import es.us.lsi.tdg.fast.core.process.event.EventBroker;
import es.us.lsi.tdg.fast.core.process.event.FASTEventType;
import es.us.lsi.tdg.fast.core.process.event.GenericFASTEvent;
import es.us.lsi.tdg.fast.core.shell.command.BaseExitCommand;
import es.us.lsi.tdg.fast.core.shell.command.ExitCommand;
import es.us.lsi.tdg.fast.core.trading.TradingProcess;

public class BaseFASTServer implements FASTServer {

	static FASTServer instance = null;
	
	
	public static FASTServer getInstance(){
		if(instance == null){
			instance = new BaseFASTServer();
		}
		return instance;	
	}

	
	private HttpServer httpServer;
	private Map<String,HttpContext> httpContexts;
//	private Map<HttpContext,Endpoint> publishedEndpoints;
	
	private BaseFASTServer(){
		int serverPort = Integer.parseInt((String)FAST.properties.get((String)"serverPort"));
		FAST.shell.showMessage("Initiating Server on Port " + serverPort+" ... ");
		try {
			
			httpServer = HttpServer.create(new InetSocketAddress(serverPort), 5);
			httpServer.start();
			FAST.shell.showMessage("Server Online.");
			httpContexts = new HashMap<String,HttpContext>();
				
		} catch (BindException e) {
			FAST.exit("ERROR: Port "+serverPort+" already in use.");
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	
	private String generateBaseContextName(TradingProcess tradingProcess){
		return "/"+FAST.currentDomainRole.getName();
		//TODO: future: return "Context"+tradingProcess.getPID();
	}
/*	
	public void createContext(TradingProcess tradingProcess) {
		String contextName = generateContextName(tradingProcess);

		if(httpContexts.containsKey(contextName)){
			httpServer.removeContext(contextName);
		}

		httpContexts.put(contextName, httpServer.createContext(contextName));
		
	}
*/
	
	public void publishService(FASTService service){
		Endpoint endpoint = service.getEndpoint();
		Class serviceClass = service.getServiceClass();
		
		String serviceCanonicalClassName = serviceClass.getName(); 
		int lastPoint = serviceCanonicalClassName.lastIndexOf('.');
		String serviceClassName = serviceCanonicalClassName.substring(lastPoint+1);
		
		String contextName = generateBaseContextName(service.getTradingProcess())+"/"+serviceClassName;
		HttpContext context;
		//EnvelopeLoggingSOAPHandler logger = new EnvelopeLoggingSOAPHandler();
		//logger.bind(endpoint);
		if(httpContexts.containsKey(contextName)){
			context = httpContexts.get("contextName");
		}else{
			context = httpServer.createContext(contextName);
			httpContexts.put(contextName, context);
		}
		endpoint.publish(context);

		EventBroker.event(new GenericFASTEvent(FASTEventType.OTHER,endpoint,"Endpoint <"+contextName+"> published"));
	}

	public void unpublishService(FASTService service){
		Endpoint endpoint = service.getEndpoint();
		Class serviceClass = service.getServiceClass();
		
		String serviceCanonicalClassName = serviceClass.getName(); 
		int lastPoint = serviceCanonicalClassName.lastIndexOf('.');
		String serviceClassName = serviceCanonicalClassName.substring(lastPoint+1);
		
		String contextName = generateBaseContextName(service.getTradingProcess())+"/"+serviceClassName;
		
		
		if(httpContexts.containsKey(contextName) && endpoint.isPublished()){
			HttpContext context = httpContexts.get("contextName");

			endpoint.stop();

			// not necessary
			// httpServer.removeContext(contextName);
			// httpContexts.remove(contextName);
			
			EventBroker.event(new GenericFASTEvent(FASTEventType.OTHER,service,"Endpoint unpublished"));
			
		}

	}
	
	public void stop() {
		httpServer.stop(0);
	}
	
}
