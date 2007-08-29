package es.us.lsi.tdg.fast.domains.fom.components.FOMSelection.process;

import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

import es.us.lsi.tdg.fast.core.dataModel.agreement.Proposal;
import es.us.lsi.tdg.fast.core.dataModel.agreementPreferences.AgreementPreferences;
import es.us.lsi.tdg.fast.domains.fom.dataModel.FOMProposalComparator;

public class FOMProposalSelection {

	@SuppressWarnings("unchecked")
	public static SortedSet<Proposal> FOMSortAgreement(Set<Proposal> proposals, AgreementPreferences agreePrefs){
		FOMProposalComparator FOMComparator = new FOMProposalComparator(agreePrefs);
		SortedSet<Proposal> result = new TreeSet<Proposal>(FOMComparator);
		for (Proposal proposal:proposals){
			
			result.add(proposal);
		}
		return result;
	}
}
