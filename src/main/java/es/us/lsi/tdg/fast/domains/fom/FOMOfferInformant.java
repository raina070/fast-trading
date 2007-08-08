package es.us.lsi.tdg.fast.domains.fom;

import javax.xml.stream.XMLStreamException;
import org.apache.axiom.om.OMAbstractFactory;
import org.apache.axiom.om.OMElement;
import org.apache.axiom.om.OMFactory;
import org.apache.axiom.om.OMNamespace;


public class FOMOfferInformant {
	
	private double 	c1=50,c2=20,c3=10;

	public OMElement getOfferInformation(OMElement elementTime) {
	    
		elementTime.build();
	    //elementTime.
	    elementTime.detach();		
		OMElement symbolElementTime = elementTime.getFirstElement();
	    String stringTime = symbolElementTime.getText();
	   
		int tInit 	= Integer.parseInt(stringTime);
		
		int tFull = 60;
		int tReal = tFull - tInit;
		double factor = (double)tFull/tReal;
		
		int t1 = tReal/3;
		int t2 = (2*tReal/3);
		
		FOMOfferInformation result = new FOMOfferInformation();
		
		FOMOffer Offer1 = new FOMOffer(tInit,t1+tInit, factor*this.c1);
		FOMOffer Offer2 = new FOMOffer(t1+tInit,t2+tInit, factor*this.c2);
		FOMOffer Offer3 = new FOMOffer(t2+tInit,tFull, factor*this.c3);
		
		result.add(Offer1);
		result.add(Offer2);
		result.add(Offer3);
		
	    OMFactory fac = OMAbstractFactory.getOMFactory();
	    OMNamespace omNs =
	    		fac.createOMNamespace("http://mysample.samples/xsd", "ns");
	    OMElement method = fac.createOMElement("getOfferInformationResponse", omNs);
	    OMElement value = fac.createOMElement("return", omNs);
	    value.addChild(result.toAxiom(fac,omNs));
	    method.addChild(value);
	    return method;

	}

	
}
