package es.us.lsi.tdg.fast.domains.fom.dataModel;

import java.net.URI;

import es.us.lsi.tdg.fast.core.dataModel.agreement.CounterParty;

public class FOMCounterParty implements CounterParty {
	
	private String CPID;
	private URI DiscoveryEndPoint;
	private URI InformationEndPoint;
	private URI SelectionEndPoint;
	private URI AgreementMakingEndPoint;
	
	public FOMCounterParty(String cpid, URI discoveryEndPoint, URI informationEndPoint,
			URI selectionEndPoint, URI agreementMakingEndPoint) {
		super();
		CPID = cpid;
		
		DiscoveryEndPoint = discoveryEndPoint;
		InformationEndPoint = informationEndPoint;
		SelectionEndPoint = selectionEndPoint;
		AgreementMakingEndPoint = agreementMakingEndPoint;
	}
	
	public String getCPID() {
		return CPID;
	}

	public void setCPID(String cpid) {
		CPID = cpid;
	}

	public URI getInformationEndPoint() {
		return InformationEndPoint;
	}

	public void setInformationEndPoint(URI informationEndPoint) {
		InformationEndPoint = informationEndPoint;
	}

	public URI getSelectionEndPoint() {
		return SelectionEndPoint;
	}

	public void setSelectionEndPoint(URI selectionEndPoint) {
		SelectionEndPoint = selectionEndPoint;
	}

	public URI getAgreementMakingEndPoint() {
		return AgreementMakingEndPoint;
	}

	public void setAgreementMakingEndPoint(URI agreementMakingEndPoint) {
		AgreementMakingEndPoint = agreementMakingEndPoint;
	}

	public boolean equals(Object obj){
		
		boolean result = false;
			
		if(obj instanceof FOMCounterParty)
			result = ((FOMCounterParty) obj).getCPID() == this.CPID;
		
		return result;
	}
	
	public String toString(){
		return CPID;
	}

	public URI getDiscoveryEndPoint() {
		return DiscoveryEndPoint;
	}

	public void setDiscoveryEndPoint(URI discoveryEndPoint) {
		DiscoveryEndPoint = discoveryEndPoint;
	}
}
