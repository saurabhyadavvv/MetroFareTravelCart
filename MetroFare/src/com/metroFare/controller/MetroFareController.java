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

public class MetroFareController {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		
		//Peak hour data
		HashMap<Double,Double> weekDayPeakHour = new HashMap();
		HashMap<Double,Double> weekendPeakHour = new HashMap();
		
		weekDayPeakHour.put((double) 7, 10.30);
		weekDayPeakHour.put((double)17, (double)20);
		
		weekendPeakHour.put((double)9, (double)11);
		weekendPeakHour.put((double)18, (double)22);
		
		
		//Fare list
		List<Fare> fareList = new ArrayList<Fare>();
		fareList.add(new Fare(1,1,30,25,100,500));
		fareList.add(new Fare(1,2,35,30,120,600));
		fareList.add(new Fare(2,1,35,30,120,600));
		fareList.add(new Fare(2,2,25,20,80,400));
		
		char choice;
		int totalDailyAmount = 0;
		int dailyCap = 0;
		int previousDayOfWeek = 0;
		
		int totalWeeklyAmount = 0;
		int weeklyCap = 0;
		
		LocalDateTime weekStartDate = null;
		LocalDateTime weekEndDate = null;
		
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
	
		
		
		if(totalWeeklyAmount==0) {
			weekStartDate = dateTime.minusDays(dwInt-1);
			weekEndDate = weekStartDate.plusDays(7);
			
		}else if(dateTime.isBefore(weekStartDate) || dateTime.isAfter(weekEndDate)) {
			
			totalWeeklyAmount = 0;
			totalDailyAmount = 0;
		}
		
		
		
		
		if(previousDayOfWeek == 0) {
			previousDayOfWeek = dwInt;
		}else if(previousDayOfWeek!=dwInt) {
			totalDailyAmount = 0;
			previousDayOfWeek = dwInt;
		}
		
		
		
		
		int finalAmount = 0;
		
		HashMap<Double,Double> usePeakHour;
		if(dwInt>=1 && dwInt <=5) {
			usePeakHour = weekDayPeakHour;
		}else {
			usePeakHour = weekendPeakHour;
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
			
			System.out.println("Amount to be paid: "+finalAmount);
			System.out.println("Total daily Amount: "+totalDailyAmount);
			System.out.println("Total weekly Amount: "+totalWeeklyAmount);
		
			
			
			
		System.out.println("");	
		System.out.println("Continue(Y/N)");
		choice = s.next().charAt(0);
		
		}while((choice == 'y') || (choice == 'Y'));
		
		System.out.println("Total daily Amount: "+totalDailyAmount);
		System.out.println("Total weekly Amount: "+totalWeeklyAmount);
		
	}

}
