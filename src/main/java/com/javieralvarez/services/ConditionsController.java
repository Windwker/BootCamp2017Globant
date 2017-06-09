package com.javieralvarez.services;

import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.javieralvarez.adapters.YahooWeatherStringToJSONAdapter;
import com.javieralvarez.connection.Conexion;
import com.javieralvarez.dao.DaoConditionsImpl;
import com.javieralvarez.entity.Conditions;

@RestController
public class ConditionsController {
	@Autowired
	Conexion conexion;
	@Autowired
	DaoConditionsImpl daoc;

	@Autowired
	YahooWeatherStringToJSONAdapter adapter;
	


	@RequestMapping(value = "/selectconditions", method = RequestMethod.GET, headers = "Accept=application/json")
	public Response getConditions() {

		if(adapter.getConditions("Cordoba", "Argentina").getDayDescription()==null){
			return Response.status(Response.Status.NOT_FOUND).entity("No current conditions available for Cordoba, Argentina").build();
		}else{
			return Response.status(Response.Status.OK).entity(adapter.getConditions("Cordoba", "Argentina")).build();
		}
		
		

	}

	@RequestMapping(value = "/selectconditions/{city}/{country}", method = RequestMethod.GET, headers = "Accept=application/json")
	public Response getConditionsCityCountry(@PathVariable("city") String city, @PathVariable("country") String country){
		if(adapter.getConditions(city, country).getDayDescription()==null){
			
			return Response.status(Response.Status.NOT_FOUND).entity("No current conditions available for "+city+','+country+"").build();
		}else{
			return Response.status(Response.Status.OK).entity(adapter.getConditions(city, country)).build();
				
		}

	}

	@RequestMapping(value = "/insertconditions", method = RequestMethod.POST)
	public void insertConditions(@RequestBody Conditions c) {
		daoc.insert(c);
	}
	
	@RequestMapping(value = "/updateconditions", method = RequestMethod.PUT)
	public void updateConditions(@RequestBody Conditions c){
		daoc.update(c);
	}
	
	

}