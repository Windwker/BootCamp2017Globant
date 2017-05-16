package com.javieralvarez;


import java.util.Calendar;
import java.util.Date;

import org.junit.Assert;

import com.javieralvarez.clases.Forecast;

import junit.framework.TestCase;

public class ForecastTest extends TestCase {
	
	public void testForecastConstructor(){
		Date date = new Date();
		Forecast fc = new Forecast(date,"lindo",14,20);
		Assert.assertEquals(date, fc.getDate());
		Assert.assertEquals("lindo", fc.getDayDescription());
		
	}

	public void testArrayList(){
		Date date = new Date();
		Forecast fc = new Forecast(date,"lindo",14,20);
		fc.getLista();
	}
	
	
	public void testNextDay(){
		Date date = new Date();
		Forecast fc = new Forecast(date ,"lindo",14,20);
		fc.getNextDay(1);
		
	}




}
