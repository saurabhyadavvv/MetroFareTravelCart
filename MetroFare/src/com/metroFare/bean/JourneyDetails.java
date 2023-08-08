package com.metroFare.bean;

import java.sql.Date;

public class JourneyDetails {
	
	private String userId;
	private String journeyDateTime;
	private Date journeyDate;
	private int fromZoneId;
	private int toZoneId;
	private int amount;
	private Date weekStartDate;
	private Date weekEndDate;
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	
	
	public String getJourneyDateTime() {
		return journeyDateTime;
	}
	public void setJourneyDateTime(String journeyDateTime) {
		this.journeyDateTime = journeyDateTime;
	}
	public Date getJourneyDate() {
		return journeyDate;
	}
	public void setJourneyDate(Date journeyDate) {
		this.journeyDate = journeyDate;
	}
	public int getFromZoneId() {
		return fromZoneId;
	}
	public void setFromZoneId(int fromZoneId) {
		this.fromZoneId = fromZoneId;
	}
	public int getToZoneId() {
		return toZoneId;
	}
	public void setToZoneId(int toZoneId) {
		this.toZoneId = toZoneId;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	public Date getWeekStartDate() {
		return weekStartDate;
	}
	public void setWeekStartDate(Date weekStartDate) {
		this.weekStartDate = weekStartDate;
	}
	public Date getWeekEndDate() {
		return weekEndDate;
	}
	public void setWeekEndDate(Date weekEndDate) {
		this.weekEndDate = weekEndDate;
	}
	
	

}
