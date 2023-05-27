package main;
import java.awt.event.ActionEvent;

import javax.swing.*;
import javax.swing.JFrame;
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.lang.*;
import java.util.*;
import java.sql.*;

public class Login implements ActionListener{
	JFrame j;
	JPanel p1,p2,p3,p4,p5,p6;
	JLabel l1,l2,l3,l4,l5;
	JButton b1,b2,b3,b4,b5;
	JTextField t1,t2,t3,t4,t5;
	JPasswordField ps1;
	public Login() {
		j=new JFrame();
		
		p1=new JPanel();
		l1=new JLabel("Login to continue");
		l2=new JLabel("Username");
		l3=new JLabel("Password");  
		
		b1=new JButton("Login");
		b1.addActionListener(this);

		
		t1=new JTextField(8);
		ps1=new JPasswordField(8);
		//ps1.setBounds(, 0, 0, 0);
		ps1.setSize(200, 30);
		
		
		p1.add(l1);
		p1.setBounds(0,0,400,30);
//		p1.setBackground(Color.blue);

		p2=new JPanel();
		p2.add(l2);
		p2.add(t1);
		p2.setBounds(0,40,400,30);
		
//		p2.setBackground(Color.red);

		
		p3=new JPanel();
		p3.add(l3);
		p3.add(ps1);
		p3.setBounds(0,80,400,30);
		
//		p3.setBackground(Color.green);
		l5=new JLabel("      ");
		p4=new JPanel();
		p4.add(b1);
		p5=new JPanel();
		p5.add(l5);
		p4.setBounds(0,120,400,30);
//		p4.setBackground(Color.magenta);
		p5.setBounds(0,150,400,30);
		
		
		
		
		j.add(p1);
		j.add(p2);
		j.add(p3);
		j.add(p4);
		j.add(p5);
		j.setSize(400,300);
		j.setLayout(null);
		j.setVisible(true);
		
		
		
		
	}
	public void actionPerformed(ActionEvent e) {
		String s=e.getActionCommand();
		String uname=t1.getText();
		String pass=String.valueOf(ps1.getPassword());
		System.out.println(s+"----"+uname+"----"+pass+"----");
		if(uname.equals("Admin") && pass.equals("123")) {
			Main m=new Main();
//			m.setVisible(true);
		}
		else {
			l5.setText("Invalid credentials");
		}
	}
	public static void main(String a[]) {
		Login l=new Login();
//		Main m=new Main();
		
	}
	
}
