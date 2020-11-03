package gr.forth.ics.argql.canonical;

import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

import org.stringtemplate.v4.compiler.STParser.ifstat_return;

import gr.forth.ics.data.DatabaseManager;
import gr.forth.ics.data.VirtuosoJena;

public class RelationalDatabaseManager {

	Connection con;
	
	public RelationalDatabaseManager() {
		createConnection();
		
	}
	
	private void createConnection() {
		try {
			Properties prop = new Properties();
			InputStream inputStream = DatabaseManager.class.getClassLoader().getResourceAsStream("project.properties");
			prop.load(inputStream);
			
			String ip = (String)prop.get("Repository_IP");
			String port = (String)prop.get("MYSQL_Port");
			String schema = (String)prop.get("MYSQL_Schema");
			String username = (String)prop.get("MYSQL_Username");
			String password = (String)prop.get("MYSQL_Password");
			
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			con = DriverManager.getConnection("jdbc:mysql://"+ip+":"+port+"/"+schema+"?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC", username, password);
			System.out.println("-->" + con.getSchema());
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public ResultSet executeQuery(String query) {
		Statement stmt;
		ResultSet rs = null;
		try {
			if(con  == null) {
				createConnection();
			}
			stmt = con.createStatement();
			rs = stmt.executeQuery(query);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rs;
	}

	public void executeUpdateQuery(String query) {
		Statement stmt;
		int rs;
		try {
			stmt = con.createStatement();
			stmt.executeUpdate(query);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void terminate() {
		try {
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void main(String args[]) {
		RelationalDatabaseManager dbDatabaseManager = new RelationalDatabaseManager();
		
	}
}
