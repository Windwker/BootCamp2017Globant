package com.javieralvarez.services;

import java.util.List;

import javax.annotation.Resource;

import org.json.JSONException;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.javieralvarez.client.YahooWeatherClient;
import com.javieralvarez.dao.DaoForecastIMP;
import com.javieralvarez.entity.Conexion;
import com.javieralvarez.entity.Forecast;
import com.javieralvarez.transformers.Transformer;

@RestController
public class ForecastController {
	@Autowired
	Conexion conexion;
	@Autowired
	DaoForecastIMP daof;
	@Autowired
	Transformer trans;
	@Resource
	YahooWeatherClient cliente;

	@RequestMapping(value = "/selectforecast", method = RequestMethod.GET)
	public List<Forecast> getForecast() {

		List<Forecast> listado = daof.select(null);

		return listado;

	}

	@RequestMapping(value = "/selectforecast/{date}", method = RequestMethod.GET)
	public List<Forecast> getForecastDate(@PathVariable String date) throws JSONException, ParseException, java.text.ParseException {

		List<Forecast> listado = daof.select(date);
		if (listado.isEmpty()) {
			Forecast f = Transformer.transformForecast(cliente);
			daof.insert(f);
		}
		return listado;

	}

	@RequestMapping(value = "/insertforecast", method = RequestMethod.POST)
	public void insertForecast(@RequestBody Forecast f) {
		daof.insert(f);

	}
}
