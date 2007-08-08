package es.us.lsi.tdg.fast.domains.fom;

import es.us.lsi.tdg.fast.core.dataModel.agreement.*;
import es.us.lsi.tdg.fast.core.dataModel.statement.IncompatibleAttributeException;

import org.apache.axiom.om.OMAbstractFactory;
import org.apache.axiom.om.OMElement;
import org.apache.axiom.om.OMFactory;
import org.apache.axiom.om.OMNamespace;
import org.apache.axis2.Constants;
import org.apache.axis2.addressing.EndpointReference;
import org.apache.axis2.client.Options;
import org.apache.axis2.client.ServiceClient;

public class FOMAgreementMakerCommit {
	public OMElement commit(OMElement elementSLA) {
	    
		elementSLA.build();
	    //elementTime.
	    elementSLA.detach();		
	    OMElement symbolElement = elementSLA.getFirstElement();
	    
	    try {
			BaseAgreement SLA = FOMSLATranslator.getAgreement(FOMAgreementMakerDispatch.translateOMElement(elementSLA));
			//This must be taken from OMElement
			String ep="http://";
		    //sendAccept((Agreement)SLA,ep);
		} catch (IncompatibleAttributeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return elementSLA;
	    
	}

		
	public static OMElement sendMethod(Agreement SLA, String accept) {
		
		OMFactory fac = OMAbstractFactory.getOMFactory();
        OMNamespace omNs = fac.createOMNamespace("http://axiom.service.mysample.samples/xsd", "tns");
        OMElement method = fac.createOMElement("commit", omNs);
        OMElement value = fac.createOMElement("agreement", omNs);
        value.addChild(FOMAgreementMakerDispatch.translateFOMSLA(FOMSLATranslator.getFOMAgreement((BaseAgreement)SLA),fac,omNs));
        method.addChild(value);        
        
       
        return method;
    }
	
	
	public void sendAccept(Agreement SLA, String ep){
		EndpointReference targetEPR = 
	        new EndpointReference(ep);
		try {
            OMElement accept = sendMethod(SLA,"accept");
            Options options = new Options();
            options.setTo(targetEPR);
            options.setTransportInProtocol(Constants.TRANSPORT_HTTP);
            ServiceClient sender = new ServiceClient();
            sender.setOptions(options);
            sender.sendReceive(accept);
        } catch (Exception e) {
            e.printStackTrace();
        }		
	}
	
	public void sendReject(Agreement SLA, String ep){
		EndpointReference targetEPR = 
	        new EndpointReference(ep);
		try {
            OMElement accept = sendMethod(SLA,"reject");
            Options options = new Options();
            options.setTo(targetEPR);
            options.setTransportInProtocol(Constants.TRANSPORT_HTTP);
            ServiceClient sender = new ServiceClient();
            sender.setOptions(options);
            sender.sendReceive(accept);
        } catch (Exception e) {
            e.printStackTrace();
        }		
	}
	
}
