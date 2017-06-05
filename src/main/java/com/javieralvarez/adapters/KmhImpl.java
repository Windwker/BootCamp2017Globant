package com.javieralvarez.adapters;

import org.springframework.stereotype.Component;

@Component
public class KmhImpl implements KMH {
private float speed;

/*	public KmhImpl()
	{
		
	}
	
	public KmhImpl(float speed){
		
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
