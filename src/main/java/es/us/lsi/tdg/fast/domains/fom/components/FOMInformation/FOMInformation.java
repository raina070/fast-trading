package es.us.lsi.tdg.fast.domains.fom.components.FOMInformation;

import es.us.lsi.tdg.fast.core.component.information.InformationComponent;

public class FOMInformation implements InformationComponent {

	protected String name = "FOMInformation";
	protected String type = "Information";
	
	public String getName() {
		return name;
	}

	public String getType() {
		return type;
	}

}
