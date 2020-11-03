package gr.forth.ics.argql.canonical;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStream;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.apache.commons.io.FilenameUtils;

//import java.sql.*;

import org.apache.jena.ontology.Individual;
import org.apache.jena.ontology.OntModel;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.Property;
import org.apache.jena.rdf.model.RDFReader;
import org.apache.jena.rdf.model.RDFWriter;
import org.apache.jena.rdf.model.ResIterator;
import org.apache.jena.rdf.model.Resource;
import org.apache.jena.rdf.model.Statement;
import org.apache.jena.rdf.model.StmtIterator;
import org.openrdf.rio.RDFFormat;
import org.stringtemplate.v4.compiler.STParser.ifstat_return;

import com.fasterxml.jackson.databind.ser.std.StaticListSerializerBase;

import gr.forth.ics.data.DatabaseManager;

public class KBTransformation {

	static OntModel newmodel;
	static Model model;


	static int currentCanonicalElement = 60320;
	
	public KBTransformation() {
		
	}
	
	//
	private static ArrayList<Equivalence> transformEquivPairs(ArrayList<EquivPair> initial){
		ArrayList<Equivalence> newEquivs = new ArrayList<Equivalence>();
		for (int i=0; i<initial.size(); i++) {
			EquivPair eqp = initial.get(i);
			Equivalence eq = new Equivalence();
			eq.add(eqp.getI1());
			eq.add(eqp.getI2());
			
			newEquivs.add(eq);
		}
		
		return newEquivs;
	}
	
	private static boolean haveCommonElements(Equivalence eq1, Equivalence eq2) {
		for(INode i1 : eq1.getINodes()) {
			for(INode i2 : eq2.getINodes()) {
				if(i1.getUri().compareTo(i2.getUri()) == 0 ) {
					return true;
				}
			}
		}
		return false;
	}
	
	private static Equivalence merge(Equivalence eq1, Equivalence eq2) {
		Equivalence newEq = new Equivalence();
		
		newEq.getINodes().addAll(eq1.getINodes());
		for(INode inode : eq2.getINodes()) {
			if(!newEq.getINodes().contains(inode)) {
				newEq.add(inode);
			}
		}
		
		return newEq;
	}
	
	private static boolean existsEqs(ArrayList<Equivalence> equivs) {
		for(int i=0; i < equivs.size(); i++) {
			Equivalence eq1 = equivs.get(i);
			for(int j = i+1; j < equivs.size(); j++) {
				Equivalence eq2 = equivs.get(j);
				if(haveCommonElements(eq1, eq2)) {
					return true;
				}
			}
		}
		
		return false;
	}
	
	
	
	private static ArrayList<Equivalence> fixClosure(ArrayList<Equivalence> equivalences){
		
		
		
		
		/*while(existsEqs(equivalences)) {
			for(int i=0; i < equivalences.size(); i++) {
				Equivalence eq1 = equivalences.get(i);
				for(int j = i+1; j < equivalences.size(); j++) {
					Equivalence eq2 = equivalences.get(j);
					if(haveCommonElements(eq1, eq2)) {
						Equivalence newEq = merge(eq1, eq2);
						equivalences.set(i, newEq);
						equivalences.remove(j);
					}
				}
			}
		}*/
		return equivalences;
	}
	
	private static int existsInEquivalence(ArrayList<Equivalence> equivs, String uri) {
		for(int i=0; i<equivs.size(); i++) {
			Equivalence eq = equivs.get(i);
			for(INode inode : eq.getINodes()) {
				if(inode.getUri().compareTo(uri) == 0) {
					return i;
				}
			}
		}
		return -1;
	}
	
	private static boolean inEquivalence(INode iNode, Equivalence equivalence) {
		
		for (INode node : equivalence.getINodes()) {
			if(node.getUri().compareTo(iNode.getUri()) == 0) {
				return true;
			}
		}
		return false;
	}
	
	
	private static ArrayList<Equivalence> mergeEquivalences(ArrayList<Equivalence> equivalences, int pos1, int pos2) {
		Equivalence eq1 = equivalences.get(pos1);
		Equivalence eq2 = equivalences.get(pos2);
		
		for (INode inode : eq2.getINodes()) {
			eq1.add(inode);
		}
		
		equivalences.remove(pos2);
		
		return equivalences;
	}
	
