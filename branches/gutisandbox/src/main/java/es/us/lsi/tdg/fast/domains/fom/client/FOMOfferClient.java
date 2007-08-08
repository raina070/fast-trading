package es.us.lsi.tdg.fast.domains.fom.client;

import java.util.Set;
import java.util.HashSet;
import es.us.lsi.tdg.fast.core.dataModel.information.*;
import es.us.lsi.tdg.fast.core.dataModel.statement.*;
import es.us.lsi.tdg.fast.core.dataModel.agreement.*;
import es.us.lsi.tdg.fast.core.dataModel.agreementPreferences.BaseAgreementPreferences;
import es.us.lsi.tdg.fast.domains.fom.*;

public class FOMOfferClient {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws IncompatibleAttributeException {
		// TODO Auto-generated method stub
		FOMOfferInquirer Requirer = new FOMOfferInquirer();
		FOMOfferInformation result;
		result = Requirer.newCP("http://localhost:8080/axis2/services/FOMOfferInformant");
		FOMOfferInformationTranslator Adaptor = new FOMOfferInformationTranslator();
		FOMOfferInformation 		  Test2   = new FOMOfferInformation();
		HashSet<BaseInformation> Test = new HashSet<BaseInformation>();
		
		
		for (FOMOffer Offer: result.iterate()){
				Test.add(Adaptor.getInformation(Offer));
			
		}
		
		for (BaseInformation BIOffer: Test){
			Test2.add(Adaptor.getFOMOffer(BIOffer));
			
		}
		
		Set<FOMAgreement> Agreement = new HashSet<FOMAgreement>();
		Set<BaseAgreement> BaseAgreements = new HashSet<BaseAgreement>();
		
		FOMSLATranslator Translator = new FOMSLATranslator();
		FOMProposal Proposer = new FOMProposal();
		Agreement = Proposer.FOMOfferToAgreement(result.iterate(),new FOMOffer(10,40,60));
		for (FOMAgreement AgreeTest: Agreement){
			BaseAgreements.add(Translator.getAgreement(AgreeTest));
			//System.out.println(AgreeTest);
		}
		
		
		for (BaseAgreement AgreeTest: BaseAgreements){
			
			//System.out.println(Translator.getFOMAgreement(AgreeTest));
			
		}
		
		FOMAgreementSelection Test4 = new FOMAgreementSelection();
		BaseAgreementPreferences myAgreePrefs;
		myAgreePrefs = Aux(10,40,60);
		
		Test4.FOMAgreementSort(BaseAgreements,myAgreePrefs);
		
		
		FOMAgreementMakerDispatch Test5 = new FOMAgreementMakerDispatch();
		Test5.dispatchAgreement(BaseAgreements, "http://localhost:8080/axis2/services/FOMAgreementMakerCommit");
		
		//System.out.println(result);
		//System.out.println(Test2);
	}

	public static BaseAgreementPreferences Aux(int minTime, int maxTime,int costTest) throws IncompatibleAttributeException{
		
		//Mecanimo de Assessment (Implementado)
		FOMAssessementMechanism myAssessmentMechanism = new FOMAssessementMechanism();
		BaseAgreementPreferences result = new BaseAgreementPreferences(myAssessmentMechanism);
		IntegerValue costValue 		= new IntegerValue((int)costTest);
		IntegerValue costValueMin 	= new IntegerValue(-100);
		BaseAttribute Cost = new BaseAttribute("Cost",IntegerDomain.getInstance(), "price per time unit");
		BaseSortedDomainConstraint c = new BaseSortedDomainConstraint((ComparableValue)costValueMin,(ComparableValue)costValue,Cost,StatementType.SERVICE);
		IntegerValue timeInitValue = new IntegerValue(minTime);
		IntegerValue timeEndValue = new IntegerValue(maxTime);
		BaseAttribute time = new BaseAttribute("Time",IntegerDomain.getInstance(), "offer time");
		BaseSortedDomainConstraint tOffer = new BaseSortedDomainConstraint((ComparableValue)timeInitValue,(ComparableValue)timeEndValue, time,StatementType.SERVICE);
		result.addRequirement((Statement)c);
		result.addRequirement((Statement)tOffer);
		
			
		return result;
		
		
	}
	
}