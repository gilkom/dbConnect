package dbConnect;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.util.List;

import javax.swing.*;

public class dbDisplay {
	private Connection conec;
	private String[] tabL;
	private String user;
	private JFrame frame;
	private JPanel panel;
	private JPanel panelNorth;
	private JPanel panelCenter;
	private JLabel comboLabel;
	private JComboBox<String> comboTable;
	private JButton show;
	private JScrollPane scr;
	private JTable table = new JTable();
	
	public dbDisplay(String[] tablesList,Connection con, String username) {
		conec = con;
		tabL = tablesList;
		frame = new JFrame("dbConnect");
		panel = new JPanel(new BorderLayout());
		panelNorth = new JPanel();
		panelCenter = new JPanel();
		comboLabel = new JLabel("Choose table:");
		comboTable = new JComboBox<String>(tablesList);
		show = new JButton("Show");
		show.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				e
				}
				
			}
		});
		scr = new JScrollPane();
		scr.setPreferredSize(new Dimension(600,400));
		
		new dbConnect(con, username, tablesList);
		
		
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
	void execute() {
		dbConnect dbc = new dbConnect(conec, username, tab);
	}
}
