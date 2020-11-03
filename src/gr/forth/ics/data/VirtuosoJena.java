package gr.forth.ics.data;

import java.nio.channels.NonWritableChannelException;
import java.util.Iterator;

import org.apache.jena.query.Query;
import org.apache.jena.query.QueryExecution;
import org.apache.jena.query.QueryExecutionFactory;
import org.apache.jena.query.QueryFactory;
import org.apache.jena.query.QuerySolution;
import org.apache.jena.query.ResultSet;
import org.apache.jena.sparql.engine.http.QueryEngineHTTP;


public class VirtuosoJena {
	
	public static String queryEndpoint(String szQuery, String szEndpoint) throws Exception  {
		// Create a Query with the given String
		StringBuilder str = new StringBuilder();
		Query query = QueryFactory.create(szQuery);

		// Create the Execution Factory using the given Endpoint
		QueryExecution qexec = QueryExecutionFactory.sparqlService(
				szEndpoint, query);

		// Set Timeout
		((QueryEngineHTTP)qexec).addParam("timeout", "10000");


		// Execute Query
		int iCount = 0;
		ResultSet rs = qexec.execSelect();
		while (rs.hasNext()) {
			// Get Result
			QuerySolution qs = rs.next();

			// Get Variable Names
			Iterator<String> itVars = qs.varNames();

			// Count
			iCount++;
			System.out.println("Result " + iCount + ": ");

			// Display Result
			while (itVars.hasNext()) {
				String szVar = itVars.next().toString();
				String szVal = qs.get(szVar).toString();

				System.out.println("[" + szVar + "]: " + szVal);
				str.append("[" + szVar + "]: " + szVal + "\n");
			}

		}

		return str.toString();
	} // End of Method: queryEndpoint()


}
