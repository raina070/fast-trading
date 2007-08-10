package es.us.lsi.tdg.fast.domains.fom.components.FOMSelection.process;

import es.us.lsi.tdg.fast.core.dataModel.agreementPreferences.*;
import es.us.lsi.tdg.fast.core.dataModel.agreement.*;
import es.us.lsi.tdg.fast.domains.fom.dataModel.FOMProposalComparator;

import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

public class FOMProposalSelection {

	public static SortedSet<Proposal> FOMSortAgreement(Set<Proposal> myAgreements, AgreementPreferences agreePrefs){
		FOMProposalComparator FOMComparator = new FOMProposalComparator(agreePrefs);
		SortedSet<Proposal> result = new TreeSet<Proposal>(FOMComparator);
		for (Proposal agree:myAgreements){
			
			result.add(agree);
		}
		return result;
	}
}