	private static ArrayList<Equivalence> addEquivalence(ArrayList<Equivalence> equivalences, INode i1, INode i2) {
		boolean added = false;

		int addedPosition = -1;
		for (int i = 0; i < equivalences.size(); i++) {
			Equivalence eq = equivalences.get(i);
	
			if(inEquivalence(i1, eq) || inEquivalence(i2, eq)) {
				if(addedPosition != -1) {
					mergeEquivalences(equivalences, addedPosition, i);
				} else {
					eq.add(i1);
					eq.add(i2);
					equivalences.set(i, eq);
					added = true;
					addedPosition = i;
				}
			}
		}
		
		if(!added) {
			Equivalence newEq = new Equivalence();
			newEq.add(i1);
			newEq.add(i2);
			equivalences.add(newEq);
		}
		return equivalences;
	}
	
	private static ArrayList<Equivalence> getEquivalences(){
		
		ArrayList<Equivalence> equivalences = new ArrayList<Equivalence>();
		
		String queryForEquivalences = "prefix rdf:<http://www.w3.org/1999/02/22-rdf-syntax-ns#>\r\n" + 
				"prefix argtech:<http://www.arg.tech/aif#>\r\n" + 
				"SELECT distinct ?i1, ?text1, ?i2, ?text2\r\n" + 
				"FROM <http://aifdb>\r\n" + 
				"WHERE {\r\n" + 
				"?i1 rdf:type argtech:I-node.\r\n" + 
				"?i1 argtech:claimText ?text1.\r\n" + 
				"?_ya1 argtech:IllocutionaryContent ?i1.\r\n" + 
				"?_ya1 argtech:Locution ?_loc1.\r\n" + 
				"?_loc1 rdf:type argtech:L-node.\r\n" + 
				"?i2 rdf:type argtech:I-node.\r\n" + 
				"?i2 argtech:claimText ?text2.\r\n" + 
				"?_ya2 argtech:IllocutionaryContent ?i2.\r\n" + 
				"?_ya2 argtech:Locution ?_loc2.\r\n" + 
				"?_loc2 rdf:type argtech:L-node.\r\n" + 
				"?_ta1 rdf:type argtech:TA-node.\r\n" + 
				"?_loc1 argtech:StartLocution ?_ta1.\r\n" + 
				"?_ta1 argtech:EndLocution ?_loc2.\r\n" + 
				"?_ya3 argtech:Anchor ?_ta1.\r\n" + 
				"?_ya3 argtech:IllocutionaryContent ?_ma1.\r\n" + 
				"?_ma1 rdf:type argtech:MA-node .\r\n" + 
				"}";
		try {
			ResultSet results = DatabaseManager.executeSparqlQuery(queryForEquivalences);
			
			int canonical = 0;
			while (results.next()) {
			
				String uri1 = results.getString("i1");
				String uri2 = results.getString("i2");
				String text1 = results.getString("text1");
				String text2 = results.getString("text2");
				
				INode i1 = new INode(uri1, text1);
				INode i2 = new INode(uri2, text2);
				
				equivalences = addEquivalence(equivalences, i1, i2);
			}
			

		} catch (Exception e) {
			e.printStackTrace();
		}
			
		return equivalences;
	}
	
	
	private static int computeCanonicalElement( RelationalDatabaseManager dbmngr, ArrayList<Equivalence> equivalences,
			String uri, String claimText, int currentCanonicalElement, String foldename) {
		int iCanonicalElem=-1;
		String str;
		
		ResultSet rs = dbmngr.executeQuery("select * from equivalences where uri=\"" + uri+ "\";");
		try {
			if(rs.next()) {
				str = rs.getString("canonicalelement");
				iCanonicalElem = Integer.valueOf(str);
			} else {
				int position = existsInEquivalence(equivalences, uri);
				
				if(position != -1) {
					int canonical = equivalences.get(position).getCanonicalElement();
					if(canonical == -1) {
						iCanonicalElem = currentCanonicalElement;
						equivalences.get(position).setCanonicalElement(iCanonicalElem);
					} else {
						iCanonicalElem = equivalences.get(position).getCanonicalElement();
					}						
				} else {
					iCanonicalElem = currentCanonicalElement;
				}

				dbmngr.executeUpdateQuery("insert into equivalences values('"+ uri + "' , '" + claimText + "'," + iCanonicalElem + ", '" + foldename + "')");
			}
		} catch (Exception e) {
			System.out.println("ERROR IN METHOD computeCanonicalElement");
			e.printStackTrace();
		}
		
		return iCanonicalElem;	
	}
	
	
	
