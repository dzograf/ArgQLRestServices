package gr.forth.ics.data;



import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.ResultSet;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.commons.io.FilenameUtils;
import org.apache.jena.iri.ViolationCodes.Initialize;
import org.apache.jena.sparql.function.library.leviathan.factorial;
import org.openrdf.rio.RDFFormat;
import org.stringtemplate.v4.compiler.STParser.ifstat_return;

import gr.forth.ics.argql.results.ReturnElement;
import gr.forth.ics.virtuoso.JDBCVirtuosoRep;
import gr.forth.ics.virtuoso.SesameVirtRep;


public class DatabaseManager {

	public static SesameVirtRep sesame;
	public static JDBCVirtuosoRep jdbc;
	
	private static String virtuosoProperties = "virtuoso.properties";
	
	/*private static String virtuosoConfigString = "Repository_IP=virtuoso\n" + 
      		"Repository_Username=dba\n" + 
      		"Repository_Password=dba\n" + 
      		"Repository_Port=1111";*/
	/*private static String virtuosoConfigString = "Repository_IP= localhost\n" + 
			"Repository_Username=dba\n" + 
			"Repository_Password=eDu9xeSe\n" + 
			"Repository_Port=1111";*/
   /*private static String virtuosoConfigString = "Repository_IP= 134.36.36.88\n" + 
		"Repository_Username=dba\n" + 
		"Repository_Password=eDu9xeSe\n" + 
		"Repository_Port=1111";*/
	
