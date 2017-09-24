package Model;

import java.sql.Connection;
import java.sql.DriverManager;

public class SqliteConnection {
	/*
	 * Connect to Sqlite database
	 * The connection between database and java
	 */
	public static Connection Connector(){
		try{
			Class.forName("org.sqlite.JDBC");
			Connection conn = DriverManager.getConnection("jdbc:sqlite:Bill.sqlite");
			return conn;
		}catch(Exception e){
			System.out.println(e);
			return null;
		}
	}
}
