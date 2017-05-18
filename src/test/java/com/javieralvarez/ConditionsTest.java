package com.javieralvarez;





import java.util.Date;

import com.javieralvarez.clases.Conditions;

import junit.framework.Assert;
import junit.framework.TestCase;

public class ConditionsTest extends TestCase {
private Date date;	
	
	public void testConditions(){
		Conditions.Builder conditionBuilder = new Conditions.Builder();
		
		Conditions cc = conditionBuilder.date(date).build();
		Assert.assertEquals(date, cc.getDate());
	}
	
	
	
}


