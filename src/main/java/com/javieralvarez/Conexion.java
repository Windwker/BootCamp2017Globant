package com.javieralvarez;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Conexion {

	private static Conexion instance;
	private String url = "jdbc:mysql://localhost:3306?&useSSL=false";
	private String usr = "root";
	private String psw = "1234";
	private int sql;
	Conditions condicion = Conditions.getInstance();
	Forecast forecast = Forecast.getInstance();
	
	private Conexion() {
		try {
			Connection con = DriverManager.getConnection(url, usr, psw);
			Statement st = con.createStatement();
			PreparedStatement ps = null;
			st.execute("CREATE DATABASE IF NOT EXISTS WeatherGlobant");
			st.execute("CREATE TABLE IF NOT EXISTS WeatherGlobant.Weather(date varchar(40), description varchar(50),temp float, chill float, windspeed float, sunrise varchar(60), sunset varchar(60), humidity float, pressure float, visibility float, type varchar(2),low float, high float)");
			ResultSet rs = st.executeQuery("SELECT date FROM WeatherGlobant.Weather WHERE type='CC'");
			
			
			while(rs.next()){
				
				
				System.out.println(condicion.getDateToString());
				System.out.println(rs.getString(1));
				if(condicion.getDateToString().equals(rs.getString(1))){
					sql=1;
				System.out.println("Update Realizado.");
					
				}
				else{
					sql=0;
					System.out.println("Inserta nueva.");
				}
				
			}
			
			if(sql==0){
			
			ps=con.prepareStatement("INSERT INTO WeatherGlobant.Weather VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?)");
			
			}
			else if(sql==1){
			ps=con.prepareStatement("UPDATE WeatherGlobant.Weather SET date=?,description=?,temp=?,chill=?,windspeed=?,sunrise=?,sunset=?,humidity=?,pressure=?,visibility=?,type=?,low=?,high=? WHERE date=?");
			ps.setString(14, condicion.getDateToString());

			}
			else{
				System.out.println("Nulo");
			}
			
			
			
			ps.setString(1, condicion.getDateToString());
			ps.setString(2, condicion.getDayDescription());
			ps.setFloat(3, condicion.getTemp());
			ps.setFloat(4, condicion.getChill());
			ps.setFloat(5, condicion.getWindSpeed());
			ps.setString(6, condicion.getSunrise());
			ps.setString(7, condicion.getSunset());
			ps.setFloat(8, condicion.getHumidity());
			ps.setFloat(9, condicion.getPressure());
			ps.setFloat(10, condicion.getVisibility());
			ps.setString(11, "CC");
			ps.setFloat(12, 0);
			ps.setFloat(13, 0);
			ps.execute();
			
			
			rs = st.executeQuery("SELECT date FROM WeatherGlobant.Weather WHERE type='FC'");
			
			sql=2;
			while(rs.next()){
				if(forecast.getDateToString().equals(rs.getString(1))){
					sql=1;
					System.out.println("Se hace update del Forecast");
				}
				else  {
					sql=0;
					System.out.println("Inserta Forecast");
				}
			}
			
			if(sql==0||sql==2){
				ps=con.prepareStatement("INSERT INTO WeatherGlobant.Weather VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?)");
			}
			else if (sql==1){
			ps=con.prepareStatement("UPDATE WeatherGlobant.Weather SET date=?,description=?,temp=?,chill=?,windspeed=?,sunrise=?,sunset=?,humidity=?,pressure=?,visibility=?, type=?, low=?, high=? WHERE date=?");
			ps.setString(14, forecast.getDateToString());
			}
			ps.setString(1, forecast.getDateToString());
			ps.setString(2, forecast.getDayDescription());
			ps.setFloat(3, 0);		
			ps.setFloat(4, 0);
			ps.setFloat(5, 0);
			ps.setString(6, "null");
			ps.setString(7, "null");
			ps.setFloat(8, 0);
			ps.setFloat(9, 0);
			ps.setFloat(10, 0);
			ps.setString(11, "FC");
			ps.setFloat(12, forecast.getLow());
			ps.setFloat(13, forecast.getHigh());
			ps.execute();
			//ps.close();
			
			
			
			
			
			
			
			
			
		} catch (SQLException e) {
			System.out.println("Error: " + e.getMessage());;
		}
	}

	public static Conexion getInstance() {
		if(instance == null){
			instance = new Conexion();
		}
		return instance;
	}

}