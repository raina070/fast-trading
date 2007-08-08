package es.us.lsi.tdg.fast.domains.fom;

import es.us.lsi.tdg.fast.core.dataModel.agreementPreferences.*;
import es.us.lsi.tdg.fast.core.dataModel.agreement.*;
import es.us.lsi.tdg.fast.core.dataModel.statement.IncompatibleAttributeException;
import es.us.lsi.tdg.fast.core.dataModel.statement.IntegerValue;

public class FOMAgreementOffer implements Comparable{

	protected FOMAgreement 				agreement;
	protected Assessment   				assess;
	protected FOMAgreementPreferences   agreementPref;
	
	public FOMAgreementOffer(Agreement agreement, AgreementPreferences agreePref) throws IncompatibleAttributeException{
		this.agreement 		= FOMSLATranslator.getFOMAgreement((BaseAgreement)agreement);
		this.agreementPref 	= new FOMAgreementPreferences(agreePref);
		this.assess 		= this.agreementPref.toBaseAgreementPreferences().assess(FOMSLATranslator.getAgreement(this.agreement));
		
	}
	
	public Assessment getAssess(){
		if (assess ==null){
			try {
				this.assess = agreementPref.toBaseAgreementPreferences().assess(FOMSLATranslator.getAgreement(agreement));
			} catch (IncompatibleAttributeException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return assess;
	}
	
	public int compareTo(Object o){
		int result=0;
		// TODO Change implementation, we should test all numeric compatible values, and throw a Exception in other case 
		if(o instanceof FOMAgreementOffer)
		{
			FOMAgreementOffer offer = (FOMAgreementOffer)o;
			result = offer.getAssess().compareTo(this.assess);
		}else
			throw new IllegalArgumentException();
		return result;
	}
	
	public String toString(){
		return this.agreement.toString();
	}
	
}
