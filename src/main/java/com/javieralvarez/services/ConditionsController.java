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
import com.javieralvarez.dao.DaoConditionsIMP;
import com.javieralvarez.entity.Conditions;
import com.javieralvarez.entity.Conexion;
import com.javieralvarez.transformers.Transformer;

@RestController
public class ConditionsController {
	@Autowired
	Conexion conexion;
	@Autowired
	DaoConditionsIMP daoc;
	@Autowired
	Transformer trans;
	@Resource
	YahooWeatherClient cliente;

	@RequestMapping(value = "/selectconditions", method = RequestMethod.GET)
	public List<Conditions> getConditions() {

		List<Conditions> listado = daoc.select(null);

		return listado;

	}

	@RequestMapping(value = "/selectconditions/{date}", method = RequestMethod.GET, headers = "Accept=application/json")
	public List<Conditions> getConditionsDate(@PathVariable String date)
			throws JSONException, ParseException, java.text.ParseException {

		List<Conditions> listado = daoc.select(date);
		if (listado.isEmpty()) {
			Conditions r = Transformer.transformConditions(cliente);
			daoc.insert(r);
		}
		listado = daoc.select(date);
		return listado;

	}

	@RequestMapping(value = "/insertconditions", method = RequestMethod.POST)
	public void insertConditions(@RequestBody Conditions c) {
		daoc.insert(c);
	}

}