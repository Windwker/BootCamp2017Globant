package com.javieralvarez.services;

import java.util.ArrayList;

import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.javieralvarez.adapters.YahooWeatherStringToJSONAdapter;
import com.javieralvarez.dao.DaoForecastImpl;
import com.javieralvarez.entity.Conexion;
import com.javieralvarez.entity.Forecast;
import com.javieralvarez.transformers.Transformer;

@RestController
public class ForecastController {
	@Autowired
	Conexion conexion;
	@Autowired
	DaoForecastImpl daof;
	@Autowired
	Transformer trans;
	@Autowired
	YahooWeatherStringToJSONAdapter adapter;

	@RequestMapping(value = "/selectforecast/{city}/{country}", method = RequestMethod.GET)
	public Response getForecastDate(@PathVariable("city") String city, @PathVariable("country") String country) {
		
		if(adapter.getForecast(city, country).isEmpty()){
			return Response.status(Response.Status.NOT_FOUND).build();
		}else{
			return Response.status(Response.Status.OK).entity(adapter.getForecast(city, country)).build();
	
		}
		
	}

	@RequestMapping(value = "/insertforecast", method = RequestMethod.POST,  headers = "Accept=application/json")
	public void insertForecast(@RequestBody ArrayList<Forecast> f) {
		
		for(int i = 0; i < f.size();i++){
		daof.insert(f.get(i));
		}

	}
}
