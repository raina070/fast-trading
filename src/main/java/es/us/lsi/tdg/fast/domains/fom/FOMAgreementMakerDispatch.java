package es.us.lsi.tdg.fast.domains.fom;

import javax.xml.namespace.QName;
import org.apache.axiom.om.OMAbstractFactory;
import org.apache.axiom.om.OMElement;
import org.apache.axiom.om.OMFactory;
import org.apache.axiom.om.OMNamespace;
import org.apache.axis2.Constants;
import org.apache.axis2.addressing.EndpointReference;
import org.apache.axis2.client.Options;
import org.apache.axis2.client.ServiceClient;

import java.util.Iterator;
import java.util.Set;

import es.us.lsi.tdg.fast.core.dataModel.agreement.Agreement;
import es.us.lsi.tdg.fast.core.dataModel.agreement.BaseAgreement;
import es.us.lsi.tdg.fast.core.dataModel.statement.IncompatibleAttributeException;

public class FOMAgreementMakerDispatch {
	
	public void dispatchAgreement(Set<Agreement> agreements,String ep){
		int sent = 0;
		for (Agreement agree:agreements){
			if (sent==0){
				sendCommit(agree,ep);
				sent = 1;
			}
		}
	}
	
	public OMElement accept(OMElement elementSLA) {
	    
		elementSLA.build();
	    //elementTime.
	    elementSLA.detach();		
		OMElement symbolElement = elementSLA.getFirstElement();
	    /**
	    try {
			Agreement SLA = FOMSLATranslator.getAgreement(translateOMElement(elementSLA));
		} catch (IncompatibleAttributeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		
	    return symbolElement;
	}
	
	public OMElement reject(OMElement elementSLA) {
	    
		elementSLA.build();
	    elementSLA.detach();		
		OMElement symbolElement = elementSLA.getFirstElement();
	    
	    try {
			Agreement SLA = FOMSLATranslator.getAgreement(translateOMElement(elementSLA));
		} catch (IncompatibleAttributeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return symbolElement;
	}
		
	public static OMElement sendMethod(Agreement SLA, String myEndPoint) {
		
        OMFactory fac = OMAbstractFactory.getOMFactory();
        OMNamespace omNs = fac.createOMNamespace("http://axiom.service.mysample.samples/xsd", "tns");
        OMElement method = fac.createOMElement("commit", omNs);
        OMElement agreement = translateFOMSLA(FOMSLATranslator.getFOMAgreement((BaseAgreement)SLA),fac,omNs);
        OMElement endPoint = fac.createOMElement("ep", omNs);
        endPoint.addChild(fac.createOMText(endPoint, myEndPoint));
        method.addChild(agreement);   
        method.addChild(endPoint);
        return method;
    }
	
	
	public void sendCommit(Agreement SLA, String ep){
		EndpointReference targetEPR = 
	        new EndpointReference(ep);
		try {
			String myEndPoint = "http://localhost:8080/axis2/services/FOMAgreementMakerDispatch";
            OMElement commit = sendMethod(SLA, myEndPoint);
            Options options = new Options();
            options.setTo(targetEPR);
            options.setTransportInProtocol(Constants.TRANSPORT_HTTP);
            ServiceClient sender = new ServiceClient();
            sender.setOptions(options);
            sender.fireAndForget(commit);
            Thread.sleep(500);
            //OMElement response = sender.sendReceive(accept);
            
        } catch (Exception e) {
            e.printStackTrace();
        }		
        
	}

	public static OMElement translateFOMSLA(FOMAgreement agree,OMFactory fac, OMNamespace omNs){
	
		OMElement result = fac.createOMElement("Agreement", omNs);
	
		OMElement Cost = fac.createOMElement("Cost", omNs);
		OMElement timeInit = fac.createOMElement("Time", omNs);
		
		Cost.addChild(fac.createOMText(Double.toString(agree.getCost())));
		timeInit.addChild(fac.createOMText(Integer.toString(agree.getTime())));
		result.addChild(Cost);
		result.addChild(timeInit);
		
		return result;
	}
	
	public static FOMAgreement translateOMElement(OMElement agree){
		
		Iterator<OMElement>agreeChild = agree.getChildElements();
		int time = 0;
		double cost = 0;
		while (agreeChild.hasNext()){
			cost = Double.parseDouble(agreeChild.next().getText());
			time = Integer.parseInt(agreeChild.next().getText());
		}
		FOMAgreement result = new FOMAgreement(time,cost);
		
		return result;
	}
}
