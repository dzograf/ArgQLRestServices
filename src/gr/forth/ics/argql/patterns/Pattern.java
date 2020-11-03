package gr.forth.ics.argql.patterns;

public abstract class Pattern {

	
	public Pattern() {
		
	}

	public abstract String getSparqlRepresentation();
	
	public abstract String toArgQLString();
	
}
