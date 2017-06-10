package com.javieralvarez.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

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
		Properties p;
		
		//Driver driver = EasyMock.createMock(Driver.class);
		DriverManager driverManager = EasyMock.createMock(DriverManager.class);
		Connection conexion = EasyMock.createMock(Connection.class);
		
		
		//EasyMock.expect(driver.acceptsURL("Test")).andReturn(true);			
		EasyMock.expect(driverManager.getConnection(urlmysql,usrmysql,pswmysql)).andReturn(Connection.class.newInstance());
		EasyMock.expect(conexion.createStatement().executeQuery(EasyMock.anyString())).andReturn(EasyMock.anyObject(ResultSet.class));
		EasyMock.replay(driverManager,conexion);
	

		
		Conexion con = new Conexion();
	
		con.setConection(conexion);
		
		Assert.assertNotNull(con.getConexion());
		EasyMock.verify(driverManager);
		
	}
}
