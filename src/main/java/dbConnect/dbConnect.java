package dbConnect;
import java.sql.*;
import java.util.*;

import javax.swing.*;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

public class dbConnect {
	private String[] tableListS;
	private ResultSetMetaData md;
	private String[] columnNames;
	private int[] columnTypes;
	private DefaultTableModel model = new DefaultTableModel();
	
	
	public dbConnect(Connection con){
		tableListS = setTableListS(con);

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
				model.addColumn(columnNames[col]);

			}
			while (rs.next()) {
				Vector row = new Vector<Object>();
				for(int i = 1; i <= cc; i++) {
					row.add(rs.getObject(i));
				}
				model.addRow(row);
			}
			rs.close();
			stmt.close();
		}catch(Exception e) {
			JOptionPane.showMessageDialog(null, e);
		}
		return model;
	}
	}

