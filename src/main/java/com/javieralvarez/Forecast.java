package com.javieralvarez;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;

public class Forecast {
	private Calendar c = Calendar.getInstance(); 
	private static Forecast instance;
	private String text;
	private Date date;
	private float high, low;
	private String dateToString;
	private Scanner sc = new Scanner(System.in);


		
	
	private Forecast(){		

	}
	
	public static Forecast getInstance(){
		if(instance==null){
			instance = new Forecast();
		}
		return instance;
	}
	
	
	public void setForecastConditions(){
		
		String date;
		String description;
		float low;
		float high;
		
		
	
		setDate(new Date());
		date=getDateToString();
		System.out.println("Condiciones para dia: " + getDateToString());
		System.out.println("Ingrese descripcion del clima: ");
		setDayDescription(sc.next());
		System.out.println("Ingrese minima: ");
		setLow(sc.nextFloat());
		System.out.println("Ingrese maxima: ");
		setHigh(sc.nextFloat());
		
		
	
		
		
	}
	

	

	public String getDateToString() {
		DateFormat df = new SimpleDateFormat("dd/MM/YYYY");
		dateToString=df.format(date);
		return dateToString;
	}

	public void setDate(Date date) {				
		c.setTime(date); 
		c.add(Calendar.DATE,1);
		this.date = c.getTime();;
	}



	public String getDayDescription() {
		return text;
	}

	public void setDayDescription(String text) {
		this.text = text;
	}

	public float getHigh() {
		return high;
	}

	public void setHigh(float high) {
		this.high = high;
	}

	public float getLow() {
		return low;
	}

	public void setLow(float low) {
		this.low = low;
	}
	
	
}
