package com.javieralvarez.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.javieralvarez.adapters.YahooWeatherStringToJSONAdapter;
import com.javieralvarez.dao.DaoConditionsImpl;
import com.javieralvarez.entity.Conditions;

@RestController
public class ConditionsController {

	@Autowired
	DaoConditionsImpl daoc;

	@Autowired
	YahooWeatherStringToJSONAdapter adapter;

	public void setAdapter(YahooWeatherStringToJSONAdapter adapter) {
		this.adapter = adapter;
	}

	@RequestMapping(value = "/selectconditions", method = RequestMethod.GET, headers = "Accept=application/json")
	public ResponseEntity<Object> getDefaultConditions() {

		if (adapter.getConditions("Cordoba", "Argentina").getDayDescription() == null) {
			return new ResponseEntity<Object>("No current conditions available for Cordoba, Argentina",
					HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<Object>(adapter.getConditions("Cordoba", "Argentina"), HttpStatus.OK);
		}

	}

	@RequestMapping(value = "/selectconditions/{city}/{country}", method = RequestMethod.GET, headers = "Accept=application/json")
	public ResponseEntity<Object> getConditionsCityCountry(@PathVariable("city") String city,
			@PathVariable("country") String country) {
		System.out.println(adapter.getConditions(city, country).getDayDescription());
		if (adapter.getConditions(city, country).getDayDescription() == null) {

			return new ResponseEntity<Object>("No current conditions available for " + city + ',' + country + "",
					HttpStatus.NOT_FOUND);

		} else {
			return new ResponseEntity<Object>(adapter.getConditions(city, country), HttpStatus.OK);

		}

	}

	@RequestMapping(value = "/insertconditions", method = RequestMethod.POST)
	public void insertConditions(@RequestBody Conditions c) {
		daoc.insert(c);
	}

	@RequestMapping(value = "/updateconditions", method = RequestMethod.PUT)
	public void updateConditions(@RequestBody Conditions c) {
		daoc.update(c);
	}

}