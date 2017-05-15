package com.javieralvarez.clases;



import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;

public class Forecast {
	//////////////////////////////////////// FORECAST////////////////////////////////////////

	private Calendar c = Calendar.getInstance();


	private Date date;
	private float high, low;

	private Scanner sc;
	private String dayDescription;
	private ArrayList<Forecast> lista = new ArrayList<Forecast>();

	private int error;
	private int id = 0;

	public Forecast() {
	}

	public Forecast(Date date, String description, float low, float high) {
		this.date = date;
		this.dayDescription = description;
		this.low = low;
		this.high = high;
	}

	public void setForecastConditions() {   // Carga Forecast por consola.

		for (int i = 1; i < 6; i++) {
			do {
				try {
					error = 0;
					sc = new Scanner(System.in);
					System.out.println("\n*Configurar Forecast*");
					System.out.println("Condiciones para dia: " + getNextDay(i));
					date = getNextDay(i);
					System.out.println("Ingrese descripcion del clima: ");
					dayDescription = sc.next();
					System.out.println("Ingrese minima: ");
					low = sc.nextFloat();
					System.out.println("Ingrese maxima: ");
					high = sc.nextFloat();

					lista.add(new Forecast(date, dayDescription, low, high));

				} catch (Exception e) {
					System.out.println("Error. Ingrese valores correctamente");
					error = 1;
				}
			} while (error == 1);
		}
	}
		



	public Date getNextDay(int i) { // Calcula el siguiente dia.
		Date d = new Date();
		Date d1 = new java.sql.Date(d.getTime());
		c.setTime(d1);
		c.add(Calendar.DATE, i);
		d = c.getTime();

		return d;

	}

	public void setDate(Date date, int i) {
		c.setTime(date);
		c.add(Calendar.DATE, i);
		this.date = c.getTime();

	}

	public String getDayDescription() {
		return dayDescription;
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

	public Date getDate() {
		return date;
	}
	
	public int getID(){
		return id;
	}
	
	public ArrayList<Forecast> getLista(){
		return lista;
	}

}
