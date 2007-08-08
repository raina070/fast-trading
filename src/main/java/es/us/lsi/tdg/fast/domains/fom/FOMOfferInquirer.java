package es.us.lsi.tdg.fast.domains.fom;

import org.apache.axiom.om.OMAbstractFactory;
import org.apache.axiom.om.OMElement;
import org.apache.axiom.om.OMFactory;
import org.apache.axiom.om.OMNamespace;
import org.apache.axis2.Constants;
import org.apache.axis2.addressing.EndpointReference;
import org.apache.axis2.client.Options;
import org.apache.axis2.client.ServiceClient;

import es.us.lsi.tdg.fast.domains.fom.*;

/**
 * 
 * @author Antonio Manuel
 *This class is the Informant Web Service Consumer
 */

public class FOMOfferInquirer {
	
	
	public static OMElement getOfferInformation() {
		
        OMFactory fac = OMAbstractFactory.getOMFactory();
        OMNamespace omNs = fac.createOMNamespace("http://axiom.service.mysample.samples/xsd", "tns");
        OMElement method = fac.createOMElement("getOfferInformation", omNs);
        OMElement value = fac.createOMElement("number", omNs);
        
        
        value.addChild(fac.createOMText(value, Integer.toString(0)));
        method.addChild(value);
        //Future Fix
        /**
        OMElement value2 = fac.createOMElement("factor", omNs);
        value2.addChild(fac.createOMText(value, Double.toString(1)));
        method.addChild(value2);
        */
        return method;
    }
	
	
	public FOMOfferInformation newCP(String ep){
		FOMOfferInformation response = null;
	
		EndpointReference targetEPR = 
	        new EndpointReference(ep);
	
		try {
        	
            OMElement getOfferInformation = getOfferInformation();
            
            Options options = new Options();
            options.setTo(targetEPR);
            options.setTransportInProtocol(Constants.TRANSPORT_HTTP);

            ServiceClient sender = new ServiceClient();
            sender.setOptions(options);

            
            OMElement result = sender.sendReceive(getOfferInformation);
            response = new FOMOfferInformation(result);    	
    

        } catch (Exception e) {
            e.printStackTrace();
        }
		return response;
	}
}
