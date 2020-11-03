package gr.forth.ics.argql.translator.sparql;



import gr.forth.ics.argql.canonical.*;
import gr.forth.ics.argql.patterns.*;
import gr.forth.ics.argql.results.*;
import gr.forth.ics.data.DatabaseManager;
import gr.forth.ics.utils.Enums.*;

import static gr.forth.ics.utils.Enums.TranslationMode.Normal;
import static gr.forth.ics.utils.Enums.TranslationMode.Optimized;

import java.sql.ResultSet;
import java.util.*;

import org.stringtemplate.v4.compiler.STParser.ifstat_return;

public class SPARQLTranslator {
	// public static ArrayList<ArgPattern> argpatterns = new
	// ArrayList<ArgPattern>();
	public static ArrayList<PropositionPattern> propPatterns = new ArrayList<PropositionPattern>();
	//public static ValueManager argManager = new ValueManager();
	public static TranslationMode translationMode;
	public static int limitOffset;

	public static gr.forth.ics.argql.canonical.RelationalDatabaseManager dbMngr;
	public static ArrayList<ReturnElement> returnValues;
	private static ArrayList<String> logicErrors;
	public static ArrayList<String> graphs;
	// private static SymbolTable memory;
	private static ArrayList<Variable> variables;
	public static ArrayList<DialoguePattern> dpList;

	private static int raVarCntr;
	private static int iVarCnt;
	private static int maVarCnt;
	private static int caVarCnt;
	private static int paVarCnt;
	private static int yaVarCnt;
	private static int locVarCnt;
	private static int taVarCnt;
	private static int txtVarCnt;
	private static int tempVarCnt;
	private static int topicVarCnt;
	
	

	public SPARQLTranslator() {

	}

	public static void initialize(int offset) {
		returnValues = new ArrayList<ReturnElement>();
		dbMngr = new RelationalDatabaseManager();
		dpList = new ArrayList<DialoguePattern>();
		variables = new ArrayList<Variable>();
		logicErrors = new ArrayList<String>();
		graphs = new ArrayList<String>();
		limitOffset = offset;
		translationMode = Normal;
		resetVariables();
	}


	public static void terminate() {
		dbMngr.terminate();
		returnValues.clear();
		logicErrors.clear();
		dpList.clear();
	}

	public static void resetVariables() {
		raVarCntr = 0;
		iVarCnt = 0;
		topicVarCnt = 0;
		maVarCnt = 0;
		caVarCnt = 0;
		yaVarCnt = 0;
		paVarCnt = 0;
		locVarCnt = 0;
		taVarCnt = 0;
		tempVarCnt = 0;
		txtVarCnt = 0;
	}

	private static String generateMAVariable() {
		maVarCnt++;
		String var = "?_ma" + maVarCnt;
		return var;
	}

	public static String generateCAVariable() {
		caVarCnt++;
		String var = "?_ca" + caVarCnt;
		return var;
	}

	public static String generateRAVariable() {
		raVarCntr++;
		String var = "?_ra" + raVarCntr;
		return var;
	}	
	
	public static String generatePAVariable() {
		paVarCnt++;
		String var = "?_pa" + paVarCnt;
		// addVarToSymbolTable(var, Variable.Type.URI);
		return var;
	}

	public static String generateIVariable() {
		iVarCnt++;
		String var = "?_i" + iVarCnt;
		return var;
	}

	public static String generateYAVariable() {
		yaVarCnt++;
		String var = "?_ya" + yaVarCnt;

		return var;
	}

	public static String generateLocVariable() {
		locVarCnt++;
		String var = "?_loc" + locVarCnt;
		return var;
	}

	public static String generateTAVariable() {
		taVarCnt++;
		String var = "?_ta" + taVarCnt;
		return var;
	}

	public static String generateTempVariable() {
		tempVarCnt++;
		String var = "?_x" + tempVarCnt;
		return var;
	}

	public static String generateTextVariable() {
		txtVarCnt++;
		String var = "?_txt" + txtVarCnt;
		return var;
	}

	public static String generateTopicVariable() {
		topicVarCnt++;
		String var = "?_t" + topicVarCnt;
		return var;
	}

	// ********** SYMBOLTABLE MANAGEMENT ********************************//

	public static boolean addVar(String name, VariableType type) {

		for (Variable v : variables) {
			if (v.name.compareTo(name) == 0) {
				logicErrors.add("Variable " + name + " is being used.\n");
			}
		}

		Variable var = new Variable(name, type);
		return variables.add(var);

	}

	// ********** SELECT_TABLE MANAGEMENT ********************************//

