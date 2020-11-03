package gr.forth.ics.restservices;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FilenameFilter;
import java.io.IOException;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Properties;
import java.util.logging.Logger;

import javax.ws.rs.PathParam;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import org.apache.commons.io.FilenameUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import gr.forth.ics.data.DatabaseManager;
import gr.forth.ics.utils.Utilities;

//import gr.forth.ics.aifdb.DatabaseManager;

@Path("/aif/")
public class DataBaseServices {

	private static final Logger LOGGER = Logger.getLogger( DataBaseServices.class.getName() );
	//String databasePath = "E://Argument Base//AIFdb";
	
	@GET
	@Produces("application/json")
	public Response showCollection() throws JSONException, FileNotFoundException, IOException  {
		Properties dbprop = new Properties();
		dbprop.load(new FileInputStream("C:\\Users\\dzograf\\Dropbox\\Work\\PHD\\Code\\ArgQLRestServices\\conf\\database.properties"));
		
		String databasePath = dbprop.getProperty("databasePath");
		File folder = new File(databasePath);
		File[] listOfFiles = folder.listFiles();
		
		ArrayList<String> collection = new ArrayList<String>();
		
		for(int i=0; i<listOfFiles.length; i++) {
			if(!collection.contains(listOfFiles[i].getName())) {
				collection.add(listOfFiles[i].getName());
			}
		}
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("collection", collection);
		
		//String result = "@Produces(\"application/json\") Output: \n\n" + jsonObject;
		return Response.status(200).entity(jsonObject.toString())
				.header("Access-Control-Allow-Origin", "*")
				.header("Access-Control-Allow-Methods", "*")
				.header("Access-Control-Allow-Headers", "*").build();
	}
	

	@GET
	@Produces("application/json")
	@Path("{fname}")
	public Response showCollectionParam(@PathParam("fname")String fname) throws JSONException, FileNotFoundException, IOException  {
		
		Properties dbprop = new Properties();
		dbprop.load(new FileInputStream("C:\\Users\\dzograf\\Dropbox\\Work\\PHD\\Code\\ArgQLRestServices\\conf\\database.properties"));
		
		String databasePath = dbprop.getProperty("databasePath");
		String filepath = databasePath + "//" + fname;
		File folder = new File(filepath);

		ArrayList<String> collection = new ArrayList<String>();

		File[] listOfFiles = folder.listFiles(new FilenameFilter() {
			@Override
			public boolean accept(File dir, String name) {
				// TODO Auto-generated method stub
				 return name.toLowerCase().endsWith(".rdf");
			}
		});
		
		
		for(int i=0; i<listOfFiles.length; i++) {
			String nameWithoutExt = FilenameUtils.removeExtension(listOfFiles[i].getName());
			if(!collection.contains(nameWithoutExt)) {
				collection.add(nameWithoutExt);
			}
		}
		
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("collection", collection);
		
		String result = "@Produces(\"application/json\") Output: \n\n" + jsonObject;
		return Response.status(200).entity(result).build();
	}
	
	@GET
	@Produces("application/json")
	@Path("/load/{collection}/{file}")
	public Response loadFileInVirtuoso(@PathParam("collection") String collection, @PathParam("file")String file) throws Exception {
		
		Properties dbprop = new Properties();
		dbprop.load(new FileInputStream("C:\\Users\\dzograf\\Dropbox\\Work\\PHD\\Code\\ArgQLRestServices\\conf\\database.properties"));
		
		String databasePath = dbprop.getProperty("databasePath");
		
		String filename = databasePath + "//" + collection + "//" + file +".rdf";
		String graph = "http://"+collection+"."+file;
		
		DatabaseManager.importFile(filename, graph);
		System.out.println("Loaded!!");
		String result = "@Produces(\"application/json\") Output: File loaded! Graph:" +graph;
		return Response.status(200).entity(result).build();
	}
	
	public Response loadDataBase() throws Exception {
		Properties dbprop = new Properties();
		dbprop.load(new FileInputStream("C:\\Users\\dzograf\\Dropbox\\Work\\PHD\\Code\\ArgQLRestServices\\conf\\database.properties"));
		
		String databasePath = dbprop.getProperty("databasePath");
		String graph = "http://aif.db";
		
		File folder = new File(databasePath);
		File[] listOfFolders = folder.listFiles();
		
		ArrayList<String> collection = new ArrayList<String>();
		
		for(int i=0; i<listOfFolders.length; i++) {
			File[] listOfFiles = listOfFolders[i].listFiles(new FilenameFilter() {
				@Override
				public boolean accept(File dir, String name) {
					return name.toLowerCase().endsWith(".rdf");
				}
				
			});
			
			for(int j=0; j<listOfFiles.length; j++) {
				DatabaseManager.importFile(listOfFiles[j].getName(), graph);
			}
		}
		
		return Response.status(200)	
				.header("Access-Control-Allow-Origin", "*")
				.header("Access-Control-Allow-Methods", "*")
				.header("Access-Control-Allow-Headers", "*").build();
	}
	
	@GET
	@Produces("application/json")
	@Path("/exec/{query}")
	public Response executeSPARQLQuery(@PathParam("query") String query) throws Exception {
		System.out.print("SPARQL: " + query);
		ResultSet results = DatabaseManager.executeSparqlQuery(query);
		
		

		JSONArray jsonArray = Utilities.convertToJSON(results);	
		
		String result = "@Produces(\"application/json\") Output: " + jsonArray.toString();
		return Response.status(200).entity(result).build();
		
	}
	
}
