package com.javieralvarez.controller;

import org.easymock.EasyMock;
import org.junit.Test;

import com.javieralvarez.adapters.YahooWeatherStringToJSONAdapter;
import com.javieralvarez.entity.Conditions;
import com.javieralvarez.services.ConditionsController;

import junit.framework.Assert;

public class ControllerTest {
@Test
public void ForecastControllerTest(){
	
}




@Test
public void CurrentConditionsTest(){
	
	YahooWeatherStringToJSONAdapter adapter = EasyMock.createMock(YahooWeatherStringToJSONAdapter.class);
	Conditions currentConditions = EasyMock.createMock(Conditions.class);
	//EasyMock.expect(currentConditions.getChill()).andReturn(EasyMock.anyFloat());
	EasyMock.expect(adapter.getConditions(EasyMock.anyString(), EasyMock.anyString())).andReturn(currentConditions);
	
	EasyMock.replay(adapter);
	ConditionsController conditionController = new ConditionsController();
	conditionController.setAdapter(adapter);

	Assert.assertNotNull(conditionController.getDefaultConditions());
	
	
	
	
}

}
