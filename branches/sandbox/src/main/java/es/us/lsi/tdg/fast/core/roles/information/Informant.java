package es.us.lsi.tdg.fast.core.roles.information;
import es.us.lsi.tdg.fast.core.dataModel.agreement.CounterParty;
import es.us.lsi.tdg.fast.core.dataModel.information.CounterPartyKnowledge;


public interface Informant{

	CounterPartyKnowledge getKnowledge(CounterParty counterParty);

}
