package com.javieralvarez.adapters;

import org.springframework.stereotype.Component;

@Component
public class FahrenheitImpl implements Fahrenheit {
	private float temp;

	
/*	public FahrenheitImpl(){
		
	}
	
	public FahrenheitImpl(float temp){
		this.temp=temp;
	}*/
	
	
	public float getTemperature() {
		// TODO Auto-generated method stub
		return temp;
	}

	public void setTemperature(float temp) {
		this.temp = temp;
	}



}
