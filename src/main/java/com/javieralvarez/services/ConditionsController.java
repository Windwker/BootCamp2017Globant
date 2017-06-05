package com.javieralvarez.services;

import javax.annotation.Resource;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.javieralvarez.client.YahooWeatherClient;
import com.javieralvarez.dao.DaoConditionsImpl;
import com.javieralvarez.entity.Conditions;
import com.javieralvarez.entity.Conexion;
import com.javieralvarez.proxy.ProxyWeather;

@RestController
public class ConditionsController {
	@Autowired
	Conexion conexion;
	@Autowired
	DaoConditionsImpl daoc;
	@Resource
	YahooWeatherClient cliente;
	@Autowired
	ProxyWeather proxyweather;


/*	@RequestMapping(value = "/selectconditions", method = RequestMethod.GET)
	public List<Conditions> getConditions() {

		List<Conditions> listado = daoc.select(null);

		return listado;

	}*/

	@RequestMapping(value = "/selectconditions/{city}/{country}", method = RequestMethod.GET, headers = "Accept=application/json")
	public Response getConditionsDate(@PathVariable("city") String city, @PathVariable("country") String country){
		if(proxyweather.getConditionsJson(city, country).getDayDescription()==null){
			
			return Response.status(Response.Status.NOT_FOUND).build();
		}else{
			return Response.status(Response.Status.OK).entity(proxyweather.getConditionsJson(city, country)).build();
				
		}

	}

	@RequestMapping(value = "/insertconditions", method = RequestMethod.POST)
	public void insertConditions(@RequestBody Conditions c) {
		daoc.insert(c);
	}

}