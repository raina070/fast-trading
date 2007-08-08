package es.us.lsi.tdg.fast.domains.fom.components.FOMDiscovery.process;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashSet;
import java.util.Set;

import es.us.lsi.tdg.fast.core.dataModel.agreement.CounterParty;
import es.us.lsi.tdg.fast.core.roles.AbstractControllableProcess;
import es.us.lsi.tdg.fast.core.roles.information.Inquirer;
import es.us.lsi.tdg.fast.domains.fom.dataModel.FOMCounterParty;

/**
 * 
 * @author Pablo Fernandez Montes
 * @author José Antonio Parejo Maestre
 *
 */
public class FOMTrackerProcess extends AbstractControllableProcess{
	
	private Inquirer inquirer;
	Set<CounterParty> FOMProviders;
	
	
	public FOMTrackerProcess(Inquirer inquirer) {
		this("FOMTracker",inquirer);				
	}

	public FOMTrackerProcess(String threadName,Inquirer inquirer)
	{
		super(threadName);
		this.inquirer = inquirer;
	}
		
	private FOMCounterParty testProvider(){
		FOMCounterParty provider = null;
		try {
			URI infoEP = new URI("http://localhost:8080/FOMProviderTrader/Information/Informant");
			URI selEP = new URI("http://localhost:8080/FOMProviderTrader/Selection/Collector");
			URI amEP = new URI("http://localhost:8080/FOMProviderTrader/AgreementMaking/AgreementHandler");
			provider = new FOMCounterParty("TestProvider",null,infoEP,selEP,amEP); 
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		return provider;
	}
	
	protected  void  run()
	{
		FOMProviders= new HashSet<CounterParty>();		
		FOMProviders.add(testProvider());
		
		inquirer.potentialCounterParties(FOMProviders);
		stop();
		
	}
}
