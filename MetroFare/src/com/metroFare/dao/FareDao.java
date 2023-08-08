package com.metroFare.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.metroFare.bean.Fare;
import com.metroFare.utility.DatabaseConfig;




public class FareDao {
	
	
	
	
	
	
public boolean insert(Fare fare) {
		
		
		
		
		Fare f = new Fare();
		DatabaseConfig d= new DatabaseConfig();
	 	Connection c= d.getCon();
	 	try {
	 	PreparedStatement s= c.prepareStatement("insert into Fare values (?,?,?,?,?,?)");
	 	    s.setInt(1, fare.getFromZoneId());
			s.setInt(2, fare.getToZoneId());
			s.setInt(3, fare.getPeakHourFare());
			s.setInt(4, fare.getOffPeakHourFare());
			s.setInt(5, fare.getDailyCap());
			s.setInt(6, fare.getWeeklyCap());
			
			
		 	 s.executeUpdate();
		 	
		
		
		
	 	}catch (SQLException e) {
			e.printStackTrace();
		return false;
		
	 	}
		return true;
		
	}
	
public  ArrayList<Fare> fareList() {
    try
    {
    	DatabaseConfig d= new DatabaseConfig();
	 	Connection con= d.getCon();
   
    PreparedStatement ps=con.prepareStatement("select * from Fare");
    ResultSet rs=ps.executeQuery();
    ArrayList<Fare> fare=new ArrayList<Fare>();
    
    while(rs.next())
    {
    	Fare fa = new Fare();
    	fa.setFromZoneId(rs.getInt("FromZoneId"));
 		fa.setToZoneId(rs.getInt("ToZoneId"));
 		fa.setPeakHourFare(rs.getInt("PeakHourFare")); 
 		fa.setOffPeakHourFare(rs.getInt("OffPeakHourFare"));
 		fa.setDailyCap(rs.getInt("DailyCap"));
 		fa.setWeeklyCap(rs.getInt("WeeklyCap"));
 	
        
        fare.add(fa);
        
    }
    return fare;

    }catch (Exception e) {
		e.printStackTrace();

	
 	}
	return null;
	
	
}


	
}	
	
	

