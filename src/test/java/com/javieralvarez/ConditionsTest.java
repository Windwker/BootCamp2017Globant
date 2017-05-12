package com.javieralvarez;





import java.util.InputMismatchException;

import junit.framework.Assert;
import junit.framework.TestCase;

public class ConditionsTest extends TestCase {
	
	public void testGetDateToString()  {
		
		Assert.assertEquals(Conditions.getDateToString(1), "13/05/2017");

	}
	
	public void testConditions(){
		Conditions cond = new Conditions();
		float chill = 20;
		cond.setChill(20);
		Assert.assertEquals(chill, cond.getChill());
	}
	
/*	public void testInput(){
		try{
		Conditions cond = new Conditions();
		cond.setCurrentConditions();
		}catch(InputMismatchException IME){
		Assert.fail();
		}
	}*/
	
	
}

