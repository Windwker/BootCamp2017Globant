package com.javieralvarez.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javieralvarez.connection.Conexion;
import com.javieralvarez.entity.Forecast;

@Repository
public class DaoForecastImpl implements ForecastAndConditionsDao<Forecast> {
	private int id = 0;
	private String city;
	private String country;
	private int sql;
	List<Forecast> lista;
	@Autowired
	private Conexion conexion;

	public void insert(Forecast fc) { // Inserta forecast en BD.

		try {
			Statement st = conexion.getConexion().createStatement();
			PreparedStatement ps = null;
			ResultSet rs = st.executeQuery("SELECT ID FROM WEATHERGLOBANT.CURRENTCONDITIONS");

			while (rs.next()) {
				id = rs.getInt(1);
			}

			rs = st.executeQuery("SELECT CITY, COUNTRY FROM WEATHERGLOBANT.CURRENTCONDITIONS WHERE ID='" + id + "'"); /// VERIFICAR!!!!
			while (rs.next()) {
				city = rs.getString(1);
				country = rs.getString(2);
			}



			ps = conexion.getConexion().prepareStatement("INSERT INTO WEATHERGLOBANT.FORECAST VALUES (?,?,?,?,?,?,?)");

			ps.setInt(1, id);
			ps.setString(2, fc.getDate());
			ps.setString(3, fc.getDayDescription());
			ps.setFloat(4, fc.getLow());
			ps.setFloat(5, fc.getHigh());
			ps.setString(6, fc.getCity());
			ps.setString(7, fc.getCountry());

			ps.execute(); // ps.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}

	public void update(Forecast fc) { // Update del Forecast en BD.
		// TODO Auto-generated method stub

		try {
			Statement st = conexion.getConexion().createStatement();
			PreparedStatement ps = null;
			ResultSet rs = st.executeQuery("SELECT ID FROM WEATHERGLOBANT.CURRENTCONDITIONS");

			while (rs.next()) {
				id = rs.getInt(1);
			}

			ps = conexion.getConexion().prepareStatement(
					"UPDATE WEATHERGLOBANT.FORECAST SET date=?,description=?, low=?, high=? WHERE date =? AND city=? AND country=?");

		
			ps.setString(1, fc.getDate());
			ps.setString(2, fc.getDayDescription());
			ps.setFloat(3, fc.getLow());
			ps.setFloat(4, fc.getHigh());
			ps.setString(5, fc.getDate());
			ps.setString(6, fc.getCity());
			ps.setString(7, fc.getCountry());

			ps.execute(); // ps.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}

	}

	public List<Forecast> select(String date, String city, String country) {

		try {

			lista = new ArrayList<Forecast>();

			Forecast.Builder forecastBuilder = new Forecast.Builder();
			Statement st = conexion.getConexion().createStatement();


			ResultSet rs = st.executeQuery(
					"SELECT date, description, low, high, city, country FROM WEATHERGLOBANT.FORECAST WHERE date='"
							+ date + "'AND city='" + city + "'AND country='" + country + "'");

			

			while (rs.next()) {

				lista.add(forecastBuilder.date(rs.getString(1)).dayDescription(rs.getString(2)).low(rs.getFloat(3))
						.high(rs.getFloat(4)).city(rs.getString(5)).country(rs.getString(6)).build());

			}
		
		} catch (SQLException e) {
			
		}

		return lista;

	}


}
