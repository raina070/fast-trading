package es.us.lsi.tdg.fast.domains.fom.client;

import java.util.Set;
import java.util.SortedSet;
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
		Set<FOMOffer> result = new HashSet<FOMOffer>();
		result = Requirer.newCP("http://localhost:8080/axis2/services/FOMOfferInformant");
		
		Set <FOMOffer> Test2   = new HashSet<FOMOffer>();
		Set<Information> Test = new HashSet<Information>();
		
		
		for (FOMOffer Offer: result){
				Test.add(FOMOfferInformationTranslator.getInformation(Offer));
			
		}
		
		for (Information BIOffer: Test){
			Test2.add(FOMOfferInformationTranslator.getFOMOffer(BIOffer));
			
		}
		
		Set<FOMAgreement> Agreement = new HashSet<FOMAgreement>();
		Set<Agreement> BaseAgreements = new HashSet<Agreement>();
		
		
		FOMProposalBuilder Proposer = new FOMProposalBuilder();
		Agreement = Proposer.FOMOfferToAgreement(result,new FOMOffer(10,40,60));
		for (FOMAgreement AgreeTest: Agreement){
			BaseAgreements.add(FOMSLATranslator.getAgreement(AgreeTest));
			//System.out.println(AgreeTest);
		}
		
		
		for (Agreement AgreeTest: BaseAgreements){
			
			//System.out.println(Translator.getFOMAgreement(AgreeTest));
			
		}
		
		FOMAgreementSelection Test4 = new FOMAgreementSelection();
		for (Agreement agree:BaseAgreements){
			//System.out.println(FOMSLATranslator.getFOMAgreement(agree));
		}
		BaseAgreementPreferences myAgreePrefs;
		myAgreePrefs = Aux(10,40,60);
		SortedSet<Agreement> Test6 = Test4.FOMSortAgreement(BaseAgreements,myAgreePrefs);
		for (Agreement agree:Test6){
			System.out.println(FOMSLATranslator.getFOMAgreement(agree));
		}
		//FOMAgreementMakerDispatch Test5 = new FOMAgreementMakerDispatch();
		//Test5.dispatchAgreement(BaseAgreements, "http://localhost:8080/axis2/services/FOMAgreementMakerCommit");
		
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
