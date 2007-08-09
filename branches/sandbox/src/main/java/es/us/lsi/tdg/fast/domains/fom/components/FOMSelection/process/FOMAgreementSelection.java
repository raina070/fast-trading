package es.us.lsi.tdg.fast.domains.fom.components.FOMSelection.process;

import es.us.lsi.tdg.fast.core.dataModel.agreementPreferences.*;
import es.us.lsi.tdg.fast.core.dataModel.agreement.*;

import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

public class FOMAgreementSelection {

	public static SortedSet<Agreement> FOMSortAgreement(Set<Agreement> myAgreements, AgreementPreferences agreePrefs){
		FOMAgreementComparator FOMComparator = new FOMAgreementComparator(agreePrefs);
		SortedSet<Agreement> result = new TreeSet<Agreement>(FOMComparator);
		for (Agreement agree:myAgreements){
			result.add(agree);
		}
		return result;
	}
}
