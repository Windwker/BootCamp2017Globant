package com.javieralvarez.transformers;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;

import com.javieralvarez.adapters.AdapterFahrenheitToCelcius;
import com.javieralvarez.adapters.Fahrenheit;
import com.javieralvarez.entity.Conditions;
import com.javieralvarez.entity.Forecast;

public class Transformer {
	@Autowired
	static Conditions c;
	@Autowired
	static Forecast f;


	public static Conditions transformConditions(String result) {

		JSONObject js = new JSONObject(result);

		JSONObject channel = js.getJSONObject("query").getJSONObject("results").getJSONObject("channel");
		String date = js.getJSONObject("query").getJSONObject("results").getJSONObject("channel").getJSONObject("item")
				.getJSONArray("forecast").getJSONObject(0).getString("date");
		String daydescription = (channel.getJSONObject("item").getJSONObject("condition").getString("text"));
		float temp = Float.parseFloat(channel.getJSONObject("item").getJSONObject("condition").getString("temp"));


		float chill = Float.parseFloat(channel.getJSONObject("wind").getString("chill"));

		float windspeed = Float.parseFloat(channel.getJSONObject("wind").getString("speed"));
		String sunrise = (channel.getJSONObject("astronomy").getString("sunrise"));
		String sunset = (channel.getJSONObject("astronomy").getString("sunset"));
		float humidity = Float.parseFloat(channel.getJSONObject("atmosphere").getString("humidity"));
		float pressure = Float.parseFloat((channel.getJSONObject("atmosphere").getString("pressure")));
		float visibility = Float.parseFloat(channel.getJSONObject("atmosphere").getString(("visibility")));
		String country = (channel.getJSONObject("location").getString("country"));
		String city = (channel.getJSONObject("location").getString("city"));
		Conditions.Builder conditionBuilder = new Conditions.Builder();
		conditionBuilder.date(date).description(daydescription).temp(temp).st(chill).windspeed(windspeed)
				.sunrise(sunrise).sunset(sunset).humidity(humidity).pressure(pressure).visibility(visibility)
				.country(country).city(city).build();

		c = new Conditions(conditionBuilder);
		return c;

	}

	public static Forecast transformForecast(String result, int pos) {

		JSONObject js = new JSONObject(result);

		JSONObject forecast = js.getJSONObject("query").getJSONObject("results").getJSONObject("channel")
				.getJSONObject("item").getJSONArray("forecast").getJSONObject(pos);

		String forecastDate = forecast.getString("date");
		String forecastDescription = forecast.getString("text");
		float low = Float.parseFloat(forecast.getString("low"));

		float high = Float.parseFloat(forecast.getString("high"));

		
		String country = (js.getJSONObject("query").getJSONObject("results").getJSONObject("channel")
				.getJSONObject("location").getString("country"));
		String city = (js.getJSONObject("query").getJSONObject("results").getJSONObject("channel")
				.getJSONObject("location").getString("city"));
		Forecast.Builder forecastBuilder = new Forecast.Builder();
		forecastBuilder.date(forecastDate).dayDescription(forecastDescription).low(low).high(high).city(city)
				.country(country).build();

		f = new Forecast(forecastBuilder);

		return f;

	}

}
