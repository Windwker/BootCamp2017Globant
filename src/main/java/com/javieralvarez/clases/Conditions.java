package com.javieralvarez.clases;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Conditions {

	
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
	private int sql;
	private static Calendar c = Calendar.getInstance();

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

		try {

			Conexion.getInstance();
			Connection con = Conexion.getConexion();
			Statement st = con.createStatement();
			PreparedStatement ps = null;
			ResultSet rs = st.executeQuery("SELECT date FROM WEATHERGLOBANT.CURRENTCONDITIONS");

			while (rs.next()) {
				Date dia = new java.sql.Date(date.getTime());
				Date d2 = rs.getDate(1);
				SimpleDateFormat df = new SimpleDateFormat("DDMMYYYY");
				if (df.format(d2).equals(df.format(dia))) {
					sql = 1;
					System.out.println("Se ha actualizado el clima para: " + new java.sql.Date(date.getTime()));

				} else {
					sql = 0;

				}

			}
			rs.close();

			if (sql == 0) {

				ps = con.prepareStatement(
						"INSERT INTO WEATHERGLOBANT.CURRENTCONDITIONS (DATE,DESCRIPTION,TEMP,CHILL,WINDSPEED,SUNRISE,SUNSET,HUMIDITY,PRESSURE,VISIBILITY,TYPE) VALUES (?,?,?,?,?,?,?,?,?,?,?)");
					
			} else if (sql == 1) {
				ps = con.prepareStatement(
						"UPDATE WEATHERGLOBANT.CURRENTCONDITIONS SET date=?,description=?,temp=?,chill=?,windspeed=?,sunrise=?,sunset=?,humidity=?,pressure=?,visibility=?,type=? WHERE date=?");
				ps.setDate(12, new java.sql.Date(date.getTime()));

			} else {
				System.out.println("Nulo");
			}

			ps.setDate(1, new java.sql.Date(date.getTime()));
			ps.setString(2, getDayDescription());
			ps.setFloat(3, getTemp());
			ps.setFloat(4, getChill());
			ps.setFloat(5, getWindSpeed());
			ps.setString(6, getSunrise());
			ps.setString(7, getSunset());
			ps.setFloat(8, getHumidity());
			ps.setFloat(9, getPressure());
			ps.setFloat(10, getVisibility());
			ps.setString(11, "CC");
			ps.execute();

		} catch (Exception e) {
			System.out.println("Error SQL modificando Condiciones actuales" + e.getMessage());
		}

	}

	public void getCurrentConditions() {

		try {
			Date d1 = new java.sql.Date(date.getTime());

			System.out.println("Clima para " + d1);

			Conexion.getInstance();
			Connection con = Conexion.getConexion();
			Statement st = con.createStatement();
			PreparedStatement ps = null;
			ResultSet rs = st.executeQuery(
					"SELECT description, temp, chill, windspeed, sunrise, sunset, humidity, pressure, visibility FROM WEATHERGLOBANT.CURRENTCONDITIONS WHERE DATE='"
							+ d1 + "'");

			while (rs.next()) {

				System.out.println("\n" + rs.getString(1));
				System.out.println("Temperatura: " + rs.getFloat(2));
				System.out.println("Sensacion Termica: " + rs.getFloat(3));
				System.out.println("Velocidad del viento: " + rs.getFloat(4));
				System.out.println("Amanecer: " + rs.getString(5));
				System.out.println("Atarcer: " + rs.getString(6));
				System.out.println("Humedad: " + rs.getFloat(7));
				System.out.println("Presion: " + rs.getFloat(8));
				System.out.println("Visibilidad: " + rs.getFloat(9));

			}
			rs.close();
		} catch (Exception e) {
			System.out.println(
					"No se pueden obtener los datos de las condiciones actuales. Descripcion Error:" + e.getMessage());
			
		}

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
