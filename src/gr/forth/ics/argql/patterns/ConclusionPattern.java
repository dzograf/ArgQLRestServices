package gr.forth.ics.argql.patterns;

import java.util.ArrayList;

import gr.forth.ics.utils.Enums.PremiseType;
import gr.forth.ics.argql.patterns.PropositionPattern;
import gr.forth.ics.argql.patterns.ArgPattern;
import gr.forth.ics.argql.results.Proposition;
import gr.forth.ics.argql.results.PropositionSet;
import gr.forth.ics.argql.translator.sparql.Names;

public class ConclusionPattern extends Pattern{

	private boolean isVariable;
	private ArgPattern argPattern;
	private ArrayList<Proposition> values;
	 
	private PropositionPattern propPattern;
	
	
	public ConclusionPattern() {super();}
	
	public ConclusionPattern(PropositionPattern propositionPattern, boolean isVariable) {
		super();
		this.propPattern = propositionPattern;
		this.isVariable = isVariable;
	}
	
	public PropositionPattern getPropPattern() {
		return propPattern;
	}

	public void setPropPattern(PropositionPattern propPattern) {
		this.propPattern = propPattern;
	} 

	public ArgPattern getArgPattern() {
		return argPattern;
	}

	public void setArgPattern(ArgPattern argPattern) {
		this.argPattern = argPattern;
	}
	
	
	public boolean isVariable() {
		return isVariable;
	}

	public void setVariable(boolean isVariable) {
		this.isVariable = isVariable;
	}	
	
	public ArrayList<Proposition> getValues() {
		return values;
	}

	public void setValues(ArrayList<Proposition> values) {
		this.values = values;
	}
	
	public int valueExists(String ID) {
		for (int i = 0; i < values.size(); i++) {
			Proposition prop = values.get(i);
			if (prop.argID.compareTo(ID) == 0) {
				return i;
			}
		}
		return -1;
	}
	
	
	public String getSparqlRepresentation() {
		StringBuilder str = new StringBuilder();
		if(isVariable) {
			str.append(propPattern.getSparqlRepresentation()); 
			str.append(argPattern.getRaVariable() + "  " + Names.sToI + " " + propPattern.getURIVar() + ".\n");
		} else {
			if(propPattern.getEquivPattern()!=null) {

				str.append(propPattern.getSparqlRepresentation());
			//	str.append("{\n"); 
				str.append(argPattern.getRaVariable() + "  " + Names.sToI + " " + propPattern.getURIVar() + ".\n");
			/*	str.append("} UNION {\n");
				str.append(propPattern.getEquivPattern().getSparqlRepresentation());
				str.append(argPattern.getRaVariable() + "  " + Names.sToI + " " + propPattern.getEquivPattern().getEqPropID() + ".\n");
				str.append("}\n");*/
			} else {
			    str.append(propPattern.getSparqlRepresentation());
				str.append(argPattern.getRaVariable() + "  " + Names.sToI + " " + propPattern.getURIVar() + ".\n");
			}
		}
		return str.toString();
	}

	public String toString() {
		return "\nConclusion RA Var: " + argPattern.getRaVariable() +
			"\nIs Variable: " + isVariable() +
			propPattern.toString();
	}
	
	@Override
	public String toArgQLString() {
		StringBuilder stringBuilder = new StringBuilder();
		if(this.isVariable) {
			stringBuilder.append(this.propPattern.getText());
		} else {
			stringBuilder.append(this.getPropPattern().toArgQLString());
		}
		return stringBuilder.toString();
	}
	
}
