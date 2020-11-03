package gr.forth.ics.argql.results;

import org.stringtemplate.v4.compiler.STParser.ifstat_return;

import gr.forth.ics.argql.translator.sparql.SPARQLTranslator;

public class Argument extends ResultValue{
	private String argVar;
	private String id;
	public PropositionSet premise = null;
	public Proposition conclusion;
	
	public Argument( String argID, String argVar) {
//		super(argID);
		super(ResultType.Argument);
		this.id = argID;
		this.argVar = argVar;
	//	premise = new PropositionSet(argID, "");
		
	}
	
	public void createPremiseSet() {
		premise = new PropositionSet(id, "");
	}

	public PropositionSet getPremise() {
		return premise;
	}

	public void setPremise(PropositionSet premise) {
		this.premise = premise;
	}

	public Proposition getConclusion() {
		return conclusion;
	}

	public void setConclusion(Proposition conclusion) {
		this.conclusion = conclusion;
	}
	
	
 
	public String getArgVar() {
		return argVar;
	}

	public void setArgVar(String argVar) {
		this.argVar = argVar;
	}

	public String toString() {
		StringBuilder str = new StringBuilder();
		str.append(argVar + ":");
		str.append("(ID-"+this.id+ ")");
		str.append("<");
		if(premise!=null) {
			str.append(premise.toString());
		} else {
			str.append("_");
		}
		str.append(", ");
		if(!conclusion.value.startsWith("\"")) {
			str.append("\"" + conclusion.value + "\"");
		} else {
			str.append(conclusion.value);
		}
		str.append(">");
		
		
		return str.toString();
	}
	
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
}
