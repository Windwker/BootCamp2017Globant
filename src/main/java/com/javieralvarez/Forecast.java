package com.javieralvarez;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;

public class Forecast {
////////////////////////////////////////FORECAST////////////////////////////////////////
	
	private Calendar c = Calendar.getInstance();
	
	private String text;
	private Date date;
	private float high, low;
	private String dateToString;
	private Scanner sc;
	private String dayDescription;
	private ArrayList<Forecast> lista = new ArrayList<Forecast>();
	private int sql;
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

	public void setForecastConditions() {

		for (int i = 1; i < 6; i++) {
			do{
			try {
				error=0;
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
				error=1;
			}
			}while(error==1);
		}



		
		try {
			Conexion.getInstance();
			Connection con = Conexion.getConexion();
			Statement st = con.createStatement();
			PreparedStatement ps = null;
			ResultSet rs;
			ResultSet rs2;
			rs = st.executeQuery("SELECT ID from WEATHERGLOBANT.CURRENTCONDITIONS");
			
			
			while(rs.next()){
				id = rs.getInt(1);
			}
			
			rs2 = st.executeQuery("SELECT DATE FROM WEATHERGLOBANT.FORECAST");
			
			sql = 0;

			for (int i = 0; i < lista.size(); i++) {

				while (rs2.next() && sql != 1) {
					Date dia = new java.sql.Date(getNextDay(i + 1).getTime());
					Date d2 =rs2.getDate(1);
					SimpleDateFormat df = new SimpleDateFormat("DDMMYYYY");
					
					if (df.format(dia).equals(df.format(rs2.getDate(1)))) {
						sql = 1;
						System.out.println("Se hace update del Forecast");

					} else {
						sql = 0;
						System.out.println("Inserta Forecast");
					}
				}

				if (sql == 0) {
					ps = con.prepareStatement("INSERT INTO WEATHERGLOBANT.FORECAST VALUES (?,?,?,?,?)");
				} else if (sql == 1) {
					ps = con.prepareStatement(
							"UPDATE WEATHERGLOBANT.FORECAST SET id=?,date=?,description=?, low=?, high=? WHERE date =?");
					Date d1 = getNextDay((i + 1));
					ps.setDate(6, new java.sql.Date(lista.get(i).getDate().getTime())  );
				}
				ps.setInt(1, id);
				ps.setDate(2, new java.sql.Date(lista.get(i).getNextDay((i + 1)).getTime()));
				ps.setString(3, lista.get(i).getDayDescription());
				ps.setFloat(4, lista.get(i).getLow());
				ps.setFloat(5, lista.get(i).getHigh());

				ps.execute(); // ps.close();

			}

		} catch (Exception e) {
			System.out.println("Error al modificar Forecast: " + e.getMessage());
		}

	}

	public void getForecastConditions() {
		try {

			Conexion.getInstance();
			Connection con = Conexion.getConexion();
			Statement st = con.createStatement();
			PreparedStatement ps = null;
			ResultSet rs = st.executeQuery("SELECT date, description, low, high FROM WeatherGlobant.FORECAST WHERE ID='"+id+"'");
			System.out.println("\nClima para los proximos 5 dias:");
			System.out.println("");
			while(rs.next()){
				
				System.out.println(rs.getDate(1)+" "+rs.getString(2)+" "+"Min: " +rs.getFloat(3)+" "+"Max: "+rs.getFloat(4));
				
			}

		} catch (Exception e) {
			System.out.println("No se pueden obtener los datos del forecast. Descripcion Error:" + e.getMessage());
		}

	}

	public Date getNextDay(int i) {
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
	
	public Date getDate(){
		return date;
	}

}
