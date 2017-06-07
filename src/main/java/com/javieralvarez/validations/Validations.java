package com.javieralvarez.validations;

import java.io.IOException;
import java.net.URL;
import java.sql.Statement;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.javieralvarez.entity.Conexion;
@Component
public class Validations {
	@Autowired
	private Conexion conexion;
	 
	///// Valida si se puede establecer conexion.
public  int checkConnection(){
	int conexion = 1;
	try{
		   String s = "http://www.google.com"; 	
		   URL url = new URL(s);
			Scanner scan = new Scanner(url.openStream());
			scan.close();
	    	
	    	
	}catch(java.net.UnknownHostException jnu){
		System.out.println("Warning: No internet connection available");
		conexion = 0;
		
	}
	
	catch(java.net.SocketException jne){
		conexion =0;
		
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		conexion =0;
	}
	return conexion;
	
	
}

public  int checkDBStatus(){
	int dbStatus = 1;
	
		
		
		try {

			
			Statement statement = conexion.getConexion().createStatement();
			statement.executeQuery("SELECT * FROM WEATHERGLOBANT.CURRENTCONDITIONS");
			
		} catch (Exception e) {
			// TODO Auto-generated catch block

			System.out.println("Warning: No db connection available");
		dbStatus = 0;
		}


	return dbStatus;

}
}
