package com.javieralvarez.proxy;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.javieralvarez.client.YahooWeatherClient;

@Component
public class Proxy implements YahooWeatherClient {
	@Resource
	YahooWeatherClient yahooClient;

	 

	public String getYahooWeather(String query) {
		String response = yahooClient.getYahooWeather(query);
		
		return response;
	}
	
	
	public void setClient(YahooWeatherClient client){
		this.yahooClient = client;
	}

}