	public static void addVarReturnvalue(String varName) {
		Variable var = findVariable(varName);
		if(var!=null){
			ReturnElement retValue = new ReturnElement(var, ReturnValueType.Variable);
			if(!retElementExists(varName)) {
				returnValues.add(retValue);
			} else { 
				logicErrors.add("Variable " + varName + "is already in return values.\n");
			}
		} else{
			logicErrors.add("There is no variable " + varName + ".\n");
		}
	}
	
	
	public static void addGraph(String graph) {
		graphs.add(graph);
	}
	
	public static void graphRange(String  graph1, String graph2) {
		int fromGraph = Integer.parseInt(graph1);
		int toGraph = Integer.parseInt(graph2);
		
		for(int i = fromGraph; i<=toGraph; i++) {
			addGraph(Integer.toString(i));
		}
	}
	
	
	private static boolean retElementExists(String varname) {
		for(ReturnElement retElem : returnValues) {
			if(retElem.getType() == ReturnValueType.Variable) {
				if(retElem.getVar().name.compareTo(varname) == 0) {
					return true;
				}
			}
			
			if(retElem.getType() == ReturnValueType.Path) {
				if(retElem.getVar().name.compareTo(varname) == 0 ||
						retElem.getVar2().name.compareTo(varname) == 0) {
					return true;
				}
			}
		}
		return false;
	}
	
	private static boolean pathpatternExists(Variable var1, Variable var2) {
		for(DialoguePattern dp: dpList) {
			if(dp.isPathPattern) {
				PathPatternCollection pathList = dp.getPpCol();
				PathPattern lastPP = pathList.getPathSet().get(pathList.getPathSet().size()-1);
				ArgPattern firstAP = lastPP.getRelSequence().get(0).getAp1();
				ArgPattern lastAP = lastPP.getRelSequence().get(lastPP.getRelSequence().size()-1).getAp2();
				if(firstAP.getArgVar().compareTo(var1.name) == 0 && lastAP.getArgVar().compareTo(var2.name) ==0) {
					return true;
				}
			}
		}
		return false;
	}
	
	
	public static void addPathReturnValue(String varName1, String varName2) {
		Variable var1 = findVariable(varName1);
		Variable var2 = findVariable(varName2);
		if(var1 == null){
			logicErrors.add("There is no variable " + varName1 + ".\n");
		} 
		if(var1 == null){
			logicErrors.add("There is no variable " + varName2 + ".\n");
		}
		
		boolean ppExists = pathpatternExists(var1, var2);
		if(!ppExists){
			logicErrors.add("There is no path pattern from variable " + var1.name + " to variable " + var2.name);
		} 
		boolean var1Exists = retElementExists(varName1);
		boolean var2Exists = retElementExists(varName2);
		
		if(var1Exists) {
			logicErrors.add("Variable " + varName1 + "is already in return values.\n");
		}
		
		if(var2Exists) {
			logicErrors.add("Variable " + varName2 + "is already in return values.\n");
		}
		
		if(var1 != null && var2 != null && ppExists && !var1Exists && !var2Exists) {
			ReturnElement retValue = new ReturnElement(var1, var2, ReturnValueType.Path);
			returnValues.add(retValue);
		}
	}

	public static void addDialoguePattern(DialoguePattern dp) {
		dpList.add(dp);
	}

	public static EquivalencePattern newEquivalenceCondition(String id1, String id2) {
		String ya1 = generateYAVariable();
		String loc1 = generateLocVariable();

		String itextVar2 = generateTextVariable();
		String ya2 = generateYAVariable();
		String loc2 = generateLocVariable();

		String ta = generateTAVariable();
		String ya4Ma = generateYAVariable();
		String ma = generateMAVariable();

		EquivalencePattern equiv = new EquivalencePattern(id1, id2, itextVar2, ya1, loc1, ya2, loc2, ta, ya4Ma, ma);

		return equiv;
	}

	// *************TRANSLATE ARGPATTERN SINGLE VARIABLE *****************//

	public static ArgPattern argPatternVariable(String varName, String raVar) {

		// ***********PREMISE**********
		String uriVar = generateIVariable();
		String textVar = generateTextVariable();

		PropositionPattern premise = new PropositionPattern(uriVar, textVar, null, true);
		propPatterns.add(premise);
		PremisePattern premPattern = new PremisePattern(premise, raVar, PremiseType.VARIABLE);

		// ***********CONCLUSION**********

		String conclURIVar = generateIVariable();
		String conclTextVar = generateTextVariable();

		PropositionPattern conclProp = new PropositionPattern(conclURIVar, conclTextVar, null, true);
		propPatterns.add(conclProp);

		ConclusionPattern conclPattern = new ConclusionPattern(conclProp, true);

		ArgPattern argpat = new ArgPattern(varName, raVar, premPattern, conclPattern);
		conclPattern.setArgPattern(argpat);

		return argpat;
	}

