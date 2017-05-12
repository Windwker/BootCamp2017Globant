package com.javieralvarez;

import junit.framework.Assert;
import junit.framework.TestCase;

public class WeatherGlobantTest extends TestCase {

	
	public void testWeather(){
		try{
		Conditions cc = new Conditions();
		Forecast fc = new Forecast();
		cc.getCurrentConditions();
		fc.getForecastConditions();
		}catch(Exception e){
			Assert.fail();
		}
		}
	
}
