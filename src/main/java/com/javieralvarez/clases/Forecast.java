package com.javieralvarez.clases;


import java.util.Date;

public class Forecast {
	//////////////////////////////////////// FORECAST////////////////////////////////////////



	private Date date;
	private float high, low;

	private String dayDescription;


	public Forecast() {
	}

	public Forecast(Builder ForecastBuilder) {
		this.date = ForecastBuilder.date;
		this.dayDescription = ForecastBuilder.dayDescription;
		this.low = ForecastBuilder.low;
		this.high = ForecastBuilder.high;
	}

	public static class Builder {
		private Date date;
		private float high, low;
		private String dayDescription;

		public Builder date(Date date) {
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
		
		  public Forecast build(){
	            return new Forecast(this);
	        }

	}
	
	

	public Date getDate() {
		return date;
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

}