	// *************TRANSLATE PROPOSITION PATTERN **********************

	private static String getCanonicalIRI(String proposition) {
		String canonical = "####";
		try {
			proposition = new String(proposition.getBytes());
			//String query = "select * from equivalences where claimText like \'%" + proposition.replace("\"", "").replace("\'", "''") + "%\';";
			String query = "select * from equivalences where claimText = " + proposition + ";";

			System.out.println("\n" + query);
			ResultSet rs = dbMngr.executeQuery(query);

			if(!rs.isAfterLast()) {
				while (rs.next()) {
					canonical = rs.getString("canonicalelement");
				}
			}
			else {
				canonical = proposition.replace("\"", "");
			}

		} catch (Exception e) {
			logicErrors.add("Internal server error");
		}
		return canonical;
	}

	public static PropositionPattern newPropositionPattern(String proposition) {
		PropositionPattern propPattern = null;

		String iVar1 = generateIVariable();
		if (SPARQLTranslator.translationMode == TranslationMode.Normal) {
			String iVar2 = generateIVariable();

			//EquivalencePattern equivPattern = newEquivalenceCondition(iVar1, iVar2);

			propPattern = new PropositionPattern(iVar1, proposition, null, false);

		} else if (SPARQLTranslator.translationMode == TranslationMode.Optimized) {

			String canonicalvalue = getCanonicalIRI(proposition);
			propPattern = new PropositionPattern(iVar1, canonicalvalue, null, false);
		}

		propPatterns.add(propPattern);
		return propPattern;
	}

	// *************TRANSLATE PREMISE PATTERN (PROPOSITION SET)
	// **********************
	public static PremisePattern premisePropSet(ArrayList<PropositionPattern> props, String raVar) {

		PremisePattern premisePattern = new PremisePattern(props, raVar, PremiseType.PROPSET);
		return premisePattern;
	}

	// *************TRANSLATE PREMISE PATTERN (VARIABLE) **********************
	public static PremisePattern premiseVar(String premVar, String raVar) {
		String uriVar = generateIVariable();

		PropositionPattern premise = new PropositionPattern(uriVar, premVar, null, true);

		PremisePattern premisePattern = new PremisePattern(premise, raVar, PremiseType.VARIABLE);

		return premisePattern;
	}

	

	// *************CONCLUSION PATTERN ***************

	public static ConclusionPattern conclVarPattern(String conclTextVar) {
		String conclURIVar = generateIVariable();

		PropositionPattern propPattern = new PropositionPattern(conclURIVar, conclTextVar, null, true);

		propPatterns.add(propPattern);

		ConclusionPattern conclPattern = new ConclusionPattern(propPattern, true);

		return conclPattern;
	}

	public static ConclusionPattern conclPropositionPattern(String conclValue) {

		PropositionPattern prop = newPropositionPattern(conclValue);

		ConclusionPattern conclPattern = new ConclusionPattern(prop, false);

		return conclPattern;
	}

	// **************PATHPATTERNS*********************//
	public static RelationPattern undercut() {
		String caVar = generateCAVariable();
		UndercutPattern undercut = new UndercutPattern("Undercut", caVar);
		return undercut;
	}
	
	public static RelationPattern undermine() {
		String caVar = generateCAVariable();
		UnderminePattern undermine = new UnderminePattern("Undermine", caVar);
		return undermine;
	}

	public static RelationPattern rebut() {
		String caVar = generateCAVariable();
		RebutPattern rebut = new RebutPattern("Rebut", caVar);
		return rebut;
	}

	public static RelationPattern attack() {
		String caVar = generateCAVariable();
		AttackPattern attack = new AttackPattern("Attack", caVar);

		return attack;
	}

	public static RelationPattern endorse() {

		EquivalencePattern equivPattern = newEquivalenceCondition("", "");
		EndorsePattern endorse = new EndorsePattern("Endorse", equivPattern);

		return endorse;
	}

	public static RelationPattern backing() {
		EquivalencePattern equiv = newEquivalenceCondition("", "");
		BackPattern backing = new BackPattern("Back", equiv);

		return backing;
	}

	public static RelationPattern support() {
		EquivalencePattern equiv = newEquivalenceCondition("", "");
		SupportPattern support = new SupportPattern("Support", equiv);

		return support;
	}

