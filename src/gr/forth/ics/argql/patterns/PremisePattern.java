package gr.forth.ics.argql.patterns;

import java.util.ArrayList;

import gr.forth.ics.argql.filters.Filter;
import gr.forth.ics.argql.results.PropositionSet;
import gr.forth.ics.argql.translator.sparql.Names;
import gr.forth.ics.argql.translator.sparql.SPARQLTranslator;
import gr.forth.ics.utils.Enums.PremiseType;
import gr.forth.ics.utils.Enums.TranslationMode;

public class PremisePattern extends Pattern {

	private PremiseType type;
	
	// if isVariable=true -> a single PropositionPattern..
	private PropositionPattern propPattern;
	private Filter filter;
	private ArrayList<PropositionSet> values;

	// if isVariable=false -> list of propositionPatterns..
	private ArrayList<PropositionPattern> props;

	String raVar = "";

	public PremisePattern(PropositionPattern propVar, String raVar, PremiseType type) {
		super();
		this.raVar = raVar;
		this.type = type;
		this.propPattern = propVar;
		this.props = null;
 		this.filter = null;
 		this.values = new ArrayList<PropositionSet>();
	}

	public PremisePattern(ArrayList<PropositionPattern> props, String raVar, PremiseType type) {
		super();
		this.raVar = raVar;
		this.type = type;
		this.props = props;
		this.propPattern = null;
		this.filter = null;
	}
	
	public PremisePattern(PremiseType type) {
		super();
		this.raVar = null;
		this.type = type;
		this.props = null;
		this.propPattern = null;
		this.filter = null;
	}
	

	public ArrayList<PropositionPattern> getProps() {
		return props;
	}

	public void setProps(ArrayList<PropositionPattern> props) {
		this.props = props;
	}

	public String getRaVar() {
		return raVar;
	}

	public void setRaVar(String raVar) {
		this.raVar = raVar;
	}

	public PremiseType getType() {
		return type;
	}

	public void setType(PremiseType type) {
		this.type = type;
	}

	public PropositionPattern getPropPattern() {
		return propPattern;
	}

	public void setPropPattern(PropositionPattern propPattern) {
		this.propPattern = propPattern;
	}

	public Filter getFilter() {
		return filter;
	}

	public void setFilter(Filter filter) {
		this.filter = filter;
	}

	
	public ArrayList<PropositionSet> getValues() {
		return values;
	}

	public void setValues(ArrayList<PropositionSet> values) {
		this.values = values;
	}

	public int valueExists(String setID) {
		for (int i = 0; i < values.size(); i++) {
			PropositionSet set = values.get(i);
			if (set.getSetID().compareTo(setID) == 0) {
				return i;
			}
		}
		return -1;
	}
	
	public String getSparqlRepresentationNormal() {
		StringBuilder str = new StringBuilder();
		
		if (this.type == PremiseType.VARIABLE) {
			
			str.append(propPattern.getSparqlRepresentation() + propPattern.getURIVar() + " " + Names.iToS + " "
					+ raVar + ".\r\n");
			
			if(filter != null) {
				str.append(filter.getSparqlRepresentation());
			}
			
		} else if (this.type == PremiseType.PROPSET){
			for (PropositionPattern prop : props) {
				str.append(prop.getSparqlRepresentation() + 
						//"{ \n" + 
						prop.getURIVar() + " " + Names.iToS + " " + raVar + ".\n"  
					//	+ "} UNION { \n" + 
					//	prop.getEquivPattern().getSparqlRepresentation() + prop.getEquivPattern().getEqPropID() + " aif:Premise " + raVar + ".\n" + "}\n"
					);
			}
		}
		return str.toString();
	}

	
	public String getSparqlRepresentationOptimized() {
		StringBuilder str = new StringBuilder();
		
		if (this.type == PremiseType.VARIABLE) {
			
			str.append(propPattern.getSparqlRepresentation() + propPattern.getURIVar() + " " + Names.iToS + " "
					+ raVar + ".\r\n");
			
			if(filter != null) {
				str.append(filter.getSparqlRepresentation());
			}
			
		} else if (this.type == PremiseType.PROPSET){

			for (PropositionPattern prop : props) {
				str.append(
						prop.getSparqlRepresentation() + 
						prop.getURIVar() + " " + Names.iToS + " " + raVar + ".\n");
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
		StringBuilder stringBuilder = new StringBuilder();
		// TODO Auto-generated method stub
		if(this.type == PremiseType.VARIABLE) {
			stringBuilder.append(propPattern.toArgQLString());
			if(filter!=null) {
				stringBuilder.append(filter.toArgQLString());
			}
		} else if(this.type == PremiseType.PROPSET)
		{
			for (PropositionPattern prop : props) {
				stringBuilder.append(prop.toArgQLString()+ ", ");
			}
		} 
		return stringBuilder.toString();
	}

	public String toString() {
		StringBuilder s = new StringBuilder();

		s.append("\nPremise Type: " + this.type);
		if (this.type == PremiseType.VARIABLE) {
			s.append("\nPremise ArgQL variable: " + propPattern.getText());
			s.append("\nPremise SPARQL variable: " + propPattern.getURIVar());
		} else if (this.type == PremiseType.PROPSET){
			for (PropositionPattern prop : props) {
				s.append(prop.toString() + "\n");
			}
		}

		s.append("\nPremise pattern RA var: " + raVar + "\n");

		return s.toString();
	}

}
