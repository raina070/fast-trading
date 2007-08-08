package es.us.lsi.tdg.fast.core.component.trading;

import es.us.lsi.tdg.fast.core.component.Component;
import es.us.lsi.tdg.fast.core.roles.trading.TradingManager;

public interface TradingComponent extends Component {
	public TradingManager getTradingManager();
}
