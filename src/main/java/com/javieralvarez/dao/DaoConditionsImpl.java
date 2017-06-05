package com.javieralvarez.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javieralvarez.entity.Conditions;
import com.javieralvarez.entity.Conexion;

@Repository
public class DaoConditionsImpl implements ForecastAndConditionsDao<Conditions> {



	@Autowired
	private Conexion conexion;

	public void insert(Conditions conditions) { // Ejecuta el insert en BD.
		try {
			Statement st = conexion.setConexion().createStatement();
			PreparedStatement ps = null;


			ps = conexion.setConexion().prepareStatement(
					"INSERT INTO WEATHERGLOBANT.CURRENTCONDITIONS (DATE,DESCRIPTION,TEMP,CHILL,WINDSPEED,SUNRISE,SUNSET,HUMIDITY,PRESSURE,VISIBILITY,CITY,COUNTRY) VALUES (?,?,?,?,?,?,?,?,?,?,?,?)");
			ps.setString(1, conditions.getDate());
			ps.setString(2, conditions.getDayDescription());
			ps.setFloat(3, conditions.getTemp());
			ps.setFloat(4, conditions.getChill());
			ps.setFloat(5, conditions.getWindSpeed());
			ps.setString(6, conditions.getSunrise());
			ps.setString(7, conditions.getSunset());
			ps.setFloat(8, conditions.getHumidity());
			ps.setFloat(9, conditions.getPressure());
			ps.setFloat(10, conditions.getVisibility());
			ps.setString(11, conditions.getCity());
			ps.setString(12, conditions.getCountry());

			ps.execute();

		} catch (SQLException e) {
			System.out.println("No se pueden agregar condiciones actuales. Error: " + e.getMessage());
		}


	}

	public void update(Conditions conditions) { // Ejecuta el update de BD.

		try {

			PreparedStatement ps = conexion.setConexion().prepareStatement(
					"UPDATE WEATHERGLOBANT.CURRENTCONDITIONS SET date=?,description=?,temp=?,chill=?,windspeed=?,sunrise=?,sunset=?,humidity=?,pressure=?,visibility=?,type=? WHERE date=? AND city=? AND country=?");
			ps.setString(1, conditions.getDate());
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
			ps.setString(12, conditions.getDate());
			ps.setString(13, conditions.getCity());
			ps.setString(14, conditions.getCountry());
			ps.execute();

		} catch (SQLException e) {
			System.out.println("No se pueden actualizar las condiciones actuales. Error : " + e.getMessage());

		} catch (Exception e) {
			System.out.println("No se pueden actualizar las condiciones actuales. Error: Base de datos en uso.");
		}

	}

	/*
	 * public int verifyBD(Conditions conditions) { // Verifica si ya se
	 * encuentra cargado en BD las condiciones del dia.
	 * 
	 * 
	 * Statement st; try { st = conexion.setConexion().createStatement(); Date
	 * dia = new java.sql.Date(conditions.getDate().getTime()); ResultSet rs =
	 * st.
	 * executeQuery("SELECT date FROM WEATHERGLOBANT.CURRENTCONDITIONS where date = '"
	 * + dia +"'");
	 * 
	 * while (rs.next()) {
	 * 
	 * Date d2 = rs.getDate(1); SimpleDateFormat df = new
	 * SimpleDateFormat("DDMMYYYY"); if (df.format(d2).equals(df.format(dia))) {
	 * sql = 1; System.out.println();
	 * 
	 * 
	 * } else { sql = 0;
	 * 
	 * }
	 * 
	 * }
	 * 
	 * if(sql==0){
	 * System.out.println("\n-Se ejecuta insert de condiciones actuales.");
	 * }else{
	 * System.out.println("\n-Se ejecuta update a las condiciones actuales."); }
	 * 
	 * 
	 * 
	 * } catch (SQLException e) { // TODO Auto-generated catch block
	 * System.out.println(e.getMessage()); } catch (Exception e) { // TODO
	 * Auto-generated catch block
	 * System.out.println("Error: Base de datos en uso"); } return sql;
	 * 
	 * 
	 * }
	 */
	public List<Conditions> select(String date, String city, String country) {
		List<Conditions> lista = new ArrayList<Conditions>();
		try {

			Statement st = conexion.setConexion().createStatement();

			ResultSet rs = st.executeQuery(
					"SELECT date, description, temp, chill, windspeed, sunrise, sunset, humidity, pressure, visibility, city, country FROM WEATHERGLOBANT.CURRENTCONDITIONS WHERE DATE='"
							+ date + "'AND CITY='" + city + "' AND COUNTRY='" + country + "'");


			
//System.out.println(rs.getString(1));

			Conditions.Builder conditionBuilder = new Conditions.Builder();
			while (rs.next()) {

				lista.add(conditionBuilder.date(rs.getString(1)).description(rs.getString(2)).temp(rs.getFloat(3))
						.st(rs.getFloat(4)).windspeed(rs.getFloat(5)).sunrise(rs.getString(6)).sunset(rs.getString(7))
						.humidity(rs.getFloat(8)).pressure(rs.getFloat(9)).visibility(rs.getFloat(10)).city(rs.getString(11)).country(rs.getString(12)).build());

			}

			
		}catch	(IndexOutOfBoundsException iobe){
			System.out.println(iobe.getMessage());
		} catch (SQLException e) {
			System.out.println("No se pueden obtener los datos de las condiciones. Error: " + e.getMessage());
		}

		return lista;

	}

}
