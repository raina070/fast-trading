package es.us.lsi.tdg.fast.components.trading;

import es.us.lsi.tdg.fast.components.Component;
import es.us.lsi.tdg.fast.core.roles.trading.TradingManager;

public interface TradingComponent extends Component {
	public TradingManager getTradingManager();
}
