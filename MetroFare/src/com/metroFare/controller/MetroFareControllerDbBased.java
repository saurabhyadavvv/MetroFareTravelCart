package com.metroFare.controller;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.Scanner;

import com.metroFare.bean.Fare;
import com.metroFare.bean.JourneyDetails;
import com.metroFare.dao.FareDao;
import com.metroFare.dao.JourneyDetailsDao;
import com.metroFare.dao.PeakHourDao;

public class MetroFareControllerDbBased {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		FareDao fd = new FareDao();
		List<Fare> fareList = fd.fareList();
		
		
		char choice;
		//int totalDailyAmount = 0;
		int dailyCap = 0;
		int previousDayOfWeek = 0;
		
		//int totalWeeklyAmount = 0;
		int weeklyCap = 0;
		
		//LocalDateTime weekStartDate = null;
		//LocalDateTime weekEndDate = null;
		
		do {
		Scanner s = new Scanner(System.in);
		System.out.println("Enter Date and time in format yyyy-MM-dd HH:mm (Example: 2023-08-25 10:25)");
		
		String date = s.nextLine();
		//System.out.println(date);
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
		LocalDateTime dateTime = LocalDateTime.parse(date, formatter);
		System.out.println(dateTime.format(formatter));
		
		System.out.println("Enter from zoneId: ");
		int fromId = s.nextInt();
		
		System.out.println("Enter to zoneId: ");
		int toId = s.nextInt();
		
		DayOfWeek dw =  dateTime.getDayOfWeek();
		int dwInt = dw.getValue();
		
		
		
		int finalAmount = 0;
		
		HashMap<Double,Double> usePeakHour;
		if(dwInt>=1 && dwInt <=5) {
			PeakHourDao peakHourDao = new PeakHourDao();
			usePeakHour = peakHourDao.getPeakHourFareBasedOnType("weekday");
		}else {
			PeakHourDao peakHourDao = new PeakHourDao();
			usePeakHour = peakHourDao.getPeakHourFareBasedOnType("weekend");
		}
			
					
			int hr = dateTime.getHour();
			int min = dateTime.getMinute();
			String timeString = hr+"."+min;
			double time = Double.parseDouble(timeString);
			String hourType = "offPeak";
			
			for (Entry<Double, Double> entry : usePeakHour.entrySet()) { 
	            //System.out.println("Key = " + entry.getKey() + ", Value = " + entry.getValue());
	            
	            if(time>=entry.getKey() && time<=entry.getValue()) {
	            	hourType = "peak";
	            }
			
			}
			
			for(int i = 0; i<fareList.size();i++) {
				Fare f = fareList.get(i);
				if(fromId == f.getFromZoneId() && toId == f.getToZoneId()) {
					if(hourType.equalsIgnoreCase("peak")) {
						finalAmount = f.getPeakHourFare();
						if(dailyCap<f.getDailyCap()) {
							dailyCap = f.getDailyCap();
						}
						if(weeklyCap<f.getWeeklyCap()) {
							weeklyCap = f.getWeeklyCap();
						}
					}else {
						finalAmount = f.getOffPeakHourFare();
						if(dailyCap<f.getDailyCap()) {
							dailyCap = f.getDailyCap();
						}
						if(weeklyCap<f.getWeeklyCap()) {
							weeklyCap = f.getWeeklyCap();
						}
					}
				}
			}
			
			
			
			JourneyDetailsDao journeyDetailsDao = new JourneyDetailsDao();
			JourneyDetails journeyDetails = new JourneyDetails();
			journeyDetails.setUserId("Dummy");
			journeyDetails.setJourneyDateTime(date);
			journeyDetails.setJourneyDate(java.sql.Date.valueOf(dateTime.toLocalDate()));
			journeyDetails.setFromZoneId(fromId);
			journeyDetails.setToZoneId(toId);
			
			LocalDateTime weekStartDate = dateTime.minusDays(dwInt-1);
			LocalDateTime weekEndDate = weekStartDate.plusDays(7);
			journeyDetails.setWeekStartDate(java.sql.Date.valueOf(weekStartDate.toLocalDate()));
			journeyDetails.setWeekEndDate(java.sql.Date.valueOf(weekEndDate.toLocalDate()));
			
			
			
			
			int totalDailyAmount = journeyDetailsDao.getTotalDailyAmount(journeyDetails);
			int totalWeeklyAmount = journeyDetailsDao.getTotalWeeklyAmount(journeyDetails);
			
			if(totalDailyAmount>=dailyCap) {
				finalAmount = 0;
			}else {
				totalDailyAmount = totalDailyAmount + finalAmount;
				if(totalDailyAmount>dailyCap) {
					finalAmount = dailyCap + finalAmount - totalDailyAmount;
					totalDailyAmount = dailyCap;
				}
			
			}
			
			
			
			
			if(totalWeeklyAmount>=weeklyCap) {
				finalAmount = 0;
			}else {
				totalWeeklyAmount = totalWeeklyAmount + finalAmount;
				if(totalWeeklyAmount>weeklyCap) {
					finalAmount = weeklyCap + finalAmount - totalWeeklyAmount;
					totalWeeklyAmount = weeklyCap;
				}
			}
			
			
			journeyDetails.setAmount(finalAmount);
			
			journeyDetailsDao.insert(journeyDetails);
			
			System.out.println("Amount to be paid: "+finalAmount);
			System.out.println("Total daily Amount: "+totalDailyAmount);
			System.out.println("Total weekly Amount: "+totalWeeklyAmount);
		
			
			
			
		System.out.println("");	
		System.out.println("Continue(Y/N)");
		choice = s.next().charAt(0);
		
		}while((choice == 'y') || (choice == 'Y'));
		
		//System.out.println("Total daily Amount: "+totalDailyAmount);
		//System.out.println("Total weekly Amount: "+totalWeeklyAmount);
		
	}

}
