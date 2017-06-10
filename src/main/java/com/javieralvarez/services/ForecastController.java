package com.javieralvarez.services;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.javieralvarez.adapters.YahooWeatherStringToJSONAdapter;
import com.javieralvarez.dao.DaoForecastImpl;
import com.javieralvarez.entity.Forecast;

@RestController
public class ForecastController {

	@Autowired
	DaoForecastImpl daof;
	@Autowired
	YahooWeatherStringToJSONAdapter adapter;

	
	@RequestMapping(value = "/selectforecast",method = RequestMethod.GET,headers = "Accept=application/json")
	public ResponseEntity<Object> getDefaultForecast(){
		if(adapter.getForecast("Cordoba", "Argentina").get(0).getDayDescription()==null){
			
			return new ResponseEntity<Object>("No Forecast available for Cordoba, Argentina",HttpStatus.NOT_FOUND);
		}else{
			return new ResponseEntity<Object>(adapter.getForecast("Cordoba", "Argentina"),HttpStatus.OK);
	
		}
	}
	
	
	@RequestMapping(value = "/selectforecast/{city}/{country}", method = RequestMethod.GET,headers = "Accept=application/json")
	public ResponseEntity<Object> getForecastCityCountry(@PathVariable("city") String city, @PathVariable("country") String country) {
		
		if(adapter.getForecast(city, country).get(0).getDayDescription()==null){
			
			return new ResponseEntity<Object>("No Forecast available for "+city+','+country+"",HttpStatus.NOT_FOUND);
			
		}else{
			return new ResponseEntity<Object>(adapter.getForecast(city, country),HttpStatus.OK);

	
		}
		
	}

	@RequestMapping(value = "/insertforecast", method = RequestMethod.POST,  headers = "Accept=application/json")
	public void insertForecast(@RequestBody ArrayList<Forecast> f) {
		
		for(int i = 0; i < f.size();i++){
		daof.insert(f.get(i));
		}

	}
	@RequestMapping(value = "/updateforecast", method = RequestMethod.PUT, headers = "Accept=application/json")
	public void updateForecast(@RequestBody ArrayList<Forecast> f){
		for(int i = 0; i < f.size();i++){
			daof.update(f.get(i));
			}
	}
}
