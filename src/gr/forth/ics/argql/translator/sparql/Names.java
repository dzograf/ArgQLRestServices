package gr.forth.ics.argql.translator.sparql;

public class Names {
	
	///PREFIXES 
	public static String rdf = "rdf";
	public static String rdfNS = "<http://www.w3.org/1999/02/22-rdf-syntax-ns#>";
	public static String aif = "aif";
	public static String aifNS = "<http://www.arg.tech/aif#>";
	public static String nodes = "aifnodes";
	public static String nodesNS = "<http://www.aifdb.org/nodes/#>";
	
	public static String aifNamedGraph = "<http://aifdb>";
	public static String canonicalNamedGraph = "<http://aifdb.canonical>";
	
	///CLASS NAMES 
	public static String iNode = aif + ":I-node";
	public static String lNode = aif + ":L-node";
	public static String raNode = aif + ":RA-node";
	public static String caNode = aif + ":CA-node";
	public static String paNode = aif + ":PA-node";
	public static String yaNode = aif + ":YA-node";
	public static String taNode = aif + ":TA-node";
	public static String maNode = aif + ":MA-node";
	
	
	////PROPERTIES' NAMES

	public static String rdfType = rdf + ":type";
	public static String claimText = aif + ":claimText";
	public static String iToS = aif + ":Premise";   //I-node to S-node
	public static String sToI = aif + ":Conclusion";  // S-node to I-node
	public static String yaToI = aif + ":IllocutionaryContent";  //YA-node to I-node
	public static String yaToL = aif + ":Locution";  //YA-node to Locution
	public static String locToTA = aif + ":StartLocution"; // Locution to TA-node
	public static String taToLoc = aif + ":EndLocution"; // TA-node to Locution
	public static String yaToTA = aif + ":Anchor"; // YA-node to TA-node
	public static String yaToS = aif + ":IllocutionaryContent"; //YA-node to S-node
	
}
