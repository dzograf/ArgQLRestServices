package gr.forth.ics.argql.patterns;

import java.util.Iterator;

import gr.forth.ics.argql.translator.sparql.Names;
import gr.forth.ics.argql.translator.sparql.SPARQLTranslator;
import gr.forth.ics.utils.Enums.PremiseType;
import gr.forth.ics.utils.Enums.TranslationMode;

public class UnderminePattern extends RelationPattern {

	private String caVar = "";

	public UnderminePattern(String name, String caVar) {
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
		return getSparqlRepresentationSimple();
	}
	 
	
/*	public String getSparqlRepresentation_old() {

		StringBuilder str = new StringBuilder();

		str.append(caVar + " rdf:type " + Names.caNode + ".\n");
		
		if (SPARQLTranslator.translationMode == TranslationMode.Normal) {
			String u1 = getAp1().getConclusionPattern().getPropPattern().getURIVar();
			String u2 = SPARQLTranslator.generateIVariable();
			String eq1 = SPARQLTranslator.generateIVariable();
			String eq2 = SPARQLTranslator.generateIVariable();
			EquivalencePattern eqp1 = SPARQLTranslator.newEquivalenceCondition(u1, eq1);
			EquivalencePattern eqp2 = SPARQLTranslator.newEquivalenceCondition(u2, eq2);

			//str.append(getAp1().getRaVariable() + " aif:Conclusion " + u1 + ".\n");
			str.append(u2 + " " + Names.iToS + " " + getAp2().getRaVariable() + ".\n");

			str.append("{\n");
			str.append(u1 + " " + Names.iToS + " " + caVar + ".\n");
			str.append(caVar + " " + Names.sToI + " " + u2 + ".\n");

			str.append("} UNION {\n");

			str.append(eqp1.getSparqlRepresentation());
			str.append(eqp1.getEqPropID() + " " + Names.iToS + " " + caVar + ".\n");
			str.append(caVar + " " + Names.sToI + " " + u2 + ".\n");

			str.append("} UNION {\n");

			str.append(u1 + " " + Names.iToS + " " + caVar + ".\n");
			str.append(eqp2.getSparqlRepresentation());
			str.append(caVar + " " + Names.sToI + " " + eqp2.getEqPropID() + ".\n");

			str.append("} UNION {\n");

			str.append(eqp1.getSparqlRepresentation());
			str.append(eqp2.getSparqlRepresentation());
			str.append(eqp1.getEqPropID() + " " + Names.iToS + " " + caVar + ".\n");
			str.append(caVar + " " + Names.sToI + " " + eqp2.getEqPropID() + ".\n");
			str.append("}");

		} else if (SPARQLTranslator.translationMode == TranslationMode.Optimized) {
			String concl1, prem2;
			if (this.getAp1().getConclusionPattern().isVariable()) {
				concl1 = this.getAp1().getConclusionPattern().getPropPattern().getText();
			} else {
				concl1 = "\"" + this.getAp1().getConclusionPattern().getPropPattern().getText() + "\"";
			}
			String iNode1 = SPARQLTranslator.generateIVariable();
			str.append(iNode1 + " " + Names.claimText + " "  + concl1 + ".\n");

			String iNode2 = SPARQLTranslator.generateIVariable();

			if (this.getAp2().getPremisePattern().getType() == PremiseType.VARIABLE) {
				prem2 = this.getAp2().getPremisePattern().getPropPattern().getText();

				str.append(iNode2 + " " + Names.claimText + " "  + prem2 + ".\n");
				str.append(iNode1 + " " + Names.iToS + " " + caVar + ".\n");
				str.append(caVar + " " + Names.sToI + " " + iNode2 + ".\n");
			} else if(this.getAp2().getPremisePattern().getType() == PremiseType.VARIABLE) {

				Iterator<PropositionPattern> iter = this.getAp2().getPremisePattern().getProps().iterator();

				while (iter.hasNext()) {
					PropositionPattern prop = (PropositionPattern) iter.next();
					prem2 = "\"" + prop.getText() + "\"";
					if (iter.hasNext())
						str.append("{\n");

					str.append(iNode2 + " " + Names.claimText + " "  + prem2 + ".\n");
					str.append(iNode1 + " " + Names.iToS + " " + caVar + ".\n");
					str.append(caVar + " " + Names.sToI + " " + iNode2 + ".\n");
					if (iter.hasNext())
						str.append(" } UNION \n");
				}
			}
		}
		return str.toString();
	}*/

