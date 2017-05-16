package com.javieralvarez;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import com.javieralvarez.clases.Conexion;

import junit.framework.Assert;
import junit.framework.TestCase;

public class ConexionTest extends TestCase {
	private static String url = "jdbc:h2:/home/javi/Dropbox/GlobantDB";
	private static String usr = "JAVI";
	private static String psw = "1234";

	public void testURL() {

		Assert.assertEquals(url, Conexion.getUrl());
		Assert.assertEquals(usr, Conexion.getUsr());
		Assert.assertEquals(psw, Conexion.getPsw());

	}

	public void testURLII() {
		Conexion.getInstance();
		Connection con = Conexion.getConexion();
		try {
			Statement st = con.createStatement();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			Assert.fail();
			e.getMessage();
		}

	}

	public void testCreaConGetInstance() {
		Conexion.getInstance();
		Assert.assertNotNull(Conexion.getConexion());
	}

}
