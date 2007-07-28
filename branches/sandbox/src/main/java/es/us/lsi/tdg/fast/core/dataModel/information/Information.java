package es.us.lsi.tdg.fast.core.dataModel.information;
import java.util.Set;
import es.us.lsi.tdg.fast.core.dataModel.statement.Statement;

public interface Information {
	Set<Statement> getRequirements();
	Set<Statement> getFeatures();
}
