package com.javieralvarez.adapters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component

public class AdapterMPHtoKMH implements KMH {
	@Autowired
	MPH mph;
	float speed;

	public AdapterMPHtoKMH(MPH mph) {
		this.mph=mph;
	}

	public float getSpeed() {
		// TODO Auto-generated method stub
		return (float) (mph.getSpeed() * 1.60934);
	}

	public void setSpeed(float speed) {
		// TODO Auto-generated method stub
		this.mph.setSpeed(speed);
	}

}
