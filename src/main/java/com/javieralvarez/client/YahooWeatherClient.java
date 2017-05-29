package com.javieralvarez.client;

import javax.ws.rs.GET;
import javax.ws.rs.Produces;

@Produces("application/json")
public interface YahooWeatherClient {
	
	@GET
	public String getYahooWeather();

}
