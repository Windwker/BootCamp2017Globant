package com.javieralvarez.adapters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
@Component
public class AdapterFahrenheitToCelcius implements Celcius {

@Autowired
private Fahrenheit fahrenheit;


public AdapterFahrenheitToCelcius(Fahrenheit fahrenheit){
    this.fahrenheit = fahrenheit;
}


public float getTemperature() {
	// TODO Auto-generated method stub
	
	
	return (fahrenheit.getTemperature() - 32)*5/9;
}

public void setTemperature(float temp) {
	// TODO Auto-generated method stub
	this.fahrenheit.setTemperature(temp);
	
}


	
}
