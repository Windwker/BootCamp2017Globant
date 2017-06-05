package com.javieralvarez.adapters;

import java.util.List;

import com.javieralvarez.entity.Conditions;
import com.javieralvarez.entity.Forecast;

public interface YahooWeather {
	
	Conditions getConditions(String city, String country);
	List<Forecast> getForecast (String city, String country);
}
