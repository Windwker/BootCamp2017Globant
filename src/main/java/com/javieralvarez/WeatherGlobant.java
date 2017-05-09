package com.javieralvarez;

import java.sql.DriverManager;
import java.sql.SQLException;


public class WeatherGlobant{
	
 public static void main(String[] args) {
	
	Conditions cc = Conditions.getInstance();
	cc.setCurrentConditions();
	Conexion.getInstance();

	
	
	
	

}
	
	
}




