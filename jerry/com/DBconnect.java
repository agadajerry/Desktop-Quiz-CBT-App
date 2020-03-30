package quiz.jerry.com;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBconnect {
	public static Connection getConnection()
	 {
		 Connection conn = null;
		 try
		 {

			 //String dbURL = "jdbc:mysql://localhost:3306/myquiz","username","password";
			 
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				
				 conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/myquiz"
					 		+ "?useTimezone=true&serverTimezone=UTC","root","12/22513/ue");
				 System.out.println("connection sucessful");
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
		 }catch(SQLException ex)
		 {
			 System.out.println(ex.getMessage());
		 }
		 return conn;
	 }
	

}
