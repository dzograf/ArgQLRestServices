package gr.forth.ics.argql.patterns;

import gr.forth.ics.argql.translator.sparql.Names;
import gr.forth.ics.argql.translator.sparql.SPARQLTranslator;
import gr.forth.ics.utils.Enums.TranslationMode;

public class RebutPattern extends RelationPattern {

	private String caVar = "";

	public RebutPattern(String name, String caVar) {
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
		str.append(caVar + " rdf:type argtech:CA-node.\n");
		
		if (SPARQLTranslator.translationMode == TranslationMode.Normal) {
			String u1 = getAp1().getConclusionPattern().getPropPattern().getURIVar();
			String u2 = getAp2().getConclusionPattern().getPropPattern().getURIVar();
			String eq1 = SPARQLTranslator.generateIVariable();
			String eq2 = SPARQLTranslator.generateIVariable();
			EquivalencePattern eqp1 = SPARQLTranslator.newEquivalenceCondition(u1, eq1);
			EquivalencePattern eqp2 = SPARQLTranslator.newEquivalenceCondition(u2, eq2);

			
			str.append("{\n");
			str.append( u1 + " " + Names.iToS + " " + caVar + ".\n"); 
			str.append(caVar + " " + Names.sToI + " " + u2 + ".\n");

			str.append("} UNION {\n");

			str.append(eqp1.getSparqlRepresentation());
			str.append(eqp1.getEqPropID() + " " + Names.iToS + " " + caVar + ".\n");
			str.append(caVar + " " + Names.sToI + " " + u2 + ".\n");

			str.append("} UNION {\n");

			str.append(eqp2.getSparqlRepresentation());
			str.append(u1 + " " + Names.iToS + " " + caVar + ".\n");
			str.append(caVar +" " + Names.sToI + " " + eqp2.getEqPropID() + ".\n");

			str.append("} UNION {\n");

			str.append(eqp1.getSparqlRepresentation());
			str.append(eqp2.getSparqlRepresentation());
			str.append(eqp1.getEqPropID() + " " + Names.iToS + " " + caVar + ".\n");
			str.append(caVar + " " + Names.sToI + " " + eqp2.getEqPropID() + ".\n");
			str.append("}\n");

			/*
			 * str.append("{\n"); str.append( caVar + " aif:Conclusion " +
			 * this.getAp2().getConclusionPattern().getPropPattern().getURIVar() + ".\n");
			 * 
			 * str.append(this.getAp1().getConclusionPattern().getPropPattern().getURIVar()
			 * + " aif:Premise " + caVar + ".\n"); if
			 * (this.getAp2().getConclusionPattern().isVariable()) { str.append(caVar +
			 * " aif:Conclusion " +
			 * this.getAp2().getConclusionPattern().getPropPattern().getURIVar() + ".\n"); }
			 * else {
			 * 
			 * str.append("{\n"); str.append(caVar + " aif:Conclusion " +
			 * this.getAp2().getConclusionPattern().getPropPattern().getURIVar() + ".\n");
			 * str.append("} UNION {\n"); str.append(caVar + " aif:Conclusion " +
			 * this.getAp2().getConclusionPattern().getPropPattern().getEquivPattern().
			 * getEqPropID() + ".\n"); str.append("}\n"); }
			 */
		} else if (SPARQLTranslator.translationMode == TranslationMode.Optimized)

		{
			String concl1, concl2;

			if (this.getAp1().getConclusionPattern().isVariable()) {
				concl1 = this.getAp1().getConclusionPattern().getPropPattern().getText();
			} else {
				concl1 = "\"" + this.getAp1().getConclusionPattern().getPropPattern().getText() + "\"";
			}
			if (this.getAp2().getConclusionPattern().isVariable()) {
				concl2 = this.getAp2().getConclusionPattern().getPropPattern().getText();
			} else {
				concl2 = "\"" + this.getAp2().getConclusionPattern().getPropPattern().getText() + "\"";
			}
			String iNode1 = SPARQLTranslator.generateIVariable();
			String iNode2 = SPARQLTranslator.generateIVariable();
			str.append(iNode1 + " " + Names.claimText + " " + concl1 + ".\n");
			str.append(iNode2 + " " + Names.claimText + " " + concl2 + ".\n");
			str.append(iNode1 + " " + Names.iToS + " " + caVar + ".\n");
			str.append(caVar + " " + Names.sToI + " " + iNode2 + ".\n");
		}

		return str.toString();
	}

