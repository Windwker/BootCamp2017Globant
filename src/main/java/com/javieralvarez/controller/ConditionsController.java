package com.javieralvarez.controller;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.javieralvarez.clases.Conditions;
import com.javieralvarez.clases.Conexion;

@RestController
public class ConditionsController {
@Autowired
Conexion conexion;


@RequestMapping(value = "/selectconditions", method = RequestMethod.GET,headers="Accept=application/json")
public ArrayList<Conditions> getConditions(){


	
	Conditions.Builder conditionBuilder = new Conditions.Builder();
	
	ArrayList<Conditions> listado = new ArrayList<Conditions>();
	try {
		PreparedStatement ps = conexion.setConexion().prepareStatement("SELECT * FROM WEATHERGLOBANT.CURRENTCONDITIONS");
		ResultSet rs = ps.executeQuery();
		
		while(rs.next()){
			listado.add(conditionBuilder.date(rs.getDate(2)).description(rs.getString(3)).temp(rs.getFloat(4)).st(rs.getFloat(5))
					.windspeed(rs.getFloat(6)).sunrise(rs.getString(7)).sunset(rs.getString(8)).humidity(rs.getFloat(9))
					.pressure(rs.getFloat(10)).visibility(rs.getFloat(11)).build());

		
		}
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return listado;


}


@RequestMapping(value = "/selectconditions/{date}", method = RequestMethod.GET,headers="Accept=application/json")
public ArrayList<Conditions> getConditionsDate(@PathVariable String date){


	
	Conditions.Builder conditionBuilder = new Conditions.Builder();
	
	ArrayList<Conditions> listado = new ArrayList<Conditions>();
	try {
		PreparedStatement ps = conexion.setConexion().prepareStatement("SELECT * FROM WEATHERGLOBANT.CURRENTCONDITIONS WHERE DATE=?");
		ps.setString(1, date);
		ResultSet rs = ps.executeQuery();
		
		while(rs.next()){
			listado.add(conditionBuilder.date(rs.getDate(2)).description(rs.getString(3)).temp(rs.getFloat(4)).st(rs.getFloat(5))
					.windspeed(rs.getFloat(6)).sunrise(rs.getString(7)).sunset(rs.getString(8)).humidity(rs.getFloat(9))
					.pressure(rs.getFloat(10)).visibility(rs.getFloat(11)).build());

		
		}
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return listado;


}







}