	@Override
	public String keyId() {
		return "UM";
	}

	
	private String sparqQLRepresentationSimpleOptimized() {
		StringBuilder str = new StringBuilder();

		String concl1, prem2;
		if (this.getAp1().getConclusionPattern().isVariable()) {
			concl1 = this.getAp1().getConclusionPattern().getPropPattern().getText();
		} else {
			concl1 = "\"" + this.getAp1().getConclusionPattern().getPropPattern().getText() + "\"";
		}
		String iNode1 = SPARQLTranslator.generateIVariable();
		str.append(iNode1 + " " + Names.claimText + " "  + concl1 + ".\n");

		String iNode2 = SPARQLTranslator.generateIVariable();

		if (this.getAp2().getPremisePattern().getType() == PremiseType.VARIABLE) {
			prem2 = this.getAp2().getPremisePattern().getPropPattern().getText();

			str.append(iNode2 + " " + Names.claimText + " "  + prem2 + ".\n");
			str.append(iNode1 + " " + Names.iToS + " " + caVar + ".\n");
			str.append(caVar + " " + Names.sToI + " " + iNode2 + ".\n");
		} else if (this.getAp2().getPremisePattern().getType() == PremiseType.PROPSET) {

			Iterator<PropositionPattern> iter = this.getAp2().getPremisePattern().getProps().iterator();

			while (iter.hasNext()) {
				PropositionPattern prop = (PropositionPattern) iter.next();
				prem2 = "\"" + prop.getText() + "\"";
				if (iter.hasNext())
					str.append("{\n");

				str.append(iNode2 + " " + Names.claimText + " "  + prem2 + ".\n");
				str.append(iNode1 + " " + Names.iToS + " "+ caVar + ".\n");
				str.append(caVar + " " + Names.sToI + " " + iNode2 + ".\n");
				if (iter.hasNext())
					str.append(" } UNION \n");
			}
		}
		
		return str.toString();
	}
	
	private String sparQLRepresentationSimpleNormal() {
		StringBuilder str = new StringBuilder();
		String u1 = getAp1().getConclusionPattern().getPropPattern().getURIVar();
		String u2 = SPARQLTranslator.generateIVariable();
		String eq1 = SPARQLTranslator.generateIVariable();
	//	String eq2 = SPARQLTranslator.generateIVariable();
		EquivalencePattern eqp1 = SPARQLTranslator.newEquivalenceCondition(u1, eq1);
	//	EquivalencePattern eqp2 = SPARQLTranslator.newEquivalenceCondition(u2, eq2);*/

//		str.append(getAp1().getRaVariable() + " aif:Conclusion " + getAp1().getConclusionPattern().getPropPattern().getURIVar() + ".\n");
		str.append(u2 + " " + Names.iToS + " " + getAp2().getRaVariable() + ".\n");

		str.append("{\n");
		str.append(u1 + " " + Names.iToS + " " + caVar + ".\n");
		str.append(caVar + " " + Names.sToI + " " + u2 + ".\n");

		str.append("} UNION {\n");

		str.append(eqp1.getSparqlRepresentation());
		str.append(eqp1.getEqPropID() + " " + Names.iToS + " " + caVar + ".\n");
		str.append(caVar + " " + Names.sToI + " " + u2 + ".\n");
/*
		str.append("} UNION {\n");

		str.append(u1 + " aif:Premise " + caVar + ".\n");
		str.append(eqp2.getSparqlRepresentation());
		str.append(caVar + " aif:Conclusion " + eqp2.getEqPropID() + ".\n");

		str.append("} UNION {\n");

		str.append(eqp1.getSparqlRepresentation());
		str.append(eqp2.getSparqlRepresentation());
		str.append(eqp1.getEqPropID() + " aif:Premise " + caVar + ".\n");
		str.append(caVar + " aif:Conclusion " + eqp2.getEqPropID() + ".\n");*/
		str.append("}");

		return str.toString();
	}
	@Override
	public String getSparqlRepresentationSimple() {

		StringBuilder str = new StringBuilder();


		str.append(caVar + " rdf:type " + Names.caNode + ".\n");
		if (SPARQLTranslator.translationMode == TranslationMode.Normal) {
			str.append(sparQLRepresentationSimpleNormal());
		} else if (SPARQLTranslator.translationMode == TranslationMode.Optimized) {
			str.append(sparqQLRepresentationSimpleOptimized());
		}
		return str.toString();
	}

	
	
	@Override
	public String toArgQLString() {
		// TODO Auto-generated method stub
		return null;
	}

	
	//entirely ignores equivalences...
	@Override
	public String getSparqlRepresentation_simplest() {
		StringBuilder str = new StringBuilder();
		String u1 = getAp1().getConclusionPattern().getPropPattern().getURIVar();
		String u2 = SPARQLTranslator.generateIVariable();
		
	
		str.append(u2 + " " + Names.iToS + " " + getAp2().getRaVariable() + ".\n");
		str.append(u1 + " " + Names.iToS + " " + caVar + ".\n");
		str.append(caVar + " " + Names.sToI + " " + u2 + ".\n");
		str.append("FILTER (" + getAp1().getRaVariable() + " != " + getAp2().getRaVariable() + ")\n");
		return str.toString();
	}

}
