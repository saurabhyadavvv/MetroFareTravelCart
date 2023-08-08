package com.metroFare.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.metroFare.bean.Fare;
import com.metroFare.bean.JourneyDetails;
import com.metroFare.utility.DatabaseConfig;




public class JourneyDetailsDao {
	
	
	
	
	
	
public boolean insert(JourneyDetails journey) {
		
		
	JourneyDetails j = new JourneyDetails();
		DatabaseConfig d= new DatabaseConfig();
	 	Connection c= d.getCon();
	 	try {
	 	PreparedStatement s= c.prepareStatement("insert into JourneyDetails values (?,?,?,?,?,?)");
	 	    s.setString(1, journey.getUserId());
			s.setString(2, journey.getJourneyDateTime());
			s.setDate(3, journey.getJourneyDate());
			s.setInt(4, journey.getFromZoneId());
			s.setInt(5, journey.getToZoneId());
			s.setInt(6, journey.getAmount());
			
			
		 	 s.executeUpdate();
		 	
		
		
		
	 	}catch (SQLException e) {
			e.printStackTrace();
		return false;
		
	 	}
		return true;
		
	}
	
public  ArrayList<JourneyDetails> journeydetails() {
    try
    {
    	DatabaseConfig d= new DatabaseConfig();
	 	Connection con= d.getCon();
   
    PreparedStatement ps=con.prepareStatement("select * from JourneyDetails");
    ResultSet rs=ps.executeQuery();
    ArrayList<JourneyDetails> journey=new ArrayList<JourneyDetails>();
    
    while(rs.next())
    {
    	JourneyDetails jd = new JourneyDetails();
    	jd.setUserId(rs.getString("UserId"));
 		jd.setJourneyDateTime(rs.getString("JourneyDateTime"));
 		jd.setJourneyDate(rs.getDate("JourneyDate"));
 		jd.setFromZoneId(rs.getInt("FromZoneId")); 
 		jd.setToZoneId(rs.getInt("ToZoneId"));
 		jd.setAmount(rs.getInt("Amount"));
 		
 	
        
        journey.add(jd);
        
    }
    return journey;

    }catch (Exception e) {
		e.printStackTrace();

	
 	}
	return null;
	
	
}


public  int getTotalDailyAmount(JourneyDetails jd) {
    try
    {
    	DatabaseConfig d= new DatabaseConfig();
	 	Connection con= d.getCon();
   
    PreparedStatement ps=con.prepareStatement("select sum(Amount) as sumAmount from JourneyDetails where JourneyDate = ?  group by JourneyDate ");
    ps.setDate(1, jd.getJourneyDate());
    ResultSet rs=ps.executeQuery();
    int amount = 0;    
    while(rs.next())
    {
    	amount = rs.getInt("sumAmount");
 		
        
    }
    return amount;

    }catch (Exception e) {
		e.printStackTrace();

	
 	}
	return 0;
	
	
}


public  int getTotalWeeklyAmount(JourneyDetails jd) {
    try
    {
    	DatabaseConfig d= new DatabaseConfig();
	 	Connection con= d.getCon();
   
    PreparedStatement ps=con.prepareStatement("select sum(Amount) as sumAmount from JourneyDetails where JourneyDate between ? and ? ");
    ps.setDate(1, jd.getWeekStartDate());
    ps.setDate(2, jd.getWeekEndDate());
    ResultSet rs=ps.executeQuery();
    int amount = 0;    
    while(rs.next())
    {
    	amount = rs.getInt("sumAmount");
 		
        
    }
    return amount;

    }catch (Exception e) {
		e.printStackTrace();

	
 	}
	return 0;
	
	
}





	
}	
	
	

