package dbConnect;


import java.awt.*;
import java.sql.*;

import javax.swing.*;

public class Main {
	
	public static void main(String[] args) {
		String driver = "oracle.jdbc.driver.OracleDriver";
		String hostname = "soil-festivities.wsisiz.edu.pl";
		String username;
		String password;	
		JTextField user = new JTextField(10);
		JTextField pass = new JTextField(10);
		JPanel loginPanel = new JPanel(new GridLayout(2,2));
		
		loginPanel.add(new JLabel("Username:"));
		loginPanel.add(user);
		loginPanel.add(new JLabel("Password:"));
		loginPanel.add(pass);
		
		int result = JOptionPane.showConfirmDialog(null, loginPanel,"Connecting database:",JOptionPane.OK_CANCEL_OPTION);
		if(result == JOptionPane.OK_OPTION) {
			username =  user.getText();
			password =  pass.getText();
			connect(driver, hostname, username, password);
		}
		
	}
	public static void connect(String driver, String hostname, String username, String password) {
		try {
			Class.forName(driver);
			Connection con = DriverManager.getConnection(
					"jdbc:oracle:thin:@"+ hostname + ":1521:orcl", username, password);
			new dbConnect(con, username);
			con.close();
		}catch(Exception e) {
			JOptionPane.showMessageDialog(null,  e, "Error", JOptionPane.ERROR_MESSAGE);
			
		}
	}
}
