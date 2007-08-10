
package es.us.lsi.tdg.fast.domains.fom.components.FOMInformation.services.jaxws;

import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "getFOMOffersResponse", namespace = "http://services.FOMInformation.components.fom.domains.fast.tdg.lsi.us.es/")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "getFOMOffersResponse", namespace = "http://services.FOMInformation.components.fom.domains.fast.tdg.lsi.us.es/")
public class GetFOMOffersResponse {

    @XmlElement(name = "return", namespace = "")
    private List<String> _return;

    /**
     * 
     * @return
     *     returns List<String>
     */
    public List<String> get_return() {
        return this._return;
    }

    /**
     * 
     * @param _return
     *     the value for the _return property
     */
    public void set_return(List<String> _return) {
        this._return = _return;
    }

}
