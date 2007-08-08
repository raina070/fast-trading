package es.us.lsi.tdg.fast.domains.fom;

import java.util.Iterator;
import java.util.Set;
import java.util.HashSet;

import org.apache.axiom.om.OMElement;
import org.apache.axiom.om.OMFactory;
import org.apache.axiom.om.OMNamespace;


public class FOMOfferInformation {

	public Set<FOMOffer> FOMInformation;
	
	public FOMOfferInformation(){
		FOMInformation = new HashSet<FOMOffer>();
	}
	public void add(FOMOffer offer){
		this.FOMInformation.add(offer);
	}
	
	public Set<FOMOffer> iterate(){
		return FOMInformation;
	}
	
	public String toString(){
		String result = null;
		result = "FOMInformation{";
		for (FOMOffer Information: FOMInformation){
			result = result + Information.toString();
			
		}
		result = result + "}";
		return result;
	}
	
	public FOMOfferInformation(OMElement Axiom){
		FOMInformation = new HashSet<FOMOffer>();
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
			//FOMOffer Offer = OfferX.get
			FOMInformation.add(Offer);
		}
		
	}
	
	public OMElement toAxiom(OMFactory fac, OMNamespace omNs){
		OMElement result = fac.createOMElement("FOMOfferInformation", omNs);
		
		for (FOMOffer Information: FOMInformation){
			OMElement Offer = fac.createOMElement("Offer", omNs);
			OMElement Cost = fac.createOMElement("Cost", omNs);
			OMElement timeInit = fac.createOMElement("TimeInit", omNs);
			OMElement timeEnd = fac.createOMElement("TimeEnd", omNs);
			Cost.addChild(fac.createOMText(Double.toString(Information.getCost())));
			timeInit.addChild(fac.createOMText(Integer.toString(Information.getTimeInit())));
			timeEnd.addChild(fac.createOMText(Integer.toString(Information.getTimeEnd())));
			Offer.addChild(Cost);
			Offer.addChild(timeInit);
			Offer.addChild(timeEnd);
			result.addChild(Offer);
		}
		
		return result;
	}
}
