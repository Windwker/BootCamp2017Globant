package com.javieralvarez.clases;

import java.util.Date;

public class Conditions {

	private Date date;
	private String dayDescription;
	private float temp;
	private float chill;
	private float windSpeed;
	private String sunrise;
	private String sunset;
	private float humidity;
	private float pressure;
	private float visibility;

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

	}

	public static class Builder {
		private Date date;
		private String dayDescription;
		private float temp;
		private float chill;
		private float windSpeed;
		private String sunrise;
		private String sunset;
		private float humidity;
		private float pressure;
		private float visibility;

		public Builder date(Date date) {
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

		public Conditions build() {
			return new Conditions(this);
		}
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
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
