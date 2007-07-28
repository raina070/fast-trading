package es.us.lsi.tdg.fast.core.dataModel.statement;
/**
* @author Pablo Fernandez Montes
* @author José Antonio Parejo Maestre
*
*/
public interface Domain {
	public boolean belongs(Value val);
	public Value getInfinite();
}
