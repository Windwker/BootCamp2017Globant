package com.javieralvarez.adapter;

import org.easymock.EasyMock;
import org.junit.Test;

import com.javieralvarez.adapters.AdapterFahrenheitToCelcius;
import com.javieralvarez.adapters.AdapterMPHtoKMH;
import com.javieralvarez.adapters.Celcius;
import com.javieralvarez.adapters.Fahrenheit;
import com.javieralvarez.adapters.FahrenheitImpl;
import com.javieralvarez.adapters.KMH;
import com.javieralvarez.adapters.MPH;
import com.javieralvarez.adapters.MphImpl;

import junit.framework.Assert;

public class AdapterTest {
	@Test
	public void adaptFahrenheitToCelciusTest() {
		float tempTry = (float) 15.555555;
		FahrenheitImpl f = new FahrenheitImpl();
		f.setTemperature(60);
		AdapterFahrenheitToCelcius adapter = new AdapterFahrenheitToCelcius(f);
		Assert.assertEquals(tempTry, adapter.getTemperature());

	}

	@Test
	public void adaptMPHtoKMHtest() {
		float speedTry = (float) 241.401;
		MphImpl m = new MphImpl();
		m.setSpeed(150);
		AdapterMPHtoKMH adapter = new AdapterMPHtoKMH(m);
		Assert.assertEquals(speedTry, adapter.getSpeed());
	}

	@Test
	public void adaptFahrenheitToCelciusEasyMockTest() {
		float tempTry = 20;
		Fahrenheit f = EasyMock.createMock(Fahrenheit.class);
		EasyMock.expect(f.getTemperature()).andReturn((float) 68).times(1);
		EasyMock.replay(f);
		Celcius a = new AdapterFahrenheitToCelcius(f);
		float tempAdapted = a.getTemperature();
		Assert.assertEquals(tempTry, tempAdapted);
		EasyMock.verify(f);

	}

	@Test
	public void adaptMPHtoKMHEasyMocktest() {
		float speedTry = (float) (241.401);
		MPH m = EasyMock.createMock(MPH.class);
		EasyMock.expect(m.getSpeed()).andReturn((float) 150).times(1);
		EasyMock.replay(m);
		KMH k = new AdapterMPHtoKMH(m);
		float speedAdapted = k.getSpeed();
		Assert.assertEquals(speedTry, speedAdapted);
		EasyMock.verify(m);
	}

}
