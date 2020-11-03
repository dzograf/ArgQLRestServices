package gr.forth.ics.argql.patterns;


import java.util.ArrayList;

import org.apache.thrift.scheme.IScheme;

import gr.forth.ics.argql.translator.sparql.Names;
import gr.forth.ics.argql.translator.sparql.SPARQLTranslator;
import gr.forth.ics.utils.Enums.TranslationMode;

public class PropositionPattern extends Pattern {
	//private boolean isVariable = false;

	private String id="";
	private String text = "";
	private String uriVar = "";
	private boolean isVariable;
	private String textVarString = "";
	private ArrayList<String> textValues = null;

	private EquivalencePattern equivPattern;

	public PropositionPattern(String uriVar, String text, EquivalencePattern equivPattern, boolean isVariable) {
		this.uriVar = uriVar;
		this.text = text;
		this.equivPattern = equivPattern;
		this.isVariable = isVariable;
	}

	/**
	 * This constructor is used in the optimised mode and in particular is used to implement the 
	 * "contains" functionality. As the sub-text might be included in more than one propositions, all of the
	 * possible values will have to be checked whether they satisfy the query condition.
	 * @param uriVar
	 * @param values
	 * @param equivPattern
	 * @param isVariable
	 */
	/*public PropositionPattern(String uriVar, ArrayList<String> textValues, EquivalencePattern equivPattern, boolean isVariable) {
		this.uriVar = uriVar;
		this.textValues = textValues;
		this.equivPattern = equivPattern;
		this.isVariable = isVariable;
	}*/
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	public String getURIVar() {
		return uriVar;
	}

	public void setURIVar(String uriVar) {
		this.uriVar = uriVar;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public EquivalencePattern getEquivPattern() {
		return equivPattern;
	}

	public void setEquivPattern(EquivalencePattern equivPattern) {
		this.equivPattern = equivPattern;
	}

	public boolean isVariable() {
		return isVariable;
	}

	public void setVariable(boolean isVariable) {
		this.isVariable = isVariable;
	}

	public String getTextVarString() {
		return textVarString;
	}

	public void setTextVarString(String textVarString) {
		this.textVarString = textVarString;
	}

	public ArrayList<String> getTextValues() {
		return textValues;
	}

	public void setTextValues(ArrayList<String> textValues) {
		this.textValues = textValues;
	}

	public String getSparqlRepresentationOptimized() {

		StringBuilder str = new StringBuilder();

		//textVarString = SPARQLTranslator.generateTextVariable();
		
		if(!isVariable) {
			str.append(uriVar + " rdf:type " + Names.iNode + ".\n");
			str.append(uriVar + " " + Names.claimText + " " +"\""+ text + "\""+ ".\n");
		//	str.append(uriVar + " " + Names.claimText + " " + textVarString + ".\n");
			//str.append("FILTER (" + textVarString + " in (");
		//	for (String val : textValues) {
		//		str.append("\"" + val + "\", ");
		//	}
			//str.delete(str.length()-2, str.length()-1);
		//	str.append("))\n");
			
		} else { // if it is a variable
			str.append(uriVar + " rdf:type " + Names.iNode + ".\n");
			str.append(uriVar + " " + Names.claimText + " " + text + ".\n");
		}
		return str.toString();
	}

	public String getSparqlRepresentationNormal() {
		StringBuilder str = new StringBuilder();

		if(!isVariable) { //if it is a string proposition pattern

			textVarString = SPARQLTranslator.generateTextVariable();

			str.append(uriVar + " rdf:type " + Names.iNode + ".\n");
			str.append(uriVar + " " + Names.claimText + " " + textVarString + ".\n");
			str.append("FILTER (CONTAINS(lcase(str("+ textVarString + ")), lcase("+ text +"))).\n");

		} else { // if it's variable
			str.append(uriVar + " rdf:type " + Names.iNode + ".\n");
			str.append(uriVar + " " + Names.claimText + " " + text + ".\n");
		}

		return str.toString();
	}

	
	
	public String toString() {
		StringBuilder str = new StringBuilder();
		str.append("\nProposition Variable: " + uriVar);
		str.append("\nProposition value: " + text);
		if (equivPattern != null) {
			str.append("\nEquivalent I-variable: " + equivPattern.getEqPropID());
			str.append("\nEquivalent text var: " + equivPattern.getItextVar2());
		}
		return str.toString();
	}
	
	@Override
	public String toArgQLString() {
		if(isVariable) 
			return this.text;// + "("+this.id + ")";
		else 
			return "\"" + this.text + "\"";// + "("+this.id + ")";
	}

	@Override
	public String getSparqlRepresentation() {
		if(SPARQLTranslator.translationMode == TranslationMode.Normal) {
			return getSparqlRepresentationNormal();
		} else {
			return getSparqlRepresentationOptimized();
		}
	}

	

}
