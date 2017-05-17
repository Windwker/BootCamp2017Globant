package com.javieralvarez.dao;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.javieralvarez.clases.Conditions;
import com.javieralvarez.clases.Conexion;

public class DaoConditionsIMP implements DaoConditions {
private int sql=0;
	public void insertConditions(Conditions con) { // Ejecuta el insert en BD.
		try{
			Conexion.getInstance();
			PreparedStatement ps = Conexion.getConexion().prepareStatement("INSERT INTO WEATHERGLOBANT.CURRENTCONDITIONS (DATE,DESCRIPTION,TEMP,CHILL,WINDSPEED,SUNRISE,SUNSET,HUMIDITY,PRESSURE,VISIBILITY,TYPE) VALUES (?,?,?,?,?,?,?,?,?,?,?)");
			ps.setDate(1, new java.sql.Date(con.getDate().getTime()));
			ps.setString(2, con.getDayDescription());
			ps.setFloat(3, con.getTemp());
			ps.setFloat(4, con.getChill());
			ps.setFloat(5, con.getWindSpeed());
			ps.setString(6, con.getSunrise());
			ps.setString(7, con.getSunset());
			ps.setFloat(8, con.getHumidity());
			ps.setFloat(9, con.getPressure());
			ps.setFloat(10, con.getVisibility());
			ps.setString(11, "CC");
			ps.execute();
			
			
			
		}catch(SQLException e){
			System.out.println("No se pueden agregar condiciones actuales. Error: " +e.getMessage());
		}catch(Exception e){
		System.out.println("No se pueden agregar condiciones actuales. Error: Base de datos en uso.");
		}			

		
	}

	public void updateConditions(Conditions con) { // Ejecuta el update de BD.
		
		
		try{
			Conexion.getInstance();
			PreparedStatement ps = Conexion.getConexion().prepareStatement("UPDATE WEATHERGLOBANT.CURRENTCONDITIONS SET date=?,description=?,temp=?,chill=?,windspeed=?,sunrise=?,sunset=?,humidity=?,pressure=?,visibility=?,type=? WHERE date=?");
			ps.setDate(1, new java.sql.Date(con.getDate().getTime()));
			ps.setString(2, con.getDayDescription());
			ps.setFloat(3, con.getTemp());
			ps.setFloat(4, con.getChill());
			ps.setFloat(5, con.getWindSpeed());
			ps.setString(6, con.getSunrise());
			ps.setString(7, con.getSunset());
			ps.setFloat(8, con.getHumidity());
			ps.setFloat(9, con.getPressure());
			ps.setFloat(10, con.getVisibility());
			ps.setString(11, "CC");
			ps.setDate(12, new java.sql.Date(con.getDate().getTime()));
			ps.execute();
			
			
			
		}catch(SQLException e){
			System.out.println("No se pueden actualizar las condiciones actuales. Error : " + e.getMessage());

		}catch(Exception e){
			System.out.println("No se pueden actualizar las condiciones actuales. Error: Base de datos en uso.");
		}
		
		
		
	}

	public int verifyConditions(Conditions con) { // Verifica si ya se encuentra cargado en BD las condiciones del dia.
		Conexion.getInstance();
		
		Statement st;
		try {
			st = Conexion.getConexion().createStatement();
			Date dia = new java.sql.Date(con.getDate().getTime());
			ResultSet rs = st.executeQuery("SELECT date FROM WEATHERGLOBANT.CURRENTCONDITIONS where date = '"+ dia +"'");
			
			while (rs.next()) {
				
				Date d2 = rs.getDate(1);
				SimpleDateFormat df = new SimpleDateFormat("DDMMYYYY");
				if (df.format(d2).equals(df.format(dia))) {
					sql = 1;
					System.out.println();
					

				} else {
					sql = 0;
					
				}

			}
			
			if(sql==0){
				System.out.println("\n-Se ejecuta insert de condiciones actuales.");
			}else{
				System.out.println("\n-Se ejecuta update a las condiciones actuales.");
			}
			
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("Error: Base de datos en uso");
		}
		return sql;
		
		
	}

	public void selectConditions(Conditions con) {
		try {
			Conexion.getInstance();
			Date d1 = new java.sql.Date(con.getDate().getTime());
			System.out.println("\n*** GLOBANT FORECAST 1.0***");
			System.out.println("\n|Clima para " + d1 +"|");

			
			
			Statement st = Conexion.getConexion().createStatement(); // Ejecuta Query en BD.

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
			
		} catch (SQLException e){
			System.out.println("No se pueden obtener los datos de las condiciones actuales. Error: " +e.getMessage());
		} catch (Exception e) {
			System.out.println(
					"No se pueden obtener los datos de las condiciones actuales. Error: Base de datos en uso.");
			
		}
		
	}

}
