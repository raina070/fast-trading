package es.us.lsi.tdg.fast.domains.fom.components.FOMDiscovery.process;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

import javax.xml.namespace.QName;

import es.us.lsi.tdg.fast.FAST;
import es.us.lsi.tdg.fast.core.roles.AbstractControllableProcess;
import es.us.lsi.tdg.fast.domains.fom.components.FOMDiscovery.FOMDiscovery;
import es.us.lsi.tdg.fast.domains.fom.components.fomdiscovery.services.DiscoveryEP;
import es.us.lsi.tdg.fast.domains.fom.components.fomdiscovery.services.FOMDiscoveryService;
import es.us.lsi.tdg.fast.domains.fom.dataModel.FOMCounterParty;


/**
 * 
 * @author Pablo Fernandez Montes
 * @author José Antonio Parejo Maestre
 *
 */
public class FOMAdvertiserProcess extends AbstractControllableProcess{
	
	
	private FOMDiscovery discoveryComponent;
	
	public FOMAdvertiserProcess() {
		this("FOMTracker");				
	}

	public FOMAdvertiserProcess(String threadName)
	{
		super(threadName);
	}
		
	public FOMAdvertiserProcess(FOMDiscovery discoveryComponent) {
		this.discoveryComponent = discoveryComponent;
	}

	
	private FOMCounterParty providerEPs(){
		FOMCounterParty provider = null;
		int port = Integer.parseInt(FAST.properties.get("serverPort"));
		String domainRoleName = FAST.currentDomainRole.getName();
		
		try {
			URI infoEP = new URI("http://"+ FAST.properties.get("serverIP")+":"+port+"/"+domainRoleName+"/InformantServiceImplementation");
			URI selEP = new URI("http://"+ FAST.properties.get("serverIP")+":"+port+"/"+domainRoleName+"/CollectorServiceImplementation");
			URI amEP = new URI("http://"+ FAST.properties.get("serverIP")+":"+port+"/"+domainRoleName+"/AgreementMakingServiceImplementation");
			provider = new FOMCounterParty("TestProvider",null,infoEP,selEP,amEP); 
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		return provider;
	}
	
	public  void  start()
	{
		try {
			URL url = new URL(FAST.properties.get("discoveryEndPoint"));
			QName qname = new QName("http://services.FOMDiscovery.components.fom.domains.fast.tdg.lsi.us.es/", "FOMDiscoveryService");
				
			FOMDiscoveryService service;
			DiscoveryEP port;
			
			service = new FOMDiscoveryService(url,qname);
			
			port = service.getDiscoveryEPPort();
			
			FOMCounterParty provider = providerEPs();

			
			port.advertise(provider.getCPID()
							,provider.getInformationEndPoint().toString()
							,provider.getSelectionEndPoint().toString()
							,provider.getAgreementMakingEndPoint().toString());
				
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}

		super.start();
		
	}

	@Override
	protected void run() {
		// TODO Auto-generated method stub
		
	}
	
}
