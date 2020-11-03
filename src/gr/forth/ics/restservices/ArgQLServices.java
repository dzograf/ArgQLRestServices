package gr.forth.ics.restservices;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.sql.ResultSet;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.misc.ParseCancellationException;
import org.json.JSONObject;
import org.stringtemplate.v4.compiler.STParser.ifstat_return;

import gr.forth.ics.argql.parser.ArgQLGrammarLexer;
import gr.forth.ics.argql.parser.ArgQLGrammarParser;
import gr.forth.ics.argql.parser.ThrowingErrorListener;
import gr.forth.ics.argql.results.ResultManager;
import gr.forth.ics.argql.translator.sparql.SPARQLTranslator;
import gr.forth.ics.data.DatabaseManager;
import gr.forth.ics.data.VirtuosoJena;

@Path("/query/")
public class ArgQLServices {
	JSONObject jsonObject = new JSONObject();
	
	private String translate(String query, int offset) {
		InputStream in = new ByteArrayInputStream(query.getBytes(StandardCharsets.UTF_8));
		String sparql = "";
		
		try {
			ANTLRInputStream input = new ANTLRInputStream(in);
			ArgQLGrammarLexer lexer = new ArgQLGrammarLexer(input);
			lexer.removeErrorListeners();
			lexer.addErrorListener(ThrowingErrorListener.INSTANCE);

			CommonTokenStream tokens = new CommonTokenStream(lexer);
			ArgQLGrammarParser parser = new ArgQLGrammarParser(tokens);
			parser.removeErrorListeners();
			parser.addErrorListener(ThrowingErrorListener.INSTANCE);

			sparql = parser.query(offset).sparqlQuery;
			
		} catch (ParseCancellationException ex) {
			System.out.println("Syntax Error: " + ex.getMessage());
			jsonObject.put("sparql", "");
			jsonObject.put("offset", 0);
			jsonObject.put("results", "Syntax Error\n\n" + ex.getMessage());// jsonArray.toString());
			SPARQLTranslator.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sparql;
	}
	
	
	@POST
	@Path("/exec_optimized")
	@Produces("application/json")
	public Response executeOptimized(String data) {
		String sparql = "";
		String resultsString = "";
		
		JSONObject jsonObject = new JSONObject(data);
		//String[] inputdata = data.split(";;;");
		
		int offset = ((Integer) jsonObject.getInt("offset")).intValue();
		String query = (String) jsonObject.get("query");
		
		
		try {
			sparql = translate(query, offset);
		//    sparql = translate(query, "aif.db2", true);
			if (SPARQLTranslator.logicErrorsExist()) {
				for (String error : SPARQLTranslator.getLogicErrors()) {
					resultsString += error;
				}
				jsonObject.put("sparql", "");
				jsonObject.put("results", "Error:\n\n" + resultsString);// jsonArray.toString());

				jsonObject.put("offset", 0);
				
			} else if(sparql.compareTo("")!=0) {
				long execstart = System.currentTimeMillis();
				ResultSet results = DatabaseManager.executeSparqlQuery(sparql);
				float executionTime = (System.currentTimeMillis() - execstart);
			
				//long collectStartTime = System.currentTimeMillis();
		     	resultsString = ResultManager.collectResults(results, SPARQLTranslator.dpList);
 
				float collectTime = (System.currentTimeMillis() - execstart);
				System.out.println("Query execution time: " + executionTime);
				System.out.println("Overall execution time: " + collectTime);
				jsonObject.put("sparql", sparql);
				if(resultsString.compareTo("") == 0) {
					resultsString = "No results found";
					offset = 0;
				}
				jsonObject.put("results", resultsString);// jsonArray.toString());	
				jsonObject.put("offset", offset);
			}
		} 

		catch (Exception e) {
			jsonObject.put("results", "Internal Error: " + e.getMessage());// jsonArray.toString());	
			jsonObject.put("offset", 0);
		}
		finally {
			SPARQLTranslator.terminate();
		}
		
		return Response.status(200).entity(jsonObject.toString()).header("Access-Control-Allow-Origin", "*")
				.header("Access-Control-Allow-Methods", "*").header("Access-Control-Allow-Headers", "*").build();
	}

	@POST
	@Path("/exec")
	@Produces("application/json")
	public Response executeArgQL(String data) {
		String sparql = "";
		String query;
		int offset = 0;
		String resultsString = "";
		try {
			JSONObject dataInJSON = new JSONObject(data);
			//String[] inputdata = data.split(";;;");
			
			Integer offInteger  = (Integer) dataInJSON.getInt("offset");
			if(offInteger != null) {
				offset = offInteger.intValue();
			}
			
			Integer resultStartingNo  = (Integer) dataInJSON.getInt("resultStartingNo");
			if(resultStartingNo != null) {
				ResultManager.resultStartingNo = resultStartingNo;
			}
			
			query  = (String) dataInJSON.getString("query");
			if(query != null) {

				query = (String) dataInJSON.get("query");

				sparql = translate(query, offset);
				if (SPARQLTranslator.logicErrorsExist()) {
					for (String error : SPARQLTranslator.getLogicErrors()) {
						resultsString += error;
					}
					jsonObject.put("sparql", "");
					jsonObject.put("results", "Logic errors\n\n" + resultsString);// jsonArray.toString());
					jsonObject.put("resultStartingNo", 0);
					jsonObject.put("offset", 0);

				} else {
					if(sparql.compareTo("")!=0) {


						long execstart = System.currentTimeMillis();
			

						ResultSet results = DatabaseManager.executeSparqlQuery(sparql);
						float executionTime = (System.currentTimeMillis() - execstart) ;

						long collectStartTime = System.currentTimeMillis();
						resultsString = ResultManager.collectResults(results, SPARQLTranslator.dpList);

						float collectTime = (System.currentTimeMillis() - execstart) ;
						System.out.println("Query execution time: " + executionTime);
						System.out.println("Overall execution time: " + collectTime);
						jsonObject.put("sparql", sparql);

						

						if(resultsString.compareTo("")==0) {
							resultsString = "No results found";
							offset = 0;
							resultStartingNo = 0;
						}
						
						jsonObject.put("results", resultsString);// jsonArray.toString());
						jsonObject.put("resultStartingNo", ResultManager.resultStartingNo);
						jsonObject.put("offset", offset);
					} 
				}
			} else {
				jsonObject.put("results", "No query found");// jsonArray.toString());
				jsonObject.put("resultStartingNo", 0);
				jsonObject.put("offset", 0);
			}

		} 

		catch (Exception e) {
			resultsString = "Internal error";
			offset = 0;
			jsonObject.put("results", resultsString);// jsonArray.toString());
			jsonObject.put("resultStartingNo", 0);
			jsonObject.put("offset", offset);
			// TODO Auto-generated catch block
			//e.printStackTrace();
		}


		SPARQLTranslator.terminate();
		
		
		return Response.status(200).entity(jsonObject.toString()).header("Access-Control-Allow-Origin", "*")
				.header("Access-Control-Allow-Methods", "*").header("Access-Control-Allow-Headers", "*").build();
	}
}
