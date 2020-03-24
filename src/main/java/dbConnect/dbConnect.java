package dbConnect;
import java.sql.*;
import java.util.*;

import javax.swing.JOptionPane;

public class dbConnect {
	private List<String> tableList = new ArrayList<String>();
	private String tableName;
	private String[] tableListS;
	private ResultSetMetaData md;
	private String[] columnNames;
	private int[] columnTypes;
	private List rows;
	
	
	public dbConnect(Connection con, String username){
		//tableList = setTableListS(con);
		tableListS = setTableListS(con);
		new dbDisplay(tableListS, con, username);
}
	public dbConnect(Connection con, String username, String[] tableLst) {
		tableListS = tableLst;
		String slowo = "BUTY_KLIENCI";
		System.out.println("wchodzimy do konstruktora");
		setTable(con,slowo);
}
/*	public List<String> setTableList(Connection con) {
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
	}*/
	public String[] setTableListS(Connection con) {
		Statement stmt;
		ResultSet rs;
		String query = "SELECT TABLE_NAME FROM USER_TABLES ORDER BY 1";
		List<String> tables = new ArrayList<String>();
		String[] table;
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
		table = new String[(tables.size())];
		for(int i = 0; i < table.length; i++) {
			table[i] = tables.get(i);
		}
		return table;
	}
	public void setTable(Connection con, String tablename) {

		Statement stmt;
		ResultSet rs;
		String query = "SELECT * FROM " + tablename + "";
		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery(query);
			md = rs.getMetaData();
			int cc = md.getColumnCount();
			columnNames = new String[cc];
			columnTypes = new int[cc];
			for(int col = 0; col < cc; col++) {
				columnNames[col] = md.getColumnName(col+1);
				columnTypes[col] = md.getColumnType(col+1);
			}
			rows = new ArrayList();
			while (rs.next()) {
				List row = new ArrayList();
				for(int i = 1; i <= cc; i++) {
					row.add(rs.getObject(i));
					
				}
				rows.add(row);
			}
			rs.close();
			stmt.close();
		}catch(Exception e) {
			JOptionPane.showMessageDialog(null, e);
		}
		
	}
	}

