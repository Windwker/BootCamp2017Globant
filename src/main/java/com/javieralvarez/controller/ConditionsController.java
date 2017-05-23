package com.javieralvarez.controller;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.javieralvarez.clases.Conexion;

@RestController
public class ConditionsController {
@Autowired
Conexion conexion;

@RequestMapping(value = "/selectconditions/{date}", method = RequestMethod.GET,headers="Accept=application/json")
public String getConditionstDate(@PathVariable String date){
	String conditions = null;
	
	try {
		PreparedStatement ps = conexion.setConexion().prepareStatement("SELECT * FROM WEATHERGLOBANT.CURRENTCONDITIONS WHERE DATE=?");
		ps.setString(1,date) ;
		ResultSet rs = ps.executeQuery();
		while(rs.next()){
			conditions = "ID: " + rs.getInt(1) + "Fecha: " + rs.getDate(2) +" Descripcion: "+rs.getString(3)+" Temperatura: "+rs.getFloat(4)+" Sensacion Termica: " +rs.getFloat(5) + "Velocidad del viento: "
					+ rs.getFloat(6) + "Amanecer: " + rs.getString(7)+ "Atardecer: " + rs.getString(8) + "Humedad: " + rs.getFloat(9) +"Presion Atmosferica: " + rs.getFloat(10) + "Visibilidad: " + rs.getFloat(11); 
		}
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	return conditions;
	
	
	
	
}


}
