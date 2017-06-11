package com.javieralvarez.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.springframework.stereotype.Component;

@Component
public class Conexion {

	private Connection con;

	private String urlh2 = "jdbc:h2:/home/javi/Dropbox/GlobantDB";
	private String usrh2 = "JAVI";
	private String pswh2 = "1234";

	private String urlmysql = "jdbc:mysql://localhost:3306/?&useSSL=false";
	private String usrmysql = "root";
	private String pswmysql = "1234";

	// TEST ONLY

	public void setConection(Connection con) {
		this.con = con;
	}

	public Connection getConexion() {

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(urlmysql, usrmysql, pswmysql);

		} catch (SQLException e) {

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
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return con;

	}

}
