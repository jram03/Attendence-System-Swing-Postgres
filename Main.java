package main;

import javax.swing.*;

import java.awt.event.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.lang.*;
import java.util.*;
import java.sql.*;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;

public class Main implements ActionListener {
	JFrame j,k;
	JPanel p1,p2,p3,p4,p5,p6;
	JLabel l1,l2,l3,l4,l5;
	JButton b1,b2,b3,b4,b5;
	JTextField t1,t2,t3,t4,t5;
	JComboBox c1,c2;
	String s[];
	public Main() {
		j=new JFrame();
		p1=new JPanel();
		l1=new JLabel("Attendence System");
		p1.add(l1);
		p1.setBounds(0,20,500,30);
		l2=new JLabel("Enter register number :");
		l3=new JLabel("");
		b1=new JButton("Record");
	    b1.addActionListener(this);  

		t1=new JTextField("                         ");
		p2=new JPanel();
		p3=new JPanel();
		
		p2.add(l2);
		p2.add(t1);
		p2.setBounds(0,60,500,30);
		p3.add(b1);
		p3.setBounds(0,100,500,29);
		
		p4=new JPanel();
		b2=new JButton("ShowLogs");
		b2.addActionListener(this);
		p4.add(b2);
		int cc=0;
		s=new String[32];
		for(int i=1;i<=31;i++) {
			s[cc++]="day"+i;
		}
		
		c1=new JComboBox(s);
		p4.add(c1);
		
		
		p4.setBounds(0,180,500,30);
		p5=new JPanel();
		p5.add(l3);
		p5.setBounds(0,150,500,30);
		
		p6=new JPanel();
		b3=new JButton("Totalreport");
		b3.addActionListener(this);
		p6.add(b3);
		p6.setBounds(0,220,500,30);
		
		
		j.add(p1);
		j.add(p2);
		j.add(p3);
		j.add(p4);
		j.add(p5);
		j.add(p6);
		j.setSize(500,300);
		j.setLayout(null);
		j.setVisible(true);
		
	}
	public void actionPerformed(ActionEvent e){  
		Connection c = null;
		Statement stmt = null;
		String ss=e.getActionCommand();
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd");
		LocalDateTime now = LocalDateTime.now();
		String currday= "day"+dtf.format(now);

	      try {
	         Class.forName("org.postgresql.Driver");
	         c = DriverManager
	            .getConnection("jdbc:postgresql://localhost:5432/attendence",
	            "postgres", "123");
	         
	         
	         System.out.println("Opened database successfully");
		      c.setAutoCommit(false);
		      	
		      
		      if(ss.equals("Record")) {
		    	  
		    	  System.out.println("Enters if ");
		    	  
		    	  String stf= t1.getText();
		    	 String aa= c1.getItemAt(c1.getSelectedIndex())+"";
		    	 try {
		    		 stmt = c.createStatement();
		    		 String sql = "UPDATE DAILYATTENDENCE SET "+currday+"='P' WHERE ID="+stf+"";
		    		 stmt.execute(sql);
		    	 }
		    	 catch(Exception ea) {
		    		 System.out.println("erre");
		    		 l3.setText("Enter regno");
		    		 return;
		    	 }
		      

		         stmt.close();
		         c.commit();
		         c.close();
		      }
		      if(ss.equals("ShowLogs")) {
		    	  System.out.println("Enters 2md if");
		    	  k=new JFrame();
		    	  String data[][]= new String[1000][2];
		    	  String column[]= {"name","Status"};
			      String aa= c1.getItemAt(c1.getSelectedIndex())+"";
			      String data1[][]= new String[1000][2];
		    	  String column1[]= {"name","Status"};
		    	  int cx=0,dx=0,cx1=0,dx1=0;
		    	  try {
			    		 stmt = c.createStatement();
			    		 ResultSet r = stmt.executeQuery("SELECT name,"+aa+" FROM student,dailyattendence as s where s.id=reg_no");
			    		 System.out.println(aa);
			    		 while(r.next()) {
			    			 
			    			 data[cx][dx++]=r.getString("name");
			    			 data[cx][dx++]=r.getString(aa);
			    			 System.out.println("test"+r.getString("name"));
			    			 cx++;
			    			 dx=0;
			    		 }
			    		 ResultSet r1 = stmt.executeQuery("SELECT name,"+aa+" FROM staff as st,dailyattendence as s where s.id=st.id");
			    		 System.out.println(aa);
			    		 while(r1.next()) {
			    			 
			    			 data1[cx1][dx1++]=r1.getString("name");
			    			 data1[cx1][dx1++]=r1.getString(aa);
			    			 System.out.println("test"+r1.getString("name"));
			    			 cx1++;
			    			 dx1=0;
			    		 }
			    	 }
			    	 catch(Exception ea) {
			    		 for(int i=0;i<cx;i++) {
			    			 for(int j=0;j<data[cx].length;j++) {
			    				 System.out.print(data[i][j]+" ");
			    			 }
			    			 System.out.println(" ");
			    		 }
			    		 System.out.println("erre");
			    		 l4.setText("Query failed");
//			    		 return;
			    	 }
		    	  
		    	  
		    	  
		    	  
		    	  JTable jt=new JTable(data,column);    
		    	  JTable jt1=new JTable(data1,column1);    

		    	  JPanel kp1=new JPanel(); 
		    	  JPanel kp2 = new JPanel();
		    	  JPanel kp3 = new JPanel();
		    	  JPanel kp4 = new JPanel();

		    	  JScrollPane sp=new JScrollPane(jt);   
		    	  JScrollPane sp1=new JScrollPane(jt1);    

		    	  l4=new JLabel("Student Logs");
		    	  JLabel l11,l12;
		    	  l11=new JLabel("Staff Logs");
		    	  kp2.add(l4);
		    	  kp1.add(sp);    
		    	  kp2.setBounds(0,10,600,30);
		    	  kp1.setBounds(0,50,600,200);  
		    	  kp3.add(l11);
		    	  kp3.setBounds(0,260,600,20);
		    	  kp4.add(sp1);
		    	  kp4.setBounds(0,300,600,200);
		    	  
		    	  k.add(kp2);
		    	  k.add(kp1);
		    	  k.add(kp3); 
		    	  k.add(kp4); 
		    	  k.setLayout(null);
		    	  k.setSize(600,650);
		    	  k.setVisible(true);
		    	  
		    	  
//		    	  stmt.close();
//			      c.commit();
//			      c.close();
			         
		      }
		      if(ss.equals("Totalreport")) {
		    	  Mark m=new Mark();
		      }
		      
		      
	            
	      } catch (Exception ee) {
	         ee.printStackTrace();
	         System.err.println(ee.getClass().getName()+": "+ee.getMessage());
	         System.exit(0);
	      }
	      
	      
//		String ss=e.getActionCommand();
		
        l3.setText("Attendence Recorded");  
}  
	public static void main(String args[]) {
	Main m=new Main();
	System.out.println("hii");
	}
}
