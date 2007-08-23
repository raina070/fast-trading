package es.us.lsi.tdg.fast.domains.fom.dataModel;

import java.util.Comparator;
import es.us.lsi.tdg.fast.FAST;
public class FOMGenericComparator implements Comparator {

	public int compare(Object o1, Object o2){
		FAST.shell.showMessage("Generic Comparator");
		return 1;
	}
	
	
	

}
