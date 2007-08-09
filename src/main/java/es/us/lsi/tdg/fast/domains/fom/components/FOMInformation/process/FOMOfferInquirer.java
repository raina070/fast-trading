package es.us.lsi.tdg.fast.domains.fom.components.FOMInformation.process;


import java.util.HashSet;
import java.util.Set;
import java.util.Iterator;

import org.apache.axiom.om.OMAbstractFactory;
import org.apache.axiom.om.OMElement;
import org.apache.axiom.om.OMFactory;
import org.apache.axiom.om.OMNamespace;
import org.apache.axis2.Constants;
import org.apache.axis2.addressing.EndpointReference;
import org.apache.axis2.client.Options;
import org.apache.axis2.client.ServiceClient;

import es.us.lsi.tdg.fast.domains.fom.*;
import es.us.lsi.tdg.fast.domains.fom.dataModel.FOMOffer;

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
        
        return method;
    }
	
	
	public Set<FOMOffer> newCP(String ep){
		
		Set<FOMOffer> response = new HashSet<FOMOffer>();
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
            response = getInformation(result);    	
    

        } catch (Exception e) {
            e.printStackTrace();
        }
		return response;
	}
	
	public Set<FOMOffer> getInformation(OMElement Axiom){
		Set<FOMOffer> FOMInformation = new HashSet<FOMOffer>();
		OMElement root = Axiom.getFirstElement();
		OMElement FOMOfferRoot = root.getFirstElement();
		Iterator<OMElement>children = FOMOfferRoot.getChildElements();
		while (children.hasNext()){
			
			OMElement OfferX = (OMElement)children.next();
			Iterator<OMElement>offerchilds = OfferX.getChildElements();
			FOMOffer Offer = new FOMOffer();
			while (offerchilds.hasNext()){

				Offer.setCost(Double.parseDouble(offerchilds.next().getText()));
				Offer.setTimeInit(Integer.parseInt(offerchilds.next().getText()));
				Offer.setTimeEnd(Integer.parseInt(offerchilds.next().getText()));
				
			}
			FOMInformation.add(Offer);
		}
		return FOMInformation;
	}
	
	
}
