package com.javieralvarez.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.easymock.EasyMock;
import org.junit.Test;

import com.mysql.cj.jdbc.Driver;

import junit.framework.Assert;

public class ConnectionTest {
	@SuppressWarnings("static-access")
	@Test
	public void getConnectionTest() throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException {
		String urlmysql = "jdbc:mysql://localhost:3306/?&useSSL=false";
		String usrmysql = "root";
		String pswmysql = "1234";
		
		
		Driver driver = EasyMock.createMock(Driver.class);
		DriverManager driverManager = EasyMock.createMock(DriverManager.class);
		Connection conexion = EasyMock.createMock(Connection.class);
		EasyMock.expect(driver.acceptsURL(EasyMock.anyString())).andReturn(true);
		EasyMock.expect(driverManager.getConnection(EasyMock.anyString())).andReturn(EasyMock.anyObject(Connection.class));
		EasyMock.expect(conexion.createStatement().executeQuery(EasyMock.anyString())).andReturn(EasyMock.anyObject(ResultSet.class));
		EasyMock.replay(driverManager,conexion);
	
		

		conexion = driverManager.getConnection(urlmysql,usrmysql,pswmysql);
		
//		Conexion con = new Conexion();
//		con.setDriver(driver);	
		//con.setConection(conexion);
		
		Assert.assertNotNull(conexion);
		EasyMock.verify(driverManager);
		
	}
}
