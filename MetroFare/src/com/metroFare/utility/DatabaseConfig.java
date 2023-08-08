package com.metroFare.utility;
import java.sql.*;

public class DatabaseConfig {

	Connection con;
	Statement s;
	public DatabaseConfig()
	{
		try
		{
			Class.forName("com.mysql.jdbc.Driver"); //load driver( xampp )
		 	
	         con= DriverManager.getConnection("jdbc:mysql://localhost:3306/bookstore","root","saurabh"); //url connection 
	      
		}
		
		catch(Exception d) 
		{d.printStackTrace();
			System.out.println(d.getMessage());
		}
	}
	
	public Connection getCon()
	{
		return con;
		
	}
}
