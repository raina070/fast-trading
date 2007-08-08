package es.us.lsi.tdg.fast.domains.fom;

import es.us.lsi.tdg.fast.core.dataModel.agreementPreferences.*;
import es.us.lsi.tdg.fast.core.dataModel.agreement.*;
import es.us.lsi.tdg.fast.core.dataModel.statement.BaseAttribute;
import es.us.lsi.tdg.fast.core.dataModel.statement.BaseSimpleConstraint;
import es.us.lsi.tdg.fast.core.dataModel.statement.BaseSortedDomainConstraint;
import es.us.lsi.tdg.fast.core.dataModel.statement.ComparableValue;
import es.us.lsi.tdg.fast.core.dataModel.statement.Constraint;
import es.us.lsi.tdg.fast.core.dataModel.statement.IncompatibleAttributeException;
import es.us.lsi.tdg.fast.core.dataModel.statement.IntegerDomain;
import es.us.lsi.tdg.fast.core.dataModel.statement.IntegerValue;
import es.us.lsi.tdg.fast.core.dataModel.statement.Statement;
import es.us.lsi.tdg.fast.core.dataModel.statement.StatementType;
import es.us.lsi.tdg.fast.core.dataModel.statement.Value;


import java.util.Set;
import java.util.HashSet;
import java.util.SortedSet;
import java.util.TreeSet;

public class FOMAgreementSelection {

	public SortedSet<FOMAgreementOffer> FOMAgreementSort(Set<Agreement> myAgreements,AgreementPreferences myAgreementPreferences){
		SortedSet<FOMAgreementOffer> myFOMOffer = new TreeSet<FOMAgreementOffer>();
		for (Agreement Agree:myAgreements){	
			try {
				myFOMOffer.add(new FOMAgreementOffer(Agree, myAgreementPreferences));
			} catch (IncompatibleAttributeException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		for (FOMAgreementOffer Offer:myFOMOffer){
			
		}
		return myFOMOffer;	
	}
	
	public SortedSet<Agreement> FOMSortAgreement(Set<Agreement> myAgreements, AgreementPreferences agreePrefs){
		FOMAgreementComparator FOMComparator = new FOMAgreementComparator(agreePrefs);
		SortedSet<Agreement> result = new TreeSet<Agreement>(FOMComparator);
		for (Agreement agree:myAgreements){
			result.add(agree);
		}
		return result;
	}
}
