package gr.forth.ics.argql.patterns;

import java.util.Iterator;

import gr.forth.ics.argql.translator.sparql.Names;
import gr.forth.ics.argql.translator.sparql.SPARQLTranslator;
import gr.forth.ics.utils.Enums.PremiseType;
import gr.forth.ics.utils.Enums.TranslationMode;

public class UndercutPattern extends RelationPattern {

	private String caVar = "";

	public UndercutPattern(String name, String caVar) {
		super(name);
		this.caVar = caVar;
	}

	public String getCaVar() {
		return caVar;
	}

	public void setCaVar(String caVar) {
		this.caVar = caVar;
	}

	@Override
	public String getSparqlRepresentation() {

		StringBuilder str = new StringBuilder();


		str.append(caVar + " rdf:type " + Names.caNode + ".\n");

		String u1 = getAp1().getConclusionPattern().getPropPattern().getURIVar();
		//String u2 = SPARQLTranslator.generateIVariable();

		str.append(u1 + " " + Names.iToS + " " + caVar + ".\n");

		String ra2 = getAp2().getRaVariable();

		str.append(caVar + " " + Names.sToI + " " + ra2 + ".\n");


		return str.toString();
	}


	@Override
	public String keyId() {
		return "UC";
	}

	@Override
	public String getSparqlRepresentationSimple() {
		return getSparqlRepresentation();
	}
	
	@Override
	public String toArgQLString() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getSparqlRepresentation_simplest() {
		// TODO Auto-generated method stub
		return getSparqlRepresentation();
	}

}
