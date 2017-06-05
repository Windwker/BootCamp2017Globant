package com.javieralvarez.validations;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;

import com.javieralvarez.entity.Conexion;

public class Validations {
	@Autowired
	Conexion con;
	 
	///// Valida si se puede establecer conexion.
public  int checkConnection(){
	int conexion = 1;
	try{
		   String s = "http://www.google.com"; 	
		   URL url = new URL(s);
			Scanner scan = new Scanner(url.openStream());
			
	    	
	    	
	}catch(java.net.UnknownHostException jnu){
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
		Statement st = con.setConexion().createStatement();
		st.executeQuery("SELECT * FROM WEATHERGLOBANT.CURRENTCONDITIONS");
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.getMessage();
		dbStatus = 0;

	}catch (Exception e){

	}
	
	return dbStatus;

}
}
