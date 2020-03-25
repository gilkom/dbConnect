package dbConnect;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Vector;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class dbDisplay {
	private Connection con;

	private String[] tableL;
	private JFrame frame;
	private JPanel panel;
	private JPanel panelNorth;
	private JPanel panelCenter;
	private JLabel comboLabel;
	private JComboBox<String> comboTable;
	private JButton show;
	private JScrollPane scr;
	private JTable table;
	private DefaultTableModel model;
	
	public dbDisplay(String driver, String hostname,String port, String sid, String username, String password){
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(
					"jdbc:oracle:thin:@"+ hostname + ":"+port+":"+sid, username, password);
			dbConnect dbc = new dbConnect(con);
			setTableList(dbc);

			//con.close();
		}catch(Exception e) {
			JOptionPane.showMessageDialog(null,  e, "Error", JOptionPane.ERROR_MESSAGE);			
		}
		

		frame = new JFrame("dbConnect");
		panel = new JPanel(new BorderLayout());
		panelNorth = new JPanel();
		panelCenter = new JPanel();
		comboLabel = new JLabel("Choose table:");
		comboTable = new JComboBox<String>(tableL);
		show = new JButton("Show");
		show.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				dbConnect dbc = new dbConnect (con);
				
				String slowo = comboTable.getSelectedItem().toString();
				model = dbc.setTable(con, slowo);
				table.setModel(model);
				}
				
			}
		);
		table = new JTable(model);
		scr = new JScrollPane(table);
		scr.setPreferredSize(new Dimension(600,400));
		
		
		
		panelCenter.add(scr);
		panelNorth.add(comboLabel);
		panelNorth.add(comboTable);
		panelNorth.add(show);
		panel.add(panelNorth, BorderLayout.NORTH);
		panel.add(panelCenter, BorderLayout.CENTER);
		frame.add(panel);
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				frame.pack();
				frame.setVisible(true);
				frame.setLocation(100,100);
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			};
		});
	}
	public void setTableList(dbConnect dbc) {
		tableL = new String[dbc.getTableListSize()];
		tableL =  dbc.setTableListS(con);
		
	}
}
