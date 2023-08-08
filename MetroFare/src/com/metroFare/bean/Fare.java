package com.metroFare.bean;

import java.util.HashMap;

public class Fare {
	
	private int fromZoneId;
	private int toZoneId;
	private int peakHourFare;
	private int offPeakHourFare;
	private int dailyCap;
	private int weeklyCap;
	
	
	public Fare(int fromZoneId, int toZoneId, int peakHourFare, int offPeakHourFare, int dailyCap, int weeklyCap) {
		super();
		this.fromZoneId = fromZoneId;
		this.toZoneId = toZoneId;
		this.peakHourFare = peakHourFare;
		this.offPeakHourFare = offPeakHourFare;
		this.dailyCap = dailyCap;
		this.weeklyCap = weeklyCap;
	}
	
	public Fare() {
		
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
	public int getPeakHourFare() {
		return peakHourFare;
	}
	public void setPeakHourFare(int peakHourFare) {
		this.peakHourFare = peakHourFare;
	}
	public int getOffPeakHourFare() {
		return offPeakHourFare;
	}
	public void setOffPeakHourFare(int offPeakHourFare) {
		this.offPeakHourFare = offPeakHourFare;
	}
	public int getDailyCap() {
		return dailyCap;
	}
	public void setDailyCap(int dailyCap) {
		this.dailyCap = dailyCap;
	}
	public int getWeeklyCap() {
		return weeklyCap;
	}
	public void setWeeklyCap(int weeklyCap) {
		this.weeklyCap = weeklyCap;
	}
	
	
	

}
