package com.javieralvarez.controller;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.javieralvarez.clases.Conexion;
import com.javieralvarez.clases.Forecast;

@RestController
public class ForecastController {
@Autowired
Conexion conexion;	
@RequestMapping(value = "/selectforecast", method = RequestMethod.GET,headers="Accept=application/json")
public ArrayList<Forecast> getForecast(){


	
	Forecast.Builder forecastBuilder = new Forecast.Builder();
	
	ArrayList<Forecast> listado = new ArrayList<Forecast>();
	try {
		PreparedStatement ps = conexion.setConexion().prepareStatement("SELECT * FROM WEATHERGLOBANT.FORECAST");
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
public ArrayList<Forecast> getForecastDate(@PathVariable String date){


	
	Forecast.Builder forecastBuilder = new Forecast.Builder();
	
	ArrayList<Forecast> listado = new ArrayList<Forecast>();
	try {
		PreparedStatement ps = conexion.setConexion().prepareStatement("SELECT * FROM WEATHERGLOBANT.FORECAST WHERE DATE=?");
		ps.setString(1, date);
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





@RequestMapping(value ="/insertforecast",method = RequestMethod.POST)
public void insertForecast(@RequestBody Forecast f){
	
	PreparedStatement ps;
	try {
		ps = conexion.setConexion().prepareStatement("INSERT INTO WEATHERGLOBANT.FORECAST (DATE, DESCRIPTION, LOW, HIGH) VALUES (?,?,?,?)");
		ps.setDate(1, new java.sql.Date(f.getDate().getTime()));
		ps.setString(2, f.getDayDescription());
		ps.setFloat(3, f.getLow());
		ps.setFloat(4, f.getHigh());
		ps.execute();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}

/*
	try {
		PreparedStatement ps = conexion.setConexion().prepareStatement("SELECT DATE from WEATHERGLOBANT.FORECAST WHERE DATE=?");
		ps.setString(1, date);
		ResultSet rs = ps.executeQuery();
		while(rs.next()){
			if(rs.getString(1).equals(date)){
				ps = conexion.setConexion().prepareStatement("UPDATE WEATHERGLOBANT.FORECAST SET DESCRIPTION=?, LOW=?, HIGH=? WHERE DATE=?");
				
				ps.setString(1, description);
				ps.setFloat(2, low);
				ps.setFloat(3, high);
				ps.setString(4, date);
				ps.execute();
			}else{
				ps = conexion.setConexion().prepareStatement("INSERT INTO WEATHERGLOBANT.FORECAST (DATE, DESCRIPTION, LOW, HIGH) VALUES (?,?,?,?)");
				ps.setString(1, date);
				ps.setString(2, description);
				ps.setFloat(3, low);
				ps.setFloat(4, high);
				ps.execute();
				
			}
		}
		if(rs.absolute(1)==false){
			ps = conexion.setConexion().prepareStatement("INSERT INTO WEATHERGLOBANT.FORECAST (DATE, DESCRIPTION, LOW, HIGH) VALUES (?,?,?,?)");
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
*/

}
}
