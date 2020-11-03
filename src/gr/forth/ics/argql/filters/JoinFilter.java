package gr.forth.ics.argql.filters;

import java.util.ArrayList;
import java.util.Iterator;

import gr.forth.ics.argql.patterns.ArgPattern;
import gr.forth.ics.argql.patterns.EquivalencePattern;
import gr.forth.ics.argql.patterns.PremisePattern;
import gr.forth.ics.argql.patterns.PropositionPattern;
import gr.forth.ics.argql.translator.sparql.Names;
import gr.forth.ics.argql.translator.sparql.SPARQLTranslator;
import gr.forth.ics.utils.Enums.TranslationMode;

public class JoinFilter extends Filter {

	public JoinFilter(PremisePattern premPattern, ArgPattern ap2, String raVar) {
		super(premPattern, ap2, raVar);
		// TODO Auto-generated constructor stub
	}

	public JoinFilter(PremisePattern premPattern, ArrayList<PropositionPattern> propset, String raVar) {
		super(premPattern, propset, raVar);
		// TODO Auto-generated constructor stub
	}

	public String getSparqlRepresentationNormal() {
		StringBuilder str = new StringBuilder();

		if (isVar()) {
			str.append("{\n");
			str.append(ap2.getPremisePattern().getPropPattern().getURIVar() + " " + Names.iToS + " " + raVar + ".\n");
			str.append("} UNION { \n");
			str.append(premPattern.getPropPattern().getURIVar() + " " + Names.iToS + " "
					+ ap2.getRaVariable() + ".\n");
			str.append("} UNION {\n");
			EquivalencePattern equiv = SPARQLTranslator.newEquivalenceCondition(
					ap2.getPremisePattern().getPropPattern().getURIVar(),
					premPattern.getPropPattern().getURIVar());
			str.append(equiv.getSparqlRepresentation());
			str.append("}\n");

			str.append("FILTER(" + raVar + " != " + ap2.getRaVariable() + ")\n");
		} else {

				Iterator<PropositionPattern> iter = propset.iterator();
				while (iter.hasNext()) {
					PropositionPattern iNode = iter.next();

					str.append("{\n" + iNode.getSparqlRepresentation() + "{\n" + iNode.getURIVar()
					+ " " + Names.iToS + " " + raVar + ".\n" + "} UNION {\n"
					+ iNode.getEquivPattern().getSparqlRepresentation()
					+ iNode.getEquivPattern().getEqPropID() + " " + Names.iToS + " " + raVar + ".\n" + "}\n"
					+ "}");

					if (iter.hasNext())
						str.append(" UNION\n");
					else
						str.append("\n");
				}
			} 
		return str.toString();
	}

	
	
	public String getSparqlRepresentationOptimized() {
		StringBuilder str = new StringBuilder();

		if (isVar()) {

			str.append("FILTER(" + premPattern.getPropPattern().getText() + " = "
					+ ap2.getPremisePattern().getPropPattern().getText() + ")\n");
			str.append("FILTER(" + raVar + " != " + ap2.getRaVariable() + ")\n");
		} else {

			Iterator<PropositionPattern> iter = propset.iterator();
			while (iter.hasNext()) {
				PropositionPattern iNode = iter.next();
				
				str.append("{\n" + iNode.getSparqlRepresentation() + iNode.getURIVar()
							+ " " + Names.iToS + " "+ raVar + ".\n " + "}");

				if (iter.hasNext())
					str.append(" UNION\n");
				else
					str.append("\n");
				}
			} 
		return str.toString();
	}
	
	@Override
	public String getSparqlRepresentation() {
		if(SPARQLTranslator.translationMode == TranslationMode.Normal) {
			return getSparqlRepresentationNormal();
		} else {
			return getSparqlRepresentationOptimized();
		}
	}
	
	
	@Override
	public String toArgQLString() {
		// TODO Auto-generated method stub
		return null;
	}
}
