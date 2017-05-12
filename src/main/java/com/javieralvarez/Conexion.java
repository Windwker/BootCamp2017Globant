package com.javieralvarez;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;

public class Conexion {

	private static Conexion instance;
	private static String url = "jdbc:mysql://localhost:3306?&useSSL=false";
	private static String usr = "root";
	private static String psw = "1234";
	private static Connection con;
	private static String createDB="CREATE DATABASE IF NOT EXISTS WeatherGlobant";
	private static String createTable="CREATE TABLE IF NOT EXISTS WeatherGlobant.Weather(date varchar(40), description varchar(50),temp float, chill float, windspeed float, sunrise varchar(60), sunset varchar(60), humidity float, pressure float, visibility float, type varchar(2),low float, high float)";

	





	private  Conexion() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(url, usr, psw);
			Statement st = con.createStatement();
			st.execute(createDB);
			st.execute(createTable);
	
			
		} catch (SQLException e) {
			System.out.println("Error: " + e.getMessage());;
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	
	
	public static String getCreateDB() {
		return createDB;
	}



	public static String getCreateTable() {
		return createTable;
	}
	
	
	
	public static String getUrl() {
		return url;
	}




	public static String getUsr() {
		return usr;
	}




	public static String getPsw() {
		return psw;
	}



	
	
	
	public static Connection getConexion(){
		return con;
	}
	
	public static Conexion getInstance() {
		if(instance == null){
			instance = new Conexion();
		}
		return instance;
	}
	

	
	

}