	public static ArgPattern premVarExists(String premVar) {


		for (DialoguePattern dp : dpList) {
			if (dp.isArgPattern) {
				if (dp.getAp().getPremisePattern().getType() == PremiseType.VARIABLE
						&& dp.getAp().getPremisePattern().getPropPattern().getText().compareTo(premVar) == 0)
					return dp.getAp();
			} else if (dp.isPathPattern) {
				PathPattern pp = dp.getPpCol().getPathSet().get(dp.getPpCol().getPathSet().size() - 1);
				for (RelationPattern rel : pp.relSequence) {
					ArgPattern ap1 = rel.getAp1();
					ArgPattern ap2 = rel.getAp2();

					if (ap1.getPremisePattern().getType() == PremiseType.VARIABLE
							&& ap1.getPremisePattern().getPropPattern().getText().compareTo(premVar) == 0)
						return ap1;
					if (ap2.getPremisePattern().getType() == PremiseType.VARIABLE
							&& ap2.getPremisePattern().getPropPattern().getText().compareTo(premVar) == 0)
						return ap2;
				}
			}
		}
		logicErrors.add("Premise variable " + premVar + " is not referring to some premise variable.\n");
		return null;
	}



	public static boolean logicErrorsExist() {
		if (logicErrors.size() > 0) {
			return true;
		}
		return false;
	}

	public static ArrayList<String> getLogicErrors() {
		return logicErrors;
	}

	public static void setLogicErrors(ArrayList<String> logicErrors) {
		SPARQLTranslator.logicErrors = logicErrors;
	}
	
	
	/**
	 * This function translates the list of dialogue patterns generated during the 
	 * parsing into the final SPARQL query.
	 * @return the SPARQL query
	 */
	public static String generateSPARQLQuery() {

		StringBuilder str = new StringBuilder();
		String rdfPrefix = Names.rdf + ":" + Names.rdfNS;
		String aifPrefix = Names.aif + ":" + Names.aifNS;
			 
		str.append("prefix " + rdfPrefix + "\n" + "prefix " + aifPrefix + "\n"+
		"SELECT * \n");

		//str.append("WHERE {\n GRAPH ?g {\n");
		//for (String graph : graphs) {
		//	str.append("FROM <http://" + graph + ">\n");
		//}
		
		
		//depending on the mode, SPARQL targets either the named graph before the 
		//optimization (http://aifdb) or the named graph with the data for the optimization
		// (http://aifdb.canonical)
		if(translationMode == Normal) {
			str.append("FROM " + Names.aifNamedGraph + "\n");
		} else if(translationMode == Optimized) {
			str.append("FROM " + Names.canonicalNamedGraph + "\n");
		}
		str.append("WHERE {\n ");
		
		for (DialoguePattern dp : dpList) {
			//if the dialogue pattern is a single argument pattern
			if (dp.isArgPattern) {
				ArgPattern ap = dp.getAp();
				str.append(ap.getSparqlRepresentation());
				
			} else if (dp.isPathPattern) {
				
			//	System.out.println(dp.getPpCol().toString());
				
				PathPatternCollection pathSet = dp.getPpCol();
				
				// iteration of the path patters				
				for (int j = 0; j < pathSet.getPathSet().size(); j++) {
					if (pathSet.getPathSet().size()>1 && j <= pathSet.getPathSet().size()-1) 
						str.append("{\n");


					ArgPattern firstAP = pathSet.getPathSet().get(0).getRelSequence().get(0).getAp1();
					str.append(firstAP.getSparqlRepresentation());


					PathPattern path = (PathPattern) pathSet.getPathSet().get(j);

					//for each path pattern. iterate the relation sequence
					for (int i = 0; i < path.getRelSequence().size(); i++) {
						RelationPattern relPattern = path.getRelSequence().get(i);
						str.append(relPattern.getAp2().getSparqlRepresentation());
						//str.append(relPattern.getSparqlRepresentation());
						//str.append(relPattern.getSparqlRepresentationSimple());
						str.append(relPattern.getSparqlRepresentation_simplest());
					}
  

					if (j < pathSet.getPathSet().size()-1) { 
						str.append("} UNION");
					}
					if(pathSet.getPathSet().size()>1 && j == pathSet.getPathSet().size()-1) {
						str.append("}\n"); 
					}
				}
			}
		} 
		str.append("}");
		
		str.append("limit 1000 offset " + limitOffset);
	
		return str.toString();
	}
	
	public static Variable findVariable(String name) {
		for(Variable var : SPARQLTranslator.variables) {
			if(var.name.compareTo(name) == 0) {
				return var;
			}
		}
		return null;
	}


}
