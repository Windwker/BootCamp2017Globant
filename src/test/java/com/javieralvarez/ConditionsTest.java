package com.javieralvarez;





import junit.framework.Assert;
import junit.framework.TestCase;

public class ConditionsTest extends TestCase {
	public void testGetDateToString()  {
		
		Assert.assertEquals(Conditions.getDateToString(1), "12/05/2017");

	}
	
	public void testConditions(){
		Conditions cond = new Conditions();
		float chill = 20;
		cond.setChill(20);
		Assert.assertEquals(chill, cond.getChill());
	}
	
	
}

