package gr.forth.ics.argql.patterns;

import gr.forth.ics.argql.translator.sparql.Names;

public class EquivalencePattern extends Pattern{

	private String propID;
	private String eqPropID;
	
	private String itextVar2;
	
	private String ya1;
	private String loc1; 
	private String ya2;
	private String loc2;
	private String ta;
	private String ya4Ma;
	private String ma;
	
	public EquivalencePattern(String propID, String eqPropID, String itextVar2, String ya1, String loc1, String ya2, String loc2, String ta, String ya4Ma,String ma) {
		this.propID = propID;
		this.eqPropID = eqPropID;
		this.itextVar2 = itextVar2;
		this.ya1 = ya1;
		this.loc1 = loc1;
		this.ya2 = ya2;
		this.loc2 = loc2;
		this.ta = ta;
		this.ya4Ma = ya4Ma;
		this.ma = ma;
	}
	
	public String getPropID() {
		return propID;
	}
	
	public void setPropID(String propID) {
		this.propID = propID;
	}

	public String getEqPropID() {
		return eqPropID;
	}

	public void setEqPropID(String eqPropID) {
		this.eqPropID = eqPropID;
	}
	
	public String getItextVar2() {
		return itextVar2;
	}
	
	public void setItextVar2(String itextVar2) {
		this.itextVar2 = itextVar2;
	}
	
	public String getYa1() {
		return ya1;
	}

	public void setYa1(String ya1) {
		this.ya1 = ya1;
	}

	public String getLoc1() {
		return loc1;
	}

	public void setLoc1(String loc1) {
		this.loc1 = loc1;
	}
	
	public String getYa2() {
		return ya2;
	}

	public void setYa2(String ya2) {
		this.ya2 = ya2;
	}

	public String getLoc2() {
		return loc2;
	}

	public void setLoc2(String loc2) {
		this.loc2 = loc2;
	}

	public String getTa() {
		return ta;
	}

	public void setTa(String ta) {
		this.ta = ta;
	}

	public String getYa4Ma() {
		return ya4Ma;
	}

	public void setYa4Ma(String ya4Ma) {
		this.ya4Ma = ya4Ma;
	}

	public String getMa() {
		return ma;
	}
	
	public void setMa(String ma) {
		this.ma = ma;
	}


	@Override
	public String getSparqlRepresentation() {
		
		String sparql = ya1 + " " + Names.yaToI + " " + propID + ".\n" +
				  ya1 + "  " + Names.yaToL + " " + loc1 + ".\n" +
				  loc1 + " rdf:type " + Names.lNode + ".\n" +
				  eqPropID + " rdf:type " + Names.iNode + ".\n" + 
				  eqPropID + " " + Names.claimText + " " + itextVar2 + ".\n" +
	 			  ya2 + " " + Names.yaToI + " " + eqPropID + ".\n" +
				  ya2 + " " + Names.yaToL + " " + loc2 + ".\n" +
				  loc2 + " rdf:type " + Names.lNode + ".\n" +
				  ta + " rdf:type " + Names.taNode + ".\n" +
				  loc1 + " " + Names.locToTA + " " + ta + ".\n" +
				  ta + " " + Names.taToLoc + " "+ loc2 + ".\n" +
				  ya4Ma + " " + Names.yaToTA + " " + ta + ".\n"+
				  ya4Ma + " " + Names.yaToS + " " + ma + ".\n" +
				  ma + " rdf:type " + Names.maNode + ".\n";
		
		
		return sparql;
	}
	
	@Override
	public String toArgQLString() {
		// TODO Auto-generated method stub
		return null;
	}
}
