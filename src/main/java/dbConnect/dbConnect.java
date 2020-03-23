package dbConnect;
import java.sql.*;
import java.util.*;

public class dbConnect {
	private List<String> tableList = new ArrayList<String>();
	private String tableName;
	
	
	public dbConnect(Connection con, String username){
	{
		tableList = setTableList(con);
		try {
		DatabaseMetaData md;
		md = con.getMetaData();
		System.out.println(md.getDatabaseProductName());
		System.out.println(md.getDatabaseProductVersion());
		System.out.println(md.getDriverName());
		System.out.println(md.getURL());
		System.out.println( md.getUserName());
		
		
	}catch(Exception e) {
		System.out.println(e);
	}}
}
	public List<String> setTableList(Connection con) {
		Statement stmt;
		ResultSet rs;
		String query = "SELECT TABLE_NAME FROM USER_TABLES ORDER BY 1";
		List<String> tables = new ArrayList<String>();
		
		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery(query);
			while(rs.next()) {
				tables.add(rs.getString(1));
			}
			System.out.println("Rozmiar listy: " + tables.size());
			rs.close();
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return tables;
	}
	}

