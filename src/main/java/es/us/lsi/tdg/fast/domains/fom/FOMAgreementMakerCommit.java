package es.us.lsi.tdg.fast.domains.fom;

import java.io.FileOutputStream;
import java.util.Iterator;

import es.us.lsi.tdg.fast.core.dataModel.agreement.*;
import es.us.lsi.tdg.fast.core.dataModel.statement.IncompatibleAttributeException;

import javax.xml.namespace.QName;
import org.apache.axiom.om.OMAbstractFactory;
import org.apache.axiom.om.OMElement;
import org.apache.axiom.om.OMFactory;
import org.apache.axiom.om.OMNamespace;
import org.apache.axiom.om.OMNamespace;
import org.apache.axis2.Constants;
import org.apache.axis2.addressing.EndpointReference;
import org.apache.axis2.client.Options;
import org.apache.axis2.client.ServiceClient;

public class FOMAgreementMakerCommit {
	public OMElement commit(OMElement elementSLA) {
	    
		elementSLA.build();
	    elementSLA.detach();		
	    Agreement SLA = null;
	    OMElement symbolElement=null; 
	    String endPoint = ""; 		
	    try {
	    	Iterator<OMElement>agreeChild = elementSLA.getChildElements();
			while (agreeChild.hasNext()){
				symbolElement = agreeChild.next();
			    endPoint=agreeChild.next().getText();
	    		SLA = FOMSLATranslator.getAgreement(FOMAgreementMakerDispatch.translateOMElement(symbolElement));
			
	    	}
		    sendAccept((Agreement)SLA,endPoint);
		} catch (IncompatibleAttributeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return symbolElement;
	    
	}

		
	public static OMElement sendMethod(Agreement SLA) {
		
		OMFactory fac = OMAbstractFactory.getOMFactory();
        OMNamespace omNs = fac.createOMNamespace("http://axiom.service.mysample.samples/xsd", "tns");
        OMElement method = fac.createOMElement("accept", omNs);
        OMElement value =  fac.createOMElement("test",omNs);
        value.addChild(fac.createOMText(value, "eso"));
        //OMElement value =  FOMAgreementMakerDispatch.translateFOMSLA(FOMSLATranslator.getFOMAgreement(SLA),fac,omNs);
        method.addChild(value);        
        return method;
    }
	
	
	public void sendAccept(Agreement SLA, String ep){
		EndpointReference targetEPR = 
	        new EndpointReference(ep);
		try {
            OMElement accept = sendMethod(SLA);
            Options options = new Options();
            options.setTo(targetEPR);
            options.setTransportInProtocol(Constants.TRANSPORT_HTTP);
            ServiceClient sender = new ServiceClient();
            sender.setOptions(options);
            //System.out.println(ep);
            
            //OMElement response = sender.sendReceive(accept);
            sender.fireAndForget(accept);
            Thread.sleep(500);
        } catch (Exception e) {
            e.printStackTrace();
        }		
	}
		
}
