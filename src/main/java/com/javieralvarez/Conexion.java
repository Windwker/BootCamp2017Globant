package com.javieralvarez;



import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import org.h2.jdbc.JdbcSQLException;

public class Conexion {

	private static Conexion instance;
	private static String url = "jdbc:h2:/home/javi/Dropbox/GlobantDB";
	private static String usr = "JAVI";
	private static String psw = "1234";
	private static Connection con;


	private  Conexion() {
		try {
			Class.forName("org.h2.Driver");
			con = DriverManager.getConnection(url, usr, psw);
			
			
			Statement st = con.createStatement();

			
		} catch (JdbcSQLException e) {
			System.out.println("Error: " + e.getMessage());

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("Error: " + e.getMessage());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Error: " + e.getMessage());
		}
			
		
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