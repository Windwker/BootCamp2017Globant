package com.javieralvarez.entity;

import org.springframework.stereotype.Component;

@Component
public class Conditions {

	private String date;
	private String dayDescription;
	private float temp;
	private float chill;
	private float windSpeed;
	private String sunrise;
	private String sunset;
	private float humidity;
	private float pressure;
	private float visibility;
	private String country;
	private String city;

	public Conditions() {
	}

	public Conditions(Builder conditionBuilder) {
		this.date = conditionBuilder.date;
		this.dayDescription = conditionBuilder.dayDescription;
		this.temp = conditionBuilder.temp;
		this.chill = conditionBuilder.chill;
		this.windSpeed = conditionBuilder.windSpeed;
		this.sunrise = conditionBuilder.sunrise;
		this.sunset = conditionBuilder.sunset;
		this.humidity = conditionBuilder.humidity;
		this.pressure = conditionBuilder.pressure;
		this.visibility = conditionBuilder.visibility;
		this.country = conditionBuilder.country;
		this.city = conditionBuilder.city;
	}

	public static class Builder {
		private String date;
		private String dayDescription;
		private float temp;
		private float chill;
		private float windSpeed;
		private String sunrise;
		private String sunset;
		private float humidity;
		private float pressure;
		private float visibility;
		private String country;
		private String city;

		public Builder date(String date) {
			this.date = date;
			return this;
		}

		public Builder description(String dayDescription) {
			this.dayDescription = dayDescription;
			return this;
		}

		public Builder temp(float temp) {
			this.temp = temp;
			return this;
		}

		public Builder st(float chill) {
			this.chill = chill;
			return this;
		}

		public Builder windspeed(float windspeed) {
			this.windSpeed = windspeed;
			return this;
		}

		public Builder sunrise(String sunrise) {
			this.sunrise = sunrise;
			return this;
		}

		public Builder sunset(String sunset) {
			this.sunset = sunset;
			return this;
		}

		public Builder humidity(Float humidity) {
			this.humidity = humidity;
			return this;
		}

		public Builder pressure(Float pressure) {
			this.pressure = pressure;
			return this;
		}

		public Builder visibility(Float visibility) {
			this.visibility = visibility;
			return this;
		}

		public Builder country(String country) {
			this.country = country;
			return this;
		}

		public Builder city(String city) {
			this.city = city;
			return this;
		}

		public Conditions build() {
			return new Conditions(this);
		}
	}

	public String getCountry() {
		return country;
	}

	public String getCity() {
		return city;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}
	
	public void setTemp(float temp){
		this.temp=temp;
	}
	
	public void setWindspeed(float speed){
		this.windSpeed=speed;
	}
	
	public void setChill(float chill){
		this.chill=chill;
	}

	public String getDayDescription() {
		return dayDescription;
	}

	public float getTemp() {
		return temp;
	}

	public float getChill() {
		return chill;
	}

	public float getWindSpeed() {
		return windSpeed;
	}

	public String getSunrise() {
		return sunrise;
	}

	public String getSunset() {
		return sunset;
	}

	public float getHumidity() {
		return humidity;
	}

	public float getPressure() {
		return pressure;
	}

	public float getVisibility() {
		return visibility;
	}

}
