package com.javieralvarez.services;

import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.javieralvarez.clases.Conditions;
import com.javieralvarez.clases.Conexion;
import com.javieralvarez.client.YahooWeatherClient;
import com.javieralvarez.dao.DaoConditionsIMP;

@RestController
public class ConditionsController {
	@Autowired
	Conexion conexion;
	@Autowired
	DaoConditionsIMP daoc;
	@Resource
	YahooWeatherClient cliente;
	
	@RequestMapping(value = "/selectconditions", method = RequestMethod.GET)
	public String getConditions(){
		return cliente.getYahooWeather();
	}
	
/*	public List<Conditions> getConditions() {

		List<Conditions> listado = daoc.select(null);
		if(listado.isEmpty()){
			
		}
		return listado;


	}*/

	@RequestMapping(value = "/selectconditions/{date}", method = RequestMethod.GET, headers = "Accept=application/json")
	public List<Conditions> getConditionsDate(@PathVariable String date) {

		List<Conditions> listado = daoc.select(date);

		return listado;

	}

	@RequestMapping(value = "/insertconditions", method = RequestMethod.POST)
	public void insertConditions(@RequestBody Conditions c) {
		daoc.insert(c);
	}

}