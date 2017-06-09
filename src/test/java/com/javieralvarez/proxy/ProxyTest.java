package com.javieralvarez.proxy;

import org.easymock.EasyMock;
import org.junit.Test;

import com.javieralvarez.client.YahooWeatherClient;

import junit.framework.Assert;

public class ProxyTest {
@Test
	public void WeatherProxyTest(){
	Proxy proxy = new Proxy();
	YahooWeatherClient client = EasyMock.createMock(YahooWeatherClient.class);
	EasyMock.expect(client.getYahooWeather(EasyMock.anyString())).andReturn(client.toString());
	EasyMock.replay(client);

	proxy.setClient(client);
	String r = proxy.getYahooWeather("dummyClient");
	
	
	Assert.assertNotNull(r);
	EasyMock.verify(client);
}
}
