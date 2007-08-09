package es.us.lsi.tdg.fast.core.roles.information;
import java.util.Set;

import es.us.lsi.tdg.fast.core.dataModel.agreement.CounterParty;
import es.us.lsi.tdg.fast.core.dataModel.information.CounterPartyKnowledge;


public interface Informant{

	Set<CounterPartyKnowledge> getKnowledge(CounterParty counterParty);

}
