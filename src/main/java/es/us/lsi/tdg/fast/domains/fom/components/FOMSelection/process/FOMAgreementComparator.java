package es.us.lsi.tdg.fast.domains.fom.components.FOMSelection.process;

import java.util.Comparator;
import es.us.lsi.tdg.fast.core.dataModel.agreementPreferences.*;
import es.us.lsi.tdg.fast.core.dataModel.agreement.*;

public class FOMAgreementComparator implements Comparator {

	private AgreementPreferences agreePrefs;
	
	public int compare(Object o1, Object o2){
		AssessmentMechanism assessMech = agreePrefs.getAssessmentMechanism();
		
		Assessment a1 = assessMech.assess(agreePrefs,(Agreement)o1);
		Assessment a2 = assessMech.assess(agreePrefs,(Agreement)o2);
		return a2.compareTo(a1);
	}
	
	public FOMAgreementComparator(AgreementPreferences agreePrefs){
		this.agreePrefs = agreePrefs;
	}
	
}
