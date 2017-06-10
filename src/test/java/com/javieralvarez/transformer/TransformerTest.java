package com.javieralvarez.transformer;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.junit.Test;

import com.javieralvarez.entity.Conditions;
import com.javieralvarez.entity.Forecast;
import com.javieralvarez.transformers.Transformer;

import junit.framework.Assert;

public class TransformerTest {


	@Test
	public void transformConditionsTest() {
		String date = "05 Jun 2017";
		String dayDescription = "dummyDayDescription";
		float temp = 44;
		float chill = 52;
		float windSpeed = 101;
		String sunrise = "dummySunrise";
		String sunset = "dummySunset";
		float humidity = 22;
		float pressure = 11;
		float visibility = 102;
		String country = "dummyCountry";
		String city = "dummyCity";
		Conditions.Builder builder = new Conditions.Builder();
		builder.date(date).description(dayDescription).temp(temp).st(chill).windspeed(windSpeed).sunrise(sunrise)
				.sunset(sunset).humidity(humidity).pressure(pressure).visibility(visibility).city(city).country(country)
				.build();
		Conditions dummyConditions = new Conditions(builder);

		String reader = "";
		String json = "";
		try {
			String file = "/home/javi/Dropbox/Eclipse/Reader/FileToRead.txt";
			FileReader lector = new FileReader(file);

			BufferedReader buffreader = new BufferedReader(lector);
			while ((reader = buffreader.readLine()) != null) {
				json += reader;
			}
			buffreader.close();

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.getMessage();
		}
	
	
		
	Assert.assertEquals(dummyConditions.getDate(), Transformer.transformConditions(json).getDate());
	Assert.assertEquals(dummyConditions.getDayDescription(), Transformer.transformConditions(json).getDayDescription());
	Assert.assertEquals(dummyConditions.getTemp(), Transformer.transformConditions(json).getTemp());
	Assert.assertEquals(dummyConditions.getChill(), Transformer.transformConditions(json).getChill());
	Assert.assertEquals(dummyConditions.getWindSpeed(), Transformer.transformConditions(json).getWindSpeed());
	Assert.assertEquals(dummyConditions.getSunrise(), Transformer.transformConditions(json).getSunrise());
	Assert.assertEquals(dummyConditions.getSunset(), Transformer.transformConditions(json).getSunset());
	Assert.assertEquals(dummyConditions.getHumidity(), Transformer.transformConditions(json).getHumidity());
	Assert.assertEquals(dummyConditions.getVisibility(), Transformer.transformConditions(json).getVisibility());
	Assert.assertEquals(dummyConditions.getPressure(), Transformer.transformConditions(json).getPressure());
	Assert.assertEquals(dummyConditions.getCity(), Transformer.transformConditions(json).getCity());
	Assert.assertEquals(dummyConditions.getCountry(), Transformer.transformConditions(json).getCountry());
			
	
	}

	@Test
	public void transformForecastTest() {
		String date = "05 Jun 2017";
		String dayDescription = "dummyDayDescription";
		float low =43;
		float high = 58;
		String city = "dummyCity";
		String country = "dummyCountry";
		String reader = "";
		String json = "";
		
		
		Forecast.Builder fbuilder=new Forecast.Builder();
		fbuilder.date(date).dayDescription(dayDescription).low(low).high(high).city(city).country(country).build();
		Forecast dummyForecast = new Forecast(fbuilder);
		
		try {
			String file = "/home/javi/Dropbox/Eclipse/Reader/FileToRead.txt";
			FileReader lector = new FileReader(file);

			BufferedReader buffreader = new BufferedReader(lector);
			while ((reader = buffreader.readLine()) != null) {
				json += reader;
			}
			buffreader.close();

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.getMessage();
		}
		
		Assert.assertEquals(dummyForecast.getDayDescription(),Transformer.transformForecast(json, 0).getDayDescription() );
		Assert.assertEquals(dummyForecast.getDate(),Transformer.transformForecast(json, 0).getDate() );
		Assert.assertEquals(dummyForecast.getLow(),Transformer.transformForecast(json, 0).getLow() );
		Assert.assertEquals(dummyForecast.getHigh(),Transformer.transformForecast(json, 0).getHigh() );
		
	}

}
