package dbConnect;
import java.sql.*;
import java.util.*;

import javax.swing.*;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

public class dbConnect {
	private List<String> tableList = new ArrayList<String>();
	private String tableName;
	private String[] tableListS;
	private ResultSetMetaData md;
	private String[] columnNames;
	private int[] columnTypes;
	private List rows;
	private DefaultTableModel model = new DefaultTableModel();
	
	
	public dbConnect(Connection con){
		tableListS = setTableListS(con);

}
	public dbConnect(Connection con, String username, String[] tableLst) {
		tableListS = tableLst;
		String slowo = "BUTY_KLIENCI";
		System.out.println("wchodzimy do konstruktora");
		setTable(con,slowo);
}

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
	public int getTableListSize() {
		return tableListS.length;
	}
	public DefaultTableModel setTable(Connection con, String tablename) {
		System.out.println("metoda w konstruktorze 1");
		Statement stmt;
		ResultSet rs;
		String query = "SELECT * FROM " + tablename + "";
		try {
			System.out.println("metoda w konstruktorze 2");
			stmt = con.createStatement();
			System.out.println("metoda w konstruktorze 2.1");
			rs = stmt.executeQuery(query);
			System.out.println("metoda w konstruktorze 2.2");
			md = rs.getMetaData();
			int cc = md.getColumnCount();
			columnNames = new String[cc];
			columnTypes = new int[cc];
			System.out.println("metoda w konstruktorze 3");
			System.out.println("column count:" + cc);
			for(int col = 0; col < cc; col++) {
				columnNames[col] = md.getColumnName(col+1);
				columnTypes[col] = md.getColumnType(col+1);
				model.addColumn(columnNames[col]);

			}
			rows = new ArrayList();
			String[] rowdata = new String[cc];
			while (rs.next()) {
				Vector row = new Vector();
				for(int i = 1; i <= cc; i++) {
					//System.out.println(rs.getObject(i));
					row.add(rs.getObject(i));
					//model.addRow(row.get(i));
				}
				rows.add(row);
				model.addRow(row);
			}
			rs.close();
			stmt.close();
			
			System.out.println("metoda w konstruktorze4");
		}catch(Exception e) {
			JOptionPane.showMessageDialog(null, e);
		}
		System.out.println("metoda w konstruktorze5");
		return model;
	}
	}

