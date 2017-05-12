package com.javieralvarez;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import junit.framework.Assert;
import junit.framework.TestCase;

public class ConexionTest extends TestCase {
	private static String url = "jdbc:mysql://localhost:3306?&useSSL=false";
	private static String usr = "root";
	private static String psw = "1234";
	
	
	
	public void testURL() {
		
		Assert.assertEquals(url, Conexion.getUrl());
		Assert.assertEquals(usr, Conexion.getUsr());
		Assert.assertEquals(psw, Conexion.getPsw());
	
	}
	
	
	public void testURLII(){
		Conexion.getInstance();
		Connection con = Conexion.getConexion();
		try {
			Statement st =con.createStatement();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			Assert.fail();
			e.getMessage();
		}
		
		
	}
	
	public void testInicioDB(){
		String createDB="CREATE DATABASE IF NOT EXISTS WeatherGlobant";
	    Assert.assertEquals(createDB, Conexion.getCreateDB());
	}
	
	public void testInicioTable(){
		 String createTable="CREATE TABLE IF NOT EXISTS WeatherGlobant.Weather(date varchar(40), description varchar(50),temp float, chill float, windspeed float, sunrise varchar(60), sunset varchar(60), humidity float, pressure float, visibility float, type varchar(2),low float, high float)";
		 Assert.assertEquals(createTable, Conexion.getCreateTable());
	}
	

	
	
	public void testCreaConGetInstance(){
		Conexion.getInstance();
		Assert.assertNotNull(Conexion.getConexion());
	}
	

}
