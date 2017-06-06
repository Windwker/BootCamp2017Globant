package com.javieralvarez.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.easymock.EasyMock;
import org.junit.Test;

import com.javieralvarez.entity.Conexion;

import junit.framework.Assert;

public class ConnectionTest {

	@Test
	public void setConnectionTest() throws SQLException{
		String url = "jdbc:mysql://localhost:3306/?&useSSL=false";
		String usr = "root";
		String psw = "1234";
		DriverManager d = EasyMock.createMock(DriverManager.class);
		Conexion c = EasyMock.createMock(Conexion.class);
		EasyMock.expect(d.getConnection(url, usr, psw)).andReturn((Connection) c);
		
		
	
		EasyMock.replay(c,d);


		EasyMock.verify(c,d);
				
		
	}
	
}
