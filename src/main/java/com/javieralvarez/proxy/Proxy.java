package com.javieralvarez.proxy;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.javieralvarez.client.YahooWeatherClient;

@Component
public class Proxy implements YahooWeatherClient {
	@Resource
	YahooWeatherClient YahooClient;


	public String getYahooWeather(String query) {
		String response = YahooClient.getYahooWeather(query);
		
		return response;
	}

}