package com.javieralvarez.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javieralvarez.clases.Conexion;
import com.javieralvarez.clases.Forecast;

@Repository
public class DaoForecastIMP implements ForecastAndConditionsDao<Forecast> {
	private int id = 0;
	private int sql;
	
	@Autowired
	private Conexion conexion;
/*	ApplicationContext appContext = new ClassPathXmlApplicationContext("com/javieralvarez/clases/Beans.xml");
	Conexion conexion = appContext.getBean(Conexion.class);
*/




	public void insert(Forecast fc) { // Inserta forecast en BD.
	
		try {
			Statement st = conexion.setConexion().createStatement();
			PreparedStatement ps = null;
			ResultSet rs = st.executeQuery("SELECT ID FROM WEATHERGLOBANT.CURRENTCONDITIONS");

			while (rs.next()) {
				id = rs.getInt(1);
			}

			ps = conexion.setConexion().prepareStatement("INSERT INTO WEATHERGLOBANT.FORECAST VALUES (?,?,?,?,?)");

			ps.setInt(1, id);
			ps.setDate(2, new java.sql.Date(fc.getDate().getTime()));
			ps.setString(3, fc.getDayDescription());
			ps.setFloat(4, fc.getLow());
			ps.setFloat(5, fc.getHigh());

			ps.execute(); // ps.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		} catch (Exception e) {
			System.out.println("No se puede insertar el forecast. Error: Base de datos en uso.");
		}

	}

	public void update(Forecast fc) { // Update del Forecast en BD.
		// TODO Auto-generated method stub
	
		try {
			Statement st = conexion.setConexion().createStatement();
			PreparedStatement ps = null;
			ResultSet rs = st.executeQuery("SELECT ID FROM WEATHERGLOBANT.CURRENTCONDITIONS");

			while (rs.next()) {
				id = rs.getInt(1);
			}

			ps = conexion.setConexion().prepareStatement(
					"UPDATE WEATHERGLOBANT.FORECAST SET id=?,date=?,description=?, low=?, high=? WHERE date =?");

			ps.setInt(1, id);
			ps.setDate(2, new java.sql.Date(fc.getDate().getTime()));
			ps.setString(3, fc.getDayDescription());
			ps.setFloat(4, fc.getLow());
			ps.setFloat(5, fc.getHigh());
			ps.setDate(6, new java.sql.Date(fc.getDate().getTime()));

			ps.execute(); // ps.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		} catch (Exception e) {
			System.out.println("No se puede ejecutar Update del Forecast. Error: Base de datos en uso.");
		}

	}

	public void select(Forecast fc) { // Resultados de la consulta en BD.

		try {


			Statement st = conexion.setConexion().createStatement();

			ResultSet rs = st.executeQuery(
					"SELECT date, description, low, high FROM WEATHERGLOBANT.FORECAST WHERE ID='" + id + "'");
			System.out.println("\n|Clima para los proximos 5 dias: |");
			System.out.println("");
			while (rs.next()) {

				System.out.println(rs.getDate(1) + " " + rs.getString(2) + " " + "Min: " + rs.getFloat(3) + " "
						+ "Max: " + rs.getFloat(4));

			}

		} catch (SQLException e) {
			System.out.println("No se pueden obtener los datos del forecast. Error: " + e.getMessage());
		} catch (Exception e) {
			System.out.println("No se pueden obtener los datos del forecast. Error: Base de datos en uso.");
		}

		finally {
			try {
				conexion.setConexion().close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				System.out.println("No se puede cerrar la conexion. " + e.getMessage());
			} catch (Exception e) {
				System.out.println("Verifique que la BD H2 no se encuentre en uso");
			}
		}

	}

	public int verifyBD(Forecast fc) { // Verifica si ya hay un forecast cargado
										// para la fecha.
		ResultSet rs;

		Statement st;
		try {
			Date dia = new java.sql.Date(fc.getDate().getTime());
			st = conexion.setConexion().createStatement();
			rs = st.executeQuery("SELECT DATE from WEATHERGLOBANT.FORECAST where DATE ='" + dia + "'");

			while (rs.next() && sql != 1) {
				dia = new java.sql.Date(fc.getDate().getTime());

				SimpleDateFormat df = new SimpleDateFormat("DDMMYYYY");

				if (df.format(dia).equals(df.format(rs.getDate(1)))) {
					sql = 1;

				} else {
					sql = 0;

				}
			}

			if (sql == 1) {
				System.out.println("\n-Se ejecuta update al Forecast Existente");
			} else {
				System.out.println("\n-Se Inserta un nuevo Forecast");
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		} catch (Exception e) {
			// TODO Auto-generated catch block

		}
		return sql;

	}



}
