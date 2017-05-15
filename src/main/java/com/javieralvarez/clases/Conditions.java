package com.javieralvarez.clases;



import java.util.Date;
import java.util.InputMismatchException;
import java.util.Scanner;



public class Conditions{

	
	private Date date;
	private static String dayDescription;
	private float temp;
	private float chill;
	private float windSpeed;
	private String sunrise;
	private String sunset;
	private float humidity;
	private float pressure;
	private float visibility;
	private Scanner sc;
	private int error = 0;



	public Conditions() {

	}

	public void setCurrentConditions() {

		setDate();
		do {
			try {
				error = 0;
				sc = new Scanner(System.in);
				System.out.println("Ingrese descripcion del clima para HOY: ");
				setDayDescription(sc.next());
				System.out.println("Ingrese temperatura actual: ");
				setTemp(sc.nextFloat());
				System.out.println("Ingrese sensacion termica: ");
				setChill(sc.nextFloat());
				System.out.println("Ingrese velocidad del viento: ");
				setWindSpeed(sc.nextFloat());
				System.out.println("Ingrese amanecer: ");
				setSunrise(sc.next());
				System.out.println("Ingrese atardecer: ");
				setSunset(sc.next());
				System.out.println("Ingrese humedad: ");
				setHumidity(sc.nextFloat());
				System.out.println("Ingrese presion atmosferica: ");
				setPressure(sc.nextFloat());
				System.out.println("Ingrese visibilidad: ");
				setVisibility(sc.nextFloat());

			} catch (InputMismatchException IME) {
				System.out.println("Error. Formato ingresado no valido");
				error = 1;

			}
		} while (error == 1);



	}





	public static void setDayDescription(String dayDesc) {
		dayDescription = dayDesc;
	}

	public void setDate() {

		date = new Date();

	}



	public void setTemp(float temp) {

		this.temp = temp;
	}

	public void setChill(float chill) {
		this.chill = chill;
	}

	public void setWindSpeed(float windSpeed) {
		this.windSpeed = windSpeed;
	}

	public void setSunrise(String sunrise) {
		this.sunrise = sunrise;
	}

	public void setSunset(String sunset) {
		this.sunset = sunset;
	}

	public void setHumidity(float humidity) {
		this.humidity = humidity;
	}

	public void setPressure(float pressure) {
		this.pressure = pressure;
	}

	public void setVisibility(float visibility) {
		this.visibility = visibility;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getDayDescription() {
		return dayDescription;
	}

	public float getTemp() {
		return temp;
	}

	public float getChill() {
		return chill;
	}

	public float getWindSpeed() {
		return windSpeed;
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
