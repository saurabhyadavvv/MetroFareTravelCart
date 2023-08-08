package com.metroFare.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import com.metroFare.bean.Fare;
import com.metroFare.utility.DatabaseConfig;




public class PeakHourDao {
	
	
public  HashMap<Double,Double> getPeakHourFareBasedOnType(String type) {
    
	HashMap<Double,Double> peakHourHahMap = new HashMap<Double, Double>();
	try
    {
    	DatabaseConfig d= new DatabaseConfig();
	 	Connection con= d.getCon();
   
    PreparedStatement ps=con.prepareStatement("select * from PeakHour where Type = ? ");
    ps.setString(1, type);
    ResultSet rs=ps.executeQuery();    
    while(rs.next())
    {
    	
    	peakHourHahMap.put(rs.getDouble("FromTime"), rs.getDouble("ToTime"));
 		
        
    }
    return peakHourHahMap;

    }catch (Exception e) {
		e.printStackTrace();

	
 	}
	return null;
	
	
}


	
}	
	
	

