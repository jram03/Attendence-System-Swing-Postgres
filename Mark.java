package main;
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.lang.*;
import java.util.*;
import java.sql.*;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;
class Node{
	String name;
	JCheckBox cb;
}
public class Mark {
	JFrame j;
	JTable jt,jt1;
	JPanel p1,p2,p3,p4;
	JCheckBox jc[];
	public Mark() {
		j=new JFrame();
		
		String data[][]=new String[1000][2];
		String data1[][]=new String[1000][2];
		String column[]= {"Name" , "No of days present"};
		jc=new JCheckBox[1000];
		
		p1=new JPanel();
		
//		Connection c=null;
		int cx=0,dx=0,cx1=0,dx1=0;
		try {
			
			Connection c = null;
			Statement stmt = null,stmt1=null;
//			String ss=e.getActionCommand();
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
		    		 stmt = c.createStatement();
		    		 stmt1=c.createStatement();
			      ResultSet r = stmt.executeQuery("SELECT name, day1, day2, day3, day4, day5, day6, day7, day8, day9, day10, day11, day12, day13, day14, day15, day16, day17, day18, day19, day20, day21, day22, day23, day24, day25, day26, day27, day28, day29, day30, day31\r\n"
			      		+ "	FROM student,dailyattendence as e where e.id=reg_no;");
			      ResultSet r1 = stmt1.executeQuery("SELECT name, day1, day2, day3, day4, day5, day6, day7, day8, day9, day10, day11, day12, day13, day14, day15, day16, day17, day18, day19, day20, day21, day22, day23, day24, day25, day26, day27, day28, day29, day30, day31\r\n"
			      		+ "	FROM staff as s,dailyattendence as e where e.id=s.id;");
			      while(r.next()) {
			   			 
			    	  int count=0;
			    	  String day="day";
			    	  for(int i=1;i<=31;i++) {
			    		  String dd=day+i;
			    		  System.out.println(r.getString(dd));
			    		  if(r.getString(dd).equals("P")) {
			    			  count++;
			    		  }
			    	  }
			    	  
			    	  	
			    	  
			   			 data[cx][dx++]=r.getString("name");
			   			 data[cx][dx++]=count+"/"+dtf.format(now);
			   			 System.out.println("test"+r.getString("name"));
			   			 cx++;
			   			 dx=0;
			   		 }
			      while(r1.next()) {
			   			 
			    	  int count=0;
			    	  String day="day";
			    	  for(int i=1;i<=31;i++) {
			    		  String dd=day+i;
			    		  System.out.println(r1.getString(dd));
			    		  if(r1.getString(dd).equals("P")) {
			    			  count++;
			    		  }
			    	  }
			    	  
			    	  	
			    	  
			   			 data1[cx1][dx1++]=r1.getString("name");
			   			 data1[cx1][dx1++]=count+"/"+dtf.format(now);
			   			 System.out.println("test"+r1.getString("name"));
			   			 cx1++;
			   			 dx1=0;
			   		 }
//   		 System.out.println(aa);
		      }
		      catch(Exception ea) {
		    		 for(int i=0;i<cx;i++) {
		    			 for(int j=0;j<data[cx].length;j++) {
		    				 System.out.print(data[i][j]+" ");
		    			 }
		    			 System.out.println(" ");
		    		 }
		    		 System.out.println("error");
//		    		 l4.setText("Query failed");
//		    		 return;
		    	 }
   		 
   		jt=new JTable(data,column);
   		jt1=new JTable(data1,column);

		JScrollPane sp=new JScrollPane(jt);
		JScrollPane sp1=new JScrollPane(jt1);

		JLabel l1=new JLabel("Students report");
		p2=new JPanel();

		p2.add(l1);
		p2.setBounds(0,0,600,29);
		p1.add(sp);
		p1.setBounds(0,31,600,100);
		
		
		p3=new JPanel();
		JLabel ll2=new JLabel("Staffs report");
		p3.add(ll2);
		p3.setBounds(0,140,600,30);
		
		p4=new JPanel();
		p4.add(sp1);
		p4.setBounds(0,171,600,100);
		
		
		
		
		
		
		
		j.add(p2);
		j.add(p1);
		j.add(p3);
		j.add(p4);
		
   		
   	 }
   	 catch(Exception ea) {
   		 for(int i=0;i<cx;i++) {
   			 for(int j=0;j<data[cx].length;j++) {
   				 System.out.print(data[i][j]+" ");
   			 }
   			 System.out.println(" ");
   		 }
   		 System.out.println("erre");
//   		 return;
   	 }
		
		
//		jt=new JTable(data,column);
//		JScrollPane sp=new JScrollPane(jt);
//		p1.add(sp);
//		p1.setBounds(0,0,600,100);
//		
		
//		j.add(p1);
		
		j.setSize(600,300);
		j.setLayout(null);
		j.setVisible(true);
		
	}
	public static void main(String args[]) {
		Mark m=new Mark();
	}

}
