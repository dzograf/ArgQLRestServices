package gr.forth.ics.argql.patterns;

public class AttackPattern extends RelationPattern {

	private String caVar = "";

	public AttackPattern(String name, String caVar) {
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
		
		RebutPattern rebut = new RebutPattern("Rebut", caVar);
		rebut.setAp1(this.getAp1());
		rebut.setAp2(this.getAp2());
		UnderminePattern undermine = new UnderminePattern("Undermine", caVar);
		undermine.setAp1(this.getAp1());
		undermine.setAp2(this.getAp2());
		
		str.append("{ \n");
		str.append(rebut.getSparqlRepresentation());
		str.append("} UNION {\n");
		str.append(undermine.getSparqlRepresentation());
		str.append("}\n");
		

		return str.toString();
	}

	@Override
	public String keyId() {
		return "A";
	}

	@Override
	public String getSparqlRepresentationSimple() {
		StringBuilder str = new StringBuilder();
		
		RebutPattern rebut = new RebutPattern("Rebut", caVar);
		rebut.setAp1(this.getAp1());
		rebut.setAp2(this.getAp2());
		UndercutPattern undercut = new UndercutPattern("Undercut", caVar);
		undercut.setAp1(this.getAp1());
		undercut.setAp2(this.getAp2());
		
		str.append("{ \n");
		str.append(rebut.getSparqlRepresentationSimple());
		str.append("} UNION {\n");
		str.append(undercut.getSparqlRepresentationSimple());
		str.append("}\n");
		

		return str.toString();
	}
	
	@Override
	public String toArgQLString() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getSparqlRepresentation_simplest() {
		StringBuilder str = new StringBuilder();
		
		RebutPattern rebut = new RebutPattern("Rebut", caVar);
		rebut.setAp1(this.getAp1());
		rebut.setAp2(this.getAp2());
		UndercutPattern undercut = new UndercutPattern("Undercut", caVar);
		undercut.setAp1(this.getAp1());
		undercut.setAp2(this.getAp2());
		UnderminePattern undermine = new UnderminePattern("Undermine", caVar);
		undermine.setAp1(this.getAp1());
		undermine.setAp2(this.getAp2());
		
		str.append("{ \n");
		str.append(rebut.getSparqlRepresentation_simplest());
		str.append("} UNION {\n");
		str.append(undermine.getSparqlRepresentation_simplest());
		//str.append("} UNION {\n");
		//str.append(undercut.getSparqlRepresentation_simplest());
		str.append("}\n");
		

		return str.toString();
	}
	
}
