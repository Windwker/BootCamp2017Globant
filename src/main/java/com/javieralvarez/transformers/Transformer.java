package com.javieralvarez.transformers;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Component;

import com.javieralvarez.client.YahooWeatherClient;
import com.javieralvarez.entity.Conditions;
import com.javieralvarez.entity.Forecast;

@Component
public class Transformer {

	public static Conditions transformConditions(YahooWeatherClient y)
			throws JSONException, ParseException, java.text.ParseException {

		String str = new String();
		JSONObject js = new JSONObject(str);
		str = y.getYahooWeather();


		JSONObject channel = js.getJSONObject("query").getJSONObject("results").getJSONObject("channel");
		String date = ((channel.getString("lastBuildDate")));
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

		Conditions c = new Conditions(conditionBuilder);
		return c;

	}

	public static Forecast transformForecast(YahooWeatherClient y)
			throws JSONException, ParseException, java.text.ParseException {
		String str = new String();
		
		str = y.getYahooWeather();
		JSONObject js = new JSONObject(str);
		JSONObject forecast = js.getJSONObject("query").getJSONObject("results").getJSONObject("channel").getJSONObject("item").getJSONArray("forecast").getJSONObject(1);
		
		String forecastDate = forecast.getString("date");
		String forecastDescription = forecast.getString("text");
		float low = Float.parseFloat(forecast.getString("low"));
		float high = Float.parseFloat(forecast.getString("high"));
		
		Forecast.Builder forecastBuilder=new Forecast.Builder();
		forecastBuilder.date(forecastDate).dayDescription(forecastDescription).low(low).high(high).build();
		
		Forecast f = new Forecast(forecastBuilder);
		
		return f;
	}

}