	/* private static String virtuosoConfigString = "Repository_IP= localhost\n" + 
	"Repository_Username=dba\n" + 
	"Repository_Password=dba\n" + 
	"Repository_Port=1111";*/

   
   public static boolean isAuthenticatedAccess() {

	    try {
	 	   Properties prop=new Properties();
	 	   InputStream inputStream = DatabaseManager.class.getClassLoader().getResourceAsStream("project.properties");

	        prop.load(inputStream);
		   if(((String)prop.get("Repository_Authenticated_Access")).compareTo("yes") == 0)
				   return true;
		    
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	   return false;
   }
   
	public static SesameVirtRep getSesame() {
		return sesame;
	}

	public static void setSesame(SesameVirtRep sesame) {
		DatabaseManager.sesame = sesame;
	}

	public static void initializeSesame() {
		try {

			Properties prop = new Properties();

			InputStream inputStream = DatabaseManager.class.getClassLoader().getResourceAsStream("project.properties");

			prop.load(inputStream);
			sesame = new SesameVirtRep(prop);
		} catch (Exception ex) {
			Logger.getLogger(DatabaseManager.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	public static void terminateSesame() {
		try {
			if (sesame != null)
				sesame.terminate();
		} catch (Exception ex) {
			Logger.getLogger(DatabaseManager.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	public static void initializeJDBC() {
		try {
			Properties prop = new Properties();

			InputStream inputStream = DatabaseManager.class.getClassLoader().getResourceAsStream("project.properties");

			prop.load(inputStream);

			jdbc = new JDBCVirtuosoRep(prop);
			
		} catch (Exception ex) {
			Logger.getLogger(DatabaseManager.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	public static void terminateJDBC() {
		try {
			if (jdbc != null)
				jdbc.terminate();
		} catch (Exception ex) {
			Logger.getLogger(DatabaseManager.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	public static void importFile(String filename, String graph) throws Exception {
		try {
			initializeSesame();

			sesame.importFile(filename, RDFFormat.RDFXML, graph);
			sesame.terminate();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	public static void importRepository(String repository) throws Exception {

		File folder = new File(repository);
		File[] listOfFiles = folder.listFiles();

		initializeSesame();

		for (int j = 0; j < listOfFiles.length; j++) {
			String filenameString = listOfFiles[j].getName();
			System.out.println("Importing file: " + listOfFiles[j].getAbsolutePath());
			
			String graph = "http://" + FilenameUtils.removeExtension(filenameString);
			System.out.println("GRAPH: "+ graph);
			sesame.importFile(listOfFiles[j].getAbsolutePath(), RDFFormat.RDFXML, graph);
			System.out.println("Imported");
		}
		terminateSesame();
	}
	
	public static void deleteGraphsOfAIFdb() {
		String sourceDirectory = "/Users/admin/Desktop/ArgQLDatasets/done";
		try {
			FileWriter myWriter = new FileWriter("filename.txt");
			File folder = new File(sourceDirectory);
			File[] listofDirs = folder.listFiles();



			for(int i=0; i<listofDirs.length; i++) {
				File[] listOfFiles = listofDirs[i].listFiles(new FilenameFilter() {
					@Override
					public boolean accept(File dir, String name) {
						return name.toLowerCase().endsWith(".rdf");
					}

				});

				for(int j=0; j<listOfFiles.length; j++) {
					String graph = "http://aif/" + listofDirs[i].getName() + "/" + FilenameUtils.removeExtension(listOfFiles[j].getName());
					try {
						//String filenameString = listOfFiles[j].getAbsolutePath();
						//importFile(filenameString, graph);
						initializeJDBC();
						jdbc.clearGraph(graph, false);
						terminateJDBC();
					} catch (Exception e) {

						myWriter.write("File " + listofDirs[i].getName() + "/" + listOfFiles[j].getName() + " not deleted.");
					}
					//	 OntologyManager.sesame.importFile( listOfFiles[j].getAbsolutePath(), RDFFormat.RDFXML, graph);
				}
				
				
			}

			myWriter.close();
		}catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	
	public static void importAIFdb(String sourceDirectory, String graph) {
		try {
			FileWriter myWriter = new FileWriter("importErrors.txt");
			File folder = new File(sourceDirectory);
			File[] listofDirs = folder.listFiles();



			for(int i=0; i<listofDirs.length; i++) {
				File[] listOfFiles = listofDirs[i].listFiles(new FilenameFilter() {
					@Override
					public boolean accept(File dir, String name) {
						return name.toLowerCase().endsWith(".rdf");
					}

				});
				if(listOfFiles != null) {
					for(int j=0; j<listOfFiles.length; j++) {
						//	String graph = "http://aif/" + listofDirs[i].getName() + "/" + FilenameUtils.removeExtension(listOfFiles[j].getName());
						try {
							String filenameString = listOfFiles[j].getAbsolutePath();
							importFile(filenameString, graph);
						} catch (Exception e) {

							myWriter.write("File " + listofDirs[i].getName() + "/" + listOfFiles[j].getName() + " not written.");
						}
						//	 OntologyManager.sesame.importFile( listOfFiles[j].getAbsolutePath(), RDFFormat.RDFXML, graph);
					}

					File destFile = new File(listofDirs[i].getAbsolutePath()+"*");

					if (listofDirs[i].renameTo(destFile)) {
						System.out.println("File renamed successfully");
					} else {
						System.out.println("Failed to rename file");
					}
				}
			}

			myWriter.close();
		}catch (Exception e) {
			// TODO: handle exception
		}
	}

	public static ResultSet executeSparqlQuery(String query) throws Exception {
		ResultSet results = null;
		if(isAuthenticatedAccess()) {
			initializeJDBC();
			results = jdbc.executeSparqlQuery(query, true);
		} else {
			Properties prop=new Properties();  
			InputStream inputStream = DatabaseManager.class.getClassLoader().getResourceAsStream("project.properties");

	        prop.load(inputStream);

			
			String endpoint = (String)prop.get("Repository_IP");
			VirtuosoJena.queryEndpoint(query, endpoint);
		}
		// terminateJDBC();

		return results;
	}
	
	public static void main(String args[]) {
		try {

		//	deleteGraphsOfAIFdb();
			
			importAIFdb("/Users/admin/Desktop/ArgQLDatasets/AIFdb_new", "http://aifdb");
		//	importFile("/Users/admin/Desktop/ArgQLDatasets/AIFdb_new/araucaria/7.rdf", "http://7");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	

}
