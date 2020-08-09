package javaLib;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.*;
public class AddMovies extends JInternalFrame {
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;

	public AddMovies() {
		setBounds(0, 0, 450, 300);
		setIconifiable(true);
		setClosable(true);
		setMaximizable(true);
		getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Movie Name");
		lblNewLabel.setBounds(106, 57, 77, 16);
		getContentPane().add(lblNewLabel);
		
		textField = new JTextField();
		textField.setBounds(203, 54, 116, 22);
		getContentPane().add(textField);
		textField.setColumns(10);
		
		JLabel lblStarcast = new JLabel("Starcast");
		lblStarcast.setBounds(106, 106, 56, 10);
		getContentPane().add(lblStarcast);
		
		textField_1 = new JTextField();
		textField_1.setBounds(203, 100, 116, 22);
		getContentPane().add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lblNoOfCopies = new JLabel("No. of Copies");
		lblNoOfCopies.setBounds(106, 146, 77, 16);
		getContentPane().add(lblNoOfCopies);
		
		textField_2 = new JTextField();
		textField_2.setBounds(203, 143, 116, 22);
		getContentPane().add(textField_2);
		textField_2.setColumns(10);
		
		JButton btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try
				{
					Class.forName("com.mysql.jdbc.Driver");
					Connection cn= DriverManager.getConnection("jdbc:mysql://localhost:3306/javaLMS", "root", "vivekdsingh");
					String q= "insert into movies(name, starcast, noc) values(?,?,?)";
					PreparedStatement st= cn.prepareStatement(q);
					st.setString(1,textField.getText());
					st.setString(2, textField_1.getText());
					st.setInt(3, Integer.parseInt(textField_2.getText()));
					st.executeUpdate();
					cn.close();
					JOptionPane.showMessageDialog(null, "Data Saved");
					
					
				}
				catch(Exception e)
				{
				JOptionPane.showMessageDialog(null, e.getMessage());
				}
			}
		});
		btnSave.setBounds(203, 195, 97, 25);
		getContentPane().add(btnSave);
		

	}
}
