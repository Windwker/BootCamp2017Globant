package com.javieralvarez.dao;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javieralvarez.clases.Conditions;
import com.javieralvarez.clases.Conexion;
import com.javieralvarez.clases.Forecast;

@Repository
public class DaoConditionsIMP implements ForecastAndConditionsDao<Conditions> {

private int sql=0;
List<Conditions> lista;

@Autowired
private Conexion conexion;




	public void insert(Conditions conditions) { // Ejecuta el insert en BD.
		try{
			Statement st = conexion.setConexion().createStatement();
			PreparedStatement ps = null;
			Format formatter = new SimpleDateFormat("yyyy-MM-dd");
			String s = formatter.format(conditions.getDate());
			
			ps = conexion.setConexion().prepareStatement("INSERT INTO WEATHERGLOBANT.CURRENTCONDITIONS (DATE,DESCRIPTION,TEMP,CHILL,WINDSPEED,SUNRISE,SUNSET,HUMIDITY,PRESSURE,VISIBILITY) VALUES (?,?,?,?,?,?,?,?,?,?)");
			ps.setString(1, s);
			ps.setString(2, conditions.getDayDescription());
			ps.setFloat(3, conditions.getTemp());
			ps.setFloat(4, conditions.getChill());
			ps.setFloat(5, conditions.getWindSpeed());
			ps.setString(6, conditions.getSunrise());
			ps.setString(7, conditions.getSunset());
			ps.setFloat(8, conditions.getHumidity());
			ps.setFloat(9, conditions.getPressure());
			ps.setFloat(10, conditions.getVisibility());
		
			ps.execute();
			
			
			
		}catch(SQLException e){
			System.out.println("No se pueden agregar condiciones actuales. Error: " +e.getMessage());
		}
/*		}catch(Exception e){
		System.out.println(e.getStackTrace());
			System.out.println("No se pueden agregar condiciones actuales. Error: Base de datos en uso."+ e.getMessage());
		}			
*/
		
	}

	public void update(Conditions conditions) { // Ejecuta el update de BD.
		
		
		try{
			
			PreparedStatement ps = conexion.setConexion().prepareStatement("UPDATE WEATHERGLOBANT.CURRENTCONDITIONS SET date=?,description=?,temp=?,chill=?,windspeed=?,sunrise=?,sunset=?,humidity=?,pressure=?,visibility=?,type=? WHERE date=?");
			ps.setDate(1, new java.sql.Date(conditions.getDate().getTime()));
			ps.setString(2, conditions.getDayDescription());
			ps.setFloat(3, conditions.getTemp());
			ps.setFloat(4, conditions.getChill());
			ps.setFloat(5, conditions.getWindSpeed());
			ps.setString(6, conditions.getSunrise());
			ps.setString(7, conditions.getSunset());
			ps.setFloat(8, conditions.getHumidity());
			ps.setFloat(9, conditions.getPressure());
			ps.setFloat(10, conditions.getVisibility());
			ps.setString(11, "CC");
			ps.setDate(12, new java.sql.Date(conditions.getDate().getTime()));
			ps.execute();
			
			
			
		}catch(SQLException e){
			System.out.println("No se pueden actualizar las condiciones actuales. Error : " + e.getMessage());

		}catch(Exception e){
			System.out.println("No se pueden actualizar las condiciones actuales. Error: Base de datos en uso.");
		}
		
		
		
	}

	public int verifyBD(Conditions conditions) { // Verifica si ya se encuentra cargado en BD las condiciones del dia.
		
		
		Statement st;
		try {
			st = conexion.setConexion().createStatement();
			Date dia = new java.sql.Date(conditions.getDate().getTime());
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

	public List<Conditions> select(String r) {
		
		try {


			Statement st = conexion.setConexion().createStatement();

		if(r!=null){
			
				ResultSet rs = st.executeQuery(
						"SELECT date, description, temp, chill, windspeed, sunrise, sunset, humidity, pressure, visibility FROM WEATHERGLOBANT.CURRENTCONDITIONS WHERE DATE='"
								+ r + "'");
				lista = new ArrayList<Conditions>();
				
				Conditions.Builder conditionBuilder = new Conditions.Builder();
				while (rs.next()) {
					
					lista.add(conditionBuilder.date(rs.getDate(1)).description(rs.getString(2)).temp(rs.getFloat(3)).st(rs.getFloat(4)).windspeed(rs.getFloat(5))
							.sunrise(rs.getString(6)).sunset(rs.getString(7)).humidity(rs.getFloat(8)).pressure(rs.getFloat(9)).visibility(rs.getFloat(10)).build());
					
				}
		}else{
			ResultSet rs = st.executeQuery(
					"SELECT date, description, temp, chill, windspeed, sunrise, sunset, humidity, pressure, visibility FROM WEATHERGLOBANT.CURRENTCONDITIONS");
			lista = new ArrayList<Conditions>();
			
			Conditions.Builder conditionBuilder = new Conditions.Builder();
			while (rs.next()) {
				
				lista.add(conditionBuilder.date(rs.getDate(1)).description(rs.getString(2)).temp(rs.getFloat(3)).st(rs.getFloat(4)).windspeed(rs.getFloat(5))
						.sunrise(rs.getString(6)).sunset(rs.getString(7)).humidity(rs.getFloat(8)).pressure(rs.getFloat(9)).visibility(rs.getFloat(10)).build());
				
			}
		}
				

			
			
			

		} catch (SQLException e) {
			System.out.println("No se pueden obtener los datos del forecast. Error: " + e.getMessage());
		}

		
		return lista;
		//try {
		/*	
			//Date d1 = new java.sql.Date(conditions.getDate().getTime());
			Statement st = conexion.setConexion().createStatement(); // Ejecuta Query en BD.

			ResultSet rs = st.executeQuery(
					"SELECT date, description, temp, chill, windspeed, sunrise, sunset, humidity, pressure, visibility FROM WEATHERGLOBANT.CURRENTCONDITIONS WHERE DATE='"
							+ d1 + "'");

			Conditions.Builder conditionBuilder = new Conditions.Builder();
			
			
			while (rs.next()) {
lista.add(conditionBuilder.date(rs.getDate(1)).description(rs.getString(2)).temp(rs.getFloat(3)).st(rs.getFloat(4)).windspeed(rs.getFloat(5))
		.sunrise(rs.getString(6)).sunset(rs.getString(7)).humidity(rs.getFloat(8)).pressure(rs.getFloat(9)).visibility(rs.getFloat(10)).build());

			}
			
		} catch (SQLException e){
			System.out.println("No se pueden obtener los datos de las condiciones actuales. Error: " +e.getMessage());
		} catch (Exception e) {
			System.out.println(
					"No se pueden obtener los datos de las condiciones actuales. Error: Base de datos en uso.");
			
		}
		return lista;*/
		
	}







}
