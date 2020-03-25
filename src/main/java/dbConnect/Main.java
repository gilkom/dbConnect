package dbConnect;


import java.awt.*;
import java.sql.*;

import javax.swing.*;

public class Main {
	
	public static void main(String[] args) {
		String driver = "oracle.jdbc.driver.OracleDriver";
		String hostname;
		String port;
		String sid;
		String username;
		String password;	
		JTextField user = new JTextField(10);
		JTextField pass = new JTextField(10);
		JTextField hostnameF = new JTextField(10);
		JTextField portF = new JTextField(10);
		JTextField sidF = new JTextField(10);
		JPanel loginPanel = new JPanel(new GridLayout(6,2));
		
		loginPanel.add(new JLabel("Username:"));
		loginPanel.add(user);
		loginPanel.add(new JLabel("Password:"));
		loginPanel.add(pass);
		loginPanel.add(new JLabel("Hostname:"));
		loginPanel.add(hostnameF);
		loginPanel.add(new JLabel("Port:"));
		loginPanel.add(portF);
		loginPanel.add(new JLabel("SID:"));
		loginPanel.add(sidF);
		
		int result = JOptionPane.showConfirmDialog(null, loginPanel,"Connecting database:",JOptionPane.OK_CANCEL_OPTION);
		if(result == JOptionPane.OK_OPTION) {
			username =  user.getText();
			password =  pass.getText();
			hostname =  hostnameF.getText();
			port =  portF.getText();
			sid =  sidF.getText();
			new dbDisplay(driver, hostname,port, sid,  username, password);
		}
		
	}
}
