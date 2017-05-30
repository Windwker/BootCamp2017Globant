package com.javieralvarez.adapters;

import org.springframework.beans.factory.annotation.Autowired;

public class AdapterCelciusToFahrenheit implements Celcius {

@Autowired
private Fahrenheit f;

public int getTemperature() {
	// TODO Auto-generated method stub
	
	
	return (f.getTemperature()-32);
}


	
}