	public static void loadAIFdb() throws Exception {
		String dbPath = "E://Argument Base//AIFdb";
		
		String graph = "http://aifdb";
		
		File folder = new File(dbPath);
		File[] listOfFolders = folder.listFiles();
		
		//ArrayList<String> collection = new ArrayList<String>();
		
		 //OntologyManager.initializeSesame();
		
		for(int i=0; i<listOfFolders.length; i++) {
			File[] listOfFiles = listOfFolders[i].listFiles(new FilenameFilter() {
				@Override
				public boolean accept(File dir, String name) {
					return name.toLowerCase().endsWith(".rdf");
				}
				
			});
			
			for(int j=0; j<listOfFiles.length; j++) {
				//DatabaseManager.importFile(listOfFiles[j].getName(), graph);
			//	 OntologyManager.sesame.importFile( listOfFiles[j].getAbsolutePath(), RDFFormat.RDFXML, graph);
			}
		}
       // OntologyManager.terminateSesame(); 
	}
	
	private static RDFWriter transformFile(File inputFile, File dstFolder, ArrayList<Equivalence> equivalences, RelationalDatabaseManager dbmngr) {
		
		model = ModelFactory.createDefaultModel();
		RDFReader arp = model.getReader("RDF/XML");

		// read the RDF/XML file
		InputStream in;
		try {
			in = new FileInputStream(inputFile.getAbsolutePath());
			arp.read(model, in, "http://www.arg.tech/aif#");

			newmodel = ModelFactory.createOntologyModel();
			RDFWriter writer = newmodel.getWriter("RDF/XML");
			writer.setProperty("showXmlDeclaration", true);
			writer.setProperty("showDoctypeDeclaration", true);
			
			model.removeNsPrefix("http");
			newmodel.setNsPrefixes(model.getNsPrefixMap());
			
			
			ResIterator iter = model.listSubjects();
			while (iter.hasNext()) {
				Resource subject = iter.next();
				boolean isINode = false;
				Property nameProperty = model.getProperty("http://www.w3.org/1999/02/22-rdf-syntax-ns#", "type");
				
				StmtIterator stmtIt = subject.listProperties(nameProperty);
				while(stmtIt.hasNext()) {
					Statement typeTriple = stmtIt.next();
					if(typeTriple.getObject().toString().compareTo("http://www.arg.tech/aif#I-node") == 0) {
						isINode = true;
					}
				}
								
				if(isINode) {
					int iCanonicalElem;
					Property claimTextProp = model.getProperty("http://www.arg.tech/aif#", "claimText");
					Statement cTextTriple = subject.getProperty(claimTextProp);
					String claimText = cTextTriple.getObject().toString();
					claimText = claimText.replace("\'", "\\'");
					
   					iCanonicalElem = computeCanonicalElement(dbmngr, equivalences, subject.getURI(), claimText, currentCanonicalElement, dstFolder.getName() + "/" +inputFile.getName());
   					if(iCanonicalElem != -1) {
   						StmtIterator propIter = subject.listProperties();
   						while(propIter.hasNext()) {
   							Statement stmt = propIter.next();
   							Individual ind = newmodel.createIndividual(subject.toString(),null);
   							if(stmt.getPredicate().getLocalName().compareTo("claimText") == 0) {
   								ind.addProperty(stmt.getPredicate(), Integer.toString(iCanonicalElem));
   							} else {
   								ind.addProperty(stmt.getPredicate(), stmt.getObject());
   							}
   						}
   						currentCanonicalElement++;
   					} else {

						System.out.println("Canonical element broke");
   						return null;
   					}
				} else {			
					StmtIterator propIter = subject.listProperties();
					while(propIter.hasNext()) {
						Statement stmt = propIter.next();
					
						Individual ind = newmodel.createIndividual(subject.toString(),null);
						ind.addProperty(stmt.getPredicate(), stmt.getObject());
					}
				}
					
			}
			
			return writer;

			/*FileOutputStream outputStream;

			String outputFilename = dstFolder.getAbsolutePath().concat(inputFile.getName());
			File folder = new File(dstFolder.getAbsolutePath());
			if(!folder.exists()) {
				folder.mkdir();
			}
			
			File fout = new File(outputFilename);
			if (fout.exists()) {
				fout.delete();
				fout = new File(outputFilename);
			}
			outputStream = new FileOutputStream(fout);
			writer.write(newmodel, outputStream, "");
			outputStream.close();
		
			System.out.println("\n\n File " + outputFilename + " created");
			Thread.sleep(50);
			DatabaseManager.importFile(outputFilename, graph);
			System.out.println("File " + outputFilename + " uploaded");*/
		} catch (FileNotFoundException e) {
			System.out.println("!!! ERROR in writing file.....");
			e.printStackTrace();

		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	private static void saveAndUpload(RDFWriter writer, String dstFolder, String outputFile, String graph) {
		  try {
				FileOutputStream outputStream;

				String outputFilename = dstFolder.concat(outputFile);
				File folder = new File(dstFolder);
				if(!folder.exists()) {
					folder.mkdir();
				}
				
				File fout = new File(outputFilename);
				if (fout.exists()) {
					fout.delete();
					fout = new File(outputFilename);
				}
				outputStream = new FileOutputStream(fout);
				writer.write(newmodel, outputStream, "");
				outputStream.close();
			
				System.out.println("\n\n File " + outputFilename + " created");
				Thread.sleep(50);
				DatabaseManager.importFile(outputFilename, graph);
				System.out.println("File " + outputFilename + " uploaded");
			} catch (FileNotFoundException e) {
				System.out.println("!!! ERROR in writing file.....");
				e.printStackTrace();

			} catch (IOException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}
	}
	

	/*private static ArrayList<Equivalence> retrieveCanElemInEquivalences(RelationalDatabaseManager dbmngr, ArrayList<Equivalence> equivalences) {
		String str;
		
		for (int i=0; i<equivalences.size(); i++) {
			Equivalence equivalence = equivalences.get(i);
			for (INode iNode : equivalence.getINodes()) {
				ResultSet rs = dbmngr.executeQuery("select canonicalelement from equivalences where uri=\"" + iNode.getUri() + "\";");
				try {
					if(rs.next()) {
						str = rs.getString("canonicalelement");
						int canonical = Integer.valueOf(str);
						equivalence.setCanonicalElement(canonical);
						equivalences.set(i, equivalence);
						break;
					} 
					
				} catch (Exception e) {
					System.out.println("ERROR IN METHOD retrieveCanonicalElementInEquivalences");
					e.printStackTrace();
				}
			}
		}
		return equivalences;
	}*/
	
	public static void main(String[] args) {
		
		RelationalDatabaseManager dbmngr = new RelationalDatabaseManager();
		
		ArrayList<Equivalence> equivalences = getEquivalences();
		
	//	equivalences = retrieveCanElemInEquivalences(dbmngr, equivalences);
		DatabaseManager.initializeSesame();
		
		String sourceDirectory = "/Users/admin/Desktop/ArgQLDatasets/AIFdb_new/";
		String dstDirectory = "/Users/admin/Desktop/ArgQLDatasets/AIFdbCanonical_new/";
		
		
		File folder = new File(sourceDirectory);
		File[] listOfFolders = folder.listFiles();
		
	   
		outerloop:
		for(int i=0; i<listOfFolders.length; i++) {
			File[] listOfFiles = listOfFolders[i].listFiles(new FilenameFilter() {
				@Override
				public boolean accept(File dir, String name) {
					return name.toLowerCase().endsWith(".rdf");
				}
				
			});
			if(listOfFiles != null) {
				for(int j=0; j<listOfFiles.length; j++) {
					String dstFolder = dstDirectory.concat(listOfFolders[i].getName()+"/");
					RDFWriter writer = transformFile(listOfFiles[j], listOfFolders[i], equivalences, dbmngr);
					if(writer != null) {
						saveAndUpload(writer, dstFolder, listOfFiles[j].getName(), "http://aifdb.canonical");		
					
						String parentFile = listOfFiles[j].getParent();
		
						File renamedFile = new File( parentFile + "/*" + listOfFiles[j].getName());

						if (listOfFiles[j].renameTo(renamedFile)) {
							System.out.println("Renamed file " +listOfFiles[j].getName());
						} else {
							System.out.println("Failed to rename file");
						}
					} else {
						break outerloop;
					}
				}
				File destFile = new File(listOfFolders[i].getAbsolutePath()+"*");

				if (listOfFolders[i].renameTo(destFile)) {
					System.out.println("File renamed successfully");
				} else {
					System.out.println("Failed to rename file");
				}
			}
		}
		
		dbmngr.terminate();
		DatabaseManager.terminateSesame(); 
			
		System.out.println("AIFdb uploaded successfully..");
		
	}

}
