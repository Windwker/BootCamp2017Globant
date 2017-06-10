package com.javieralvarez.entity;


import org.springframework.stereotype.Component;
@Component
public class Forecast {
	//////////////////////////////////////// FORECAST////////////////////////////////////////



	private String date;
	private float high, low;

	private String dayDescription;
	private String city,country;

	public Forecast() {
	}

	public Forecast(Builder ForecastBuilder) {
		this.date = ForecastBuilder.date;
		this.dayDescription = ForecastBuilder.dayDescription;
		this.low = ForecastBuilder.low;
		this.high = ForecastBuilder.high;
		this.city=ForecastBuilder.city;
		this.country=ForecastBuilder.country;
	}

	public static class Builder {
		private String date;
		private float high, low;
		private String dayDescription;
		private String city,country;

		public Builder date(String date) {
			this.date = date;
			return this;
		}

		public Builder dayDescription(String dayDescription) {
			this.dayDescription = dayDescription;
			return this;
		}

		public Builder high(float high) {
			this.high = high;
			return this;
		}

		public Builder low(float low) {
			this.low = low;
			return this;
		}
		
		public Builder city(String city){
			this.city=city;
			return this;
		}
		
		public Builder country(String country){
			this.country=country;
			return this;
		}
		
		  public Forecast build(){
	            return new Forecast(this);
	        }

	}
	
	

	public String getDate() {
		return date;
	}

	public void setDayDescription(String dayDescription){
		this.dayDescription=dayDescription;
	}
	
	public void setLow(float low){
		this.low=low;
	}
	
	public void setHigh(float high){
		this.high=high;
	}
	
	public float getHigh() {
		return high;
	}

	public float getLow() {
		return low;
	}

	public String getDayDescription() {
		return dayDescription;
	}
	
	public String getCity(){
		return city;
	}
	
	public String getCountry(){
		return country;
	}

}
