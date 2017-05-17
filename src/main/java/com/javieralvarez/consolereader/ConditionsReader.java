package com.javieralvarez.consolereader;

import java.util.Date;
import java.util.InputMismatchException;
import java.util.Scanner;

public class ConditionsReader {
	
	private Date date;
	private String description;
	private float temp;
	private float chill;
	private float windspeed;
	private String sunrise;
	private String sunset;
	private float humidity;
	private float pressure;
	private float visibility;
	private Scanner sc;
	
	
	
	
	
	
	
	
	 public void conditionsConsoleReader(){
		 int error;
		 do {
				try {
					error = 0;
					date = new Date();
					sc = new Scanner(System.in);
					System.out.println("Ingrese descripcion del clima para HOY: ");
					description = sc.next();
					System.out.println("Ingrese temperatura actual: ");
					temp = sc.nextFloat();
					System.out.println("Ingrese sensacion termica: ");
					chill =sc.nextFloat();
					System.out.println("Ingrese velocidad del viento: ");
					windspeed=(sc.nextFloat());
					System.out.println("Ingrese amanecer: ");
					sunrise = (sc.next());
					System.out.println("Ingrese atardecer: ");
					sunset = (sc.next());
					System.out.println("Ingrese humedad: ");
					humidity = (sc.nextFloat());
					System.out.println("Ingrese presion atmosferica: ");
					pressure =(sc.nextFloat());
					System.out.println("Ingrese visibilidad: ");
					visibility =(sc.nextFloat());
					
				} catch (InputMismatchException IME) {
					System.out.println("Error. Formato ingresado no valido");
					error = 1;

				}finally{
					
				}
			} while (error == 1);
		 //sc.close();
	 }


	 
	public Date getDate() {
		return date;
	}


	public String getDescription() {
		return description;
	}

	public float getTemp() {
		return temp;
	}

	public float getChill() {
		return chill;
	}

	public float getWindspeed() {
		return windspeed;
	}

	public String getSunrise() {
		return sunrise;
	}

	public String getSunset() {
		return sunset;
	}

	public float getHumidity() {
		return humidity;
	}

	public float getPressure() {
		return pressure;
	}

	public float getVisibility() {
		return visibility;
	}

	
	

}
