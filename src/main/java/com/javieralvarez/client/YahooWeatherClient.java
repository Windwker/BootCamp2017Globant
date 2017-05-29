package com.javieralvarez.client;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

@Path(value = "/v1/public/yql?q=select%20*%20from%20weather.forecast%20where%20woeid%20in%20(select%20woeid%20from%20geo.places(1)%20where%20text%3D\"Cordoba%2Car\")&format=json&env=store%3A%2F%2Fdatatables.org%2Falltableswithkeys")
public interface YahooWeatherClient {
	
	@GET
	public String getYahooWeather();

}
