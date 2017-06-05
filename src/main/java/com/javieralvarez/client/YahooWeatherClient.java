package com.javieralvarez.client;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

@Path("v1/public")
@Produces("application/json")
public interface YahooWeatherClient {
	
	@GET
	@Path("/yql")
	public String getYahooWeather(@QueryParam("q") String query);

}
