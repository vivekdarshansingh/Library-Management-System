package javaLib;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import java.awt.BorderLayout;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class SearchMovies extends JInternalFrame {
	private JTextField textField;
	private JTable table;

	void fill()
	{
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			Connection cn= DriverManager.getConnection("jdbc:mysql://localhost:3306/javaLMS", "root", "vivekdsingh");
			String q= "select * from movies";
			PreparedStatement st= cn.prepareStatement(q);
			ResultSet rs= st.executeQuery();
			DefaultTableModel dt= new DefaultTableModel();
			dt.addColumn("MID");
			dt.addColumn("Name");
			dt.addColumn("StarCast");
			dt.addColumn("NOC");
			
			while(rs.next())
			{
				String ar[]={rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4)};
				dt.addRow(ar);
				
			}
			table.setModel(dt);
			cn.close();
			
			
			
		}
		catch(Exception e)
		{
		JOptionPane.showMessageDialog(null, e.getMessage());
		}
	}
	void fill(String t)
	{
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			Connection cn= DriverManager.getConnection("jdbc:mysql://localhost:3306/javaLMS", "root", "vivekdsingh");
			String q= "select * from movies where name like '%"+t+"%' or starcast like '%"+t+"%'";
			PreparedStatement st= cn.prepareStatement(q);
			ResultSet rs= st.executeQuery();
			DefaultTableModel dt= new DefaultTableModel();
			dt.addColumn("MID");
			dt.addColumn("Name");
			dt.addColumn("StarCast");
			dt.addColumn("NOC");
			
			while(rs.next())
			{
				String ar[]={rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4)};
				dt.addRow(ar);
				
			}
			table.setModel(dt);
			cn.close();
			
			
			
		}
		catch(Exception e)
		{
		JOptionPane.showMessageDialog(null, e.getMessage());
		}
	}
	
	public SearchMovies() {
		setBounds(0, 0, 450, 300);
		setIconifiable(true);
		setClosable(true);
		setMaximizable(true);
		
		textField = new JTextField();
		textField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				
				fill(textField.getText());
			}
		});
		getContentPane().add(textField, BorderLayout.NORTH);
		textField.setColumns(10);
		
		table = new JTable();
		getContentPane().add(table, BorderLayout.CENTER);
	fill();

	}

}
