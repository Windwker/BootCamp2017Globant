package com.javieralvarez.controller;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.javieralvarez.clases.Conexion;
import com.javieralvarez.clases.Forecast;

@RestController
public class ForecastController {
@RequestMapping(value = "/selectforecasts", method = RequestMethod.GET,headers="Accept=application/json")
public ArrayList<Forecast> getForecast(){

	Conexion.getInstance();
	
	Forecast.Builder forecastBuilder = new Forecast.Builder();
	
	ArrayList<Forecast> listado = new ArrayList<Forecast>();
	try {
		PreparedStatement ps = Conexion.getConexion().prepareStatement("SELECT * FROM WEATHERGLOBANT.FORECAST");
		ResultSet rs = ps.executeQuery();
		
		while(rs.next()){
			listado.add(forecastBuilder.date(rs.getDate(2)).dayDescription(rs.getString(3))
					.low(rs.getFloat(4)).high(rs.getFloat(5)).build());
		
		}
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	
	

	return listado;
	
}
@RequestMapping(value = "/selectforecast/{date}", method = RequestMethod.GET,headers="Accept=application/json")
public String getEmpleadosDni(@PathVariable String date){
	String forecast=null;
	Conexion.getInstance();
	try {
		PreparedStatement ps = Conexion.getConexion().prepareStatement("SELECT * FROM WEATHERGLOBANT.FORECAST WHERE DATE=?");
		ps.setString(1,date) ;
		ResultSet rs = ps.executeQuery();
		while(rs.next()){
			forecast = "Fecha: " + rs.getDate(2) +" Descripcion: "+rs.getString(3)+" Minima: "+rs.getFloat(4)+" Maxima: " +rs.getFloat(5);
		}
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	return forecast;
	
	
	
	
}


@RequestMapping(value ="/insertforecast/{date}/{description}/{low}/{high}")
public void insertForecastDescription(@PathVariable String date,@PathVariable String description,@PathVariable float low,@PathVariable float high){
	Conexion.getInstance();
	try {
		PreparedStatement ps = Conexion.getConexion().prepareStatement("SELECT DATE from WEATHERGLOBANT.FORECAST WHERE DATE=?");
		ps.setString(1, date);
		ResultSet rs = ps.executeQuery();
		while(rs.next()){
			if(rs.getString(1).equals(date)){
				ps = Conexion.getConexion().prepareStatement("UPDATE WEATHERGLOBANT.FORECAST SET DESCRIPTION=?, LOW=?, HIGH=? WHERE DATE=?");
				
				ps.setString(1, description);
				ps.setFloat(2, low);
				ps.setFloat(3, high);
				ps.setString(4, date);
				ps.execute();
			}else{
				ps = Conexion.getConexion().prepareStatement("INSERT INTO WEATHERGLOBANT.FORECAST (DATE, DESCRIPTION, LOW, HIGH) VALUES (?,?,?,?)");
				ps.setString(1, date);
				ps.setString(2, description);
				ps.setFloat(3, low);
				ps.setFloat(4, high);
				ps.execute();
				
			}
		}
		if(rs.absolute(1)==false){
			ps = Conexion.getConexion().prepareStatement("INSERT INTO WEATHERGLOBANT.FORECAST (DATE, DESCRIPTION, LOW, HIGH) VALUES (?,?,?,?)");
			ps.setString(1, date);
			ps.setString(2, description);
			ps.setFloat(3, low);
			ps.setFloat(4, high);
			ps.execute();
			
		}
		
		
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
}



}
