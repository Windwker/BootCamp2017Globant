package com.javieralvarez.adapters;

import org.springframework.stereotype.Component;

@Component
public class MphImpl implements MPH {
private float speed;


/*	public MphImpl(float speed){
		this.speed=speed;
	}*/
	public float getSpeed() {
		// TODO Auto-generated method stub
		return speed;
	}

	public void setSpeed(float speed) {
		// TODO Auto-generated method stub
		this.speed=speed;
	}

}
