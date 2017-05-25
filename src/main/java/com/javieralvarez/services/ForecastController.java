package com.javieralvarez.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.javieralvarez.clases.Conexion;
import com.javieralvarez.clases.Forecast;
import com.javieralvarez.dao.DaoForecastIMP;

@RestController
public class ForecastController {
	@Autowired
	Conexion conexion;
	@Autowired
	DaoForecastIMP daof;

	@RequestMapping(value = "/selectforecast", method = RequestMethod.GET, headers = "Accept=application/json")
	public List<Forecast> getForecast() {

		List<Forecast> listado = daof.select(null);

		return listado;

	}

	@RequestMapping(value = "/selectforecast/{date}", method = RequestMethod.GET, headers = "Accept=application/json")
	public List<Forecast> getForecastDate(@PathVariable String date) {

		List<Forecast> listado = daof.select(date);

		return listado;

	}

	@RequestMapping(value = "/insertforecast", method = RequestMethod.POST)
	public void insertForecast(@RequestBody Forecast f) {
		daof.insert(f);

	}
}
