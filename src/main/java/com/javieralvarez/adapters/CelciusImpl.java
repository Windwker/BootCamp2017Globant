package com.javieralvarez.adapters;

import org.springframework.stereotype.Component;

@Component
public class CelciusImpl implements Celcius{

	private float temp;

	public float getTemperature() {
		// TODO Auto-generated method stub
		return temp;
	}
	
/*	public CelciusImpl(){
		
	}
	
	public CelciusImpl(float temp){
		this.temp=temp;
	}*/

	public void setTemperature(float temp) {
		this.temp = temp;
	}
	

}
