package es.us.lsi.tdg.fast.core.dataModel.statement;
/**
* @author Pablo Fernandez Montes
* @author Jos� Antonio Parejo Maestre
*
*/
public interface Domain {
	public boolean belongs(Value val);
	public Value parseValue(String value);
	public Value getInfinite();
}