	@Override
	public String keyId() {
		return "R";
	}

	@Override
	public String getSparqlRepresentationSimple() {
		StringBuilder str = new StringBuilder();
		str.append(caVar + " rdf:type " + Names.caNode + ".\n");
		
		if (SPARQLTranslator.translationMode == TranslationMode.Normal) {
			String u1 = getAp1().getConclusionPattern().getPropPattern().getURIVar();
			String u2 = getAp2().getConclusionPattern().getPropPattern().getURIVar();
			String eq1 = SPARQLTranslator.generateIVariable();
		//	String eq2 = SPARQLTranslator.generateIVariable();
			EquivalencePattern eqp1 = SPARQLTranslator.newEquivalenceCondition(u1, eq1);
		//	EquivalencePattern eqp2 = SPARQLTranslator.newEquivalenceCondition(u2, eq2);

		//	str.append(getAp1().getRaVariable() + " aif:Conclusion " + u1 + ".\n");
			//str.append(getAp2().getRaVariable() + " aif:Conclusion " + u2 + ".\n");

			str.append("{\n");
			str.append(u1 + " " + Names.iToS + " " + caVar + ".\n");
			str.append(caVar + " " + Names.sToI + " " + u2 + ".\n");

			str.append("} UNION {\n");

			str.append(eqp1.getSparqlRepresentation());
			str.append(eqp1.getEqPropID() + " " + Names.iToS + " " + caVar + ".\n");
			str.append(caVar + " " + Names.sToI + " " + u2 + ".\n");

		/*	str.append("} UNION {\n");

			str.append(eqp2.getSparqlRepresentation());
			str.append(u1 + " aif:Premise " + caVar + ".\n");
			str.append(caVar + " aif:Conclusion " + eqp2.getEqPropID() + ".\n");

			str.append("} UNION {\n");

			str.append(eqp1.getSparqlRepresentation());
			str.append(eqp2.getSparqlRepresentation());
			str.append(eqp1.getEqPropID() + " aif:Premise " + caVar + ".\n");
			str.append(caVar + " aif:Conclusion " + eqp2.getEqPropID() + ".\n");*/
			str.append("}\n");

			
		} else if (SPARQLTranslator.translationMode == TranslationMode.Optimized)

		{
			String concl1, concl2;

			if (this.getAp1().getConclusionPattern().isVariable()) {
				concl1 = this.getAp1().getConclusionPattern().getPropPattern().getText();
			} else {
				concl1 = "\"" + this.getAp1().getConclusionPattern().getPropPattern().getText() + "\"";
			}
			if (this.getAp2().getConclusionPattern().isVariable()) {
				concl2 = this.getAp2().getConclusionPattern().getPropPattern().getText();
			} else {
				concl2 = "\"" + this.getAp2().getConclusionPattern().getPropPattern().getText() + "\"";
			}
			String iNode1 = SPARQLTranslator.generateIVariable();
			String iNode2 = SPARQLTranslator.generateIVariable();
			str.append(iNode1 + " "  + Names.claimText + " " + concl1 + ".\n");
			str.append(iNode2 + " " + Names.claimText + " " + concl2 + ".\n");
			str.append(iNode1 + " " + Names.iToS + " " + caVar + ".\n");
			str.append(caVar + " " + Names.sToI + " " + iNode2 + ".\n");
		}

		return str.toString();
	}
	
	
	//the translation entirely ignores the equivalences
	public String getSparqlRepresentation_simplest() {
		StringBuilder str = new StringBuilder();
		str.append(caVar + " rdf:type " + Names.caNode + ".\n");
		
		//if (SPARQLTranslator.translationMode == TranslationMode.Normal) {
		String u1 = getAp1().getConclusionPattern().getPropPattern().getURIVar();
		String u2 = getAp2().getConclusionPattern().getPropPattern().getURIVar();
		
		str.append( u1 + " " + Names.iToS + " " + caVar + ".\n");
		str.append(caVar + " " + Names.sToI + " " + u2 + ".\n");
		//}

		return str.toString();
	}
	
	@Override
	public String toArgQLString() {
		// TODO Auto-generated method stub
		return null;
	}

}
