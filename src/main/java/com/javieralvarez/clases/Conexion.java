package com.javieralvarez.clases;




import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.h2.jdbc.JdbcSQLException;

public class Conexion {

	private static Conexion instance;
	private static String urlh2 = "jdbc:h2:/home/javi/Dropbox/GlobantDB";
	private static String usrh2 = "JAVI";
	private static String pswh2 = "1234";
	
	private static String urlmysql = "jdbc:mysql://localhost:3306/?&useSSL=false";
	private static String usrmysql = "root";
	private static String pswmysql = "1234";
	
	
	
	
	
	private static Connection con;


	private  Conexion() {
		try {
			
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(urlmysql,usrmysql,pswmysql);
			PreparedStatement ps =con.prepareStatement("CREATE SCHEMA IF NOT EXISTS WEATHERGLOBANT");
			ps.execute();
			ps= con.prepareStatement("CREATE TABLE IF NOT EXISTS WEATHERGLOBANT.CURRENTCONDITIONS (ID INT AUTO_INCREMENT PRIMARY KEY, DATE DATE, DESCRIPTION VARCHAR (50),TEMP FLOAT, CHILL FLOAT, WINDSPEED FLOAT, SUNRISE VARCHAR(50), SUNSET VARCHAR(50), HUMIDITY FLOAT, PRESSURE FLOAT, VISIBILITY FLOAT, TYPE VARCHAR(2))");
			ps.execute();
			ps= con.prepareStatement("CREATE TABLE IF NOT EXISTS WEATHERGLOBANT.FORECAST (ID INT, DATE DATE, DESCRIPTION VARCHAR (50), LOW FLOAT, HIGH FLOAT)");
			ps.execute();
		
/*			Class.forName("org.h2.Driver");
			con = DriverManager.getConnection(urlh2, usrh2, pswh2);*/
			
			


			
		} catch (JdbcSQLException e) {
			System.out.println("Error: JDBC: " + e.getMessage());

		} catch (ClassNotFoundException e) {
			
			System.out.println("Error: Class " + e.getMessage());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("\nError al conectar con BD principal mySQL: " + e.getMessage());  
			System.out.println("\n\n\n**************************************");
			System.out.println("||| Usando BD alternativa Local H2 |||");
			System.out.println("**************************************");
			try {
				Class.forName("org.h2.Driver");
				con = DriverManager.getConnection(urlh2, usrh2, pswh2);
			} catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				System.out.println(e1.getMessage());
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				System.out.println(e1.getMessage());
			}
		}
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
	
