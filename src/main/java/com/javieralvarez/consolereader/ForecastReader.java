package com.javieralvarez.consolereader;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;


public class ForecastReader {
	private Date date;
	private float high, low;
	private Calendar c = Calendar.getInstance();
	private Scanner sc;
	private String dayDescription;
	
	private static int dayCounter=1;
	
	private int error;
	private int id = 0;

public void forecastConsoleReader(){
	
	
		do {
			try {
				error = 0;
				sc = new Scanner(System.in);
				System.out.println("\n*Configurar Forecast*");
				System.out.println("Condiciones para dia: " + getNextDay());				
				System.out.println("Ingrese descripcion del clima: ");
				dayDescription = sc.next();
				System.out.println("Ingrese minima: ");
				low = sc.nextFloat();
				System.out.println("Ingrese maxima: ");
				high = sc.nextFloat();

				

			} catch (Exception e) {
				System.out.println("Error. Ingrese valores correctamente");
				error = 1;
			}
		} while (error == 1);
	
	
}	

public Date getNextDay() { // Calcula el siguiente dia.
	Date d = new Date();
	Date d1 = new java.sql.Date(d.getTime());
	c.setTime(d1);
	c.add(Calendar.DATE, dayCounter);
	dayCounter+=1;
	d = c.getTime();
	this.date = d;
	return d;
	

}

public Date getDate() {
	return date;
}


public float getHigh() {
	return high;
}


public float getLow() {
	return low;
}



public String getDayDescription() {
	return dayDescription;
}





	
	
}