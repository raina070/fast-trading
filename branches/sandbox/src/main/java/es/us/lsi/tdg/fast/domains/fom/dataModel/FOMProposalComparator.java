package es.us.lsi.tdg.fast.domains.fom.dataModel;

import java.util.Comparator;
import es.us.lsi.tdg.fast.core.dataModel.agreementPreferences.*;
import es.us.lsi.tdg.fast.core.dataModel.agreement.*;
import es.us.lsi.tdg.fast.FAST;


public class FOMProposalComparator implements Comparator {

	private AgreementPreferences agreePrefs;
	
	public int compare(Object o1, Object o2){
		AssessmentMechanism assessMech = agreePrefs.getAssessmentMechanism();
		
		Assessment a1 = assessMech.assess(agreePrefs,(Agreement)o1);
		FAST.shell.showMessage("Comparing proposal "+FOMProposalTranslator.getFOMProposal((Proposal)o1)+": Assessment Value "+a1);
		Assessment a2 = assessMech.assess(agreePrefs,(Agreement)o2);
		FAST.shell.showMessage("Comparing proposal "+FOMProposalTranslator.getFOMProposal((Proposal)o2)+": Assessment Value "+a2);

		return a2.compareTo(a1);
	}
	
	public FOMProposalComparator(AgreementPreferences agreePrefs){
		this.agreePrefs = agreePrefs;
	}
	
}
