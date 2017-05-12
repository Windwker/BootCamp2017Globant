package com.javieralvarez;


import java.util.Calendar;
import java.util.Date;

import org.junit.Assert;

import junit.framework.TestCase;

public class ForecastTest extends TestCase {
	private Forecast fc = new Forecast();
public void testGetForecastConditions(){
	
	try{
		fc.getForecastConditions();
	}catch(Exception e){
		Assert.fail();
	}
}


public void testGetDateToString()  {
	
	Assert.assertEquals(fc.getDateToString(1), "13/05/2017");

}

public void testSetDate(){
	 
	 
		 Date date = new Date();
		 Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.add(Calendar.DATE, 1);
		date = c.getTime();
		
		fc.setDate(new Date(), 1);
		
		Assert.assertEquals(date, fc.getDate());

}	
	





}
