package com.javieralvarez.proxy;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.javieralvarez.adapters.AdapterFahrenheitToCelcius;
import com.javieralvarez.adapters.AdapterMPHtoKMH;
import com.javieralvarez.adapters.FahrenheitImpl;
import com.javieralvarez.adapters.MphImpl;
import com.javieralvarez.client.YahooWeatherClient;
import com.javieralvarez.dao.DaoConditionsImpl;
import com.javieralvarez.dao.DaoForecastImpl;
import com.javieralvarez.entity.Conditions;
import com.javieralvarez.entity.Forecast;
import com.javieralvarez.transformers.Transformer;
import com.javieralvarez.validations.Validations;

@Component
public class ProxyWeather extends Transformer {

	@Autowired
	private Conditions condition;
	@Autowired
	private Forecast forecast;
	@Resource
	YahooWeatherClient cliente;
	@Autowired
	DaoConditionsImpl daoc;
	@Autowired
	DaoForecastImpl daof;
	@Autowired
	AdapterFahrenheitToCelcius adapterTemp;
	@Autowired
	FahrenheitImpl fahrenheit;
	@Autowired
	AdapterMPHtoKMH adapterSpeed;
	@Autowired
	MphImpl mph;

	Date date = new Date();
	SimpleDateFormat df = new SimpleDateFormat("dd MMM YYYY");
	String auxDate = (df.format(date));
	Validations validateStatus = new Validations();

	public Conditions getConditionsJson(String city, String country) {

		String city1 = city;
		String country1 = country;

		int conexion = validateStatus.checkConnection();
		int dbStatus = validateStatus.checkDBStatus();

		if (conexion == 1) { // VERIFICA HAYA CONEXION CON INTERNET.

			try {
				String query = "select * from weather.forecast where woeid in (select woeid from geo.places(1) where text=";
				String cityURL = city1;
				String countryURL = country1;

				String path = query + "\"" + cityURL + "," + countryURL + "\")";

				try {
					String result = cliente.getYahooWeather(path);
					condition = Transformer.transformConditions(result);
					fahrenheit.setTemperature(condition.getTemp());
					condition.setTemp(adapterTemp.getTemperature());
					fahrenheit.setTemperature(condition.getChill());
					condition.setChill(adapterTemp.getTemperature());
					mph.setSpeed(condition.getWindSpeed());
					condition.setWindspeed(adapterSpeed.getSpeed());
					if (dbStatus == 1) { // VERIFICA HAYA CONEXION CON LA BD.
						List<Conditions> con = daoc.select(condition.getDate(), condition.getCity(),
								condition.getCountry());

						if (con.size() == 0) {
							daoc.insert(condition);
						} else {
							daoc.update(condition);
						}
					}
				} catch (Exception e) {
					System.out.println("Error Conditions");
					System.out.println(e.getMessage());
				}

			} catch (Exception e) {
				System.out.println(e.getMessage());
			}

		} else if (dbStatus == 1) {// NO HAY CONEXION CON EL ENDPOINT. VERIFICA
									// HAYA CONEXION CON DB LOCAL.
			System.out.println("No hay conexion con internet, usando BD.");
			try {

				condition = daoc.select(auxDate, city, country).get(0);
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		} else {
			System.out.println("No hay conexion con db ni con internet");// NO
																			// Hay
																			// conexion
																			// con
																			// DB
																			// ni
																			// Internet.

		}
		return condition;
	}

	public List<Forecast> getForecastJson(String city, String country) {
		List<Forecast> listado = new ArrayList<Forecast>();
		int conexion = validateStatus.checkConnection();
		int dbStatus = validateStatus.checkDBStatus();

		if (conexion == 1) {

			String query = "select * from weather.forecast where woeid in (select woeid from geo.places(1) where text=";
			String cityURL = city;
			String countryURL = country;

			String path = query + "\"" + cityURL + "," + countryURL + "\")";

			String result = cliente.getYahooWeather(path);
			for (int i = 1; i < 6; i++) {
				forecast = Transformer.transformForecast(result, i);

				fahrenheit.setTemperature(forecast.getLow());
				forecast.setLow((adapterTemp.getTemperature()));
				fahrenheit.setTemperature(forecast.getHigh());
				forecast.setHigh((adapterTemp.getTemperature()));
				listado.add(forecast);

			}

			if (dbStatus == 1) {
				List<Forecast> listaux = daof.select(listado.get(0).getDate(), listado.get(0).getCity(),
						listado.get(0).getCountry());

				if (listaux.size() == 0) {

					for (int i = 0; i < listado.size(); i++) {
						daof.insert(listado.get(i));

					}
				} else {
					for (int i = 0; i < listado.size(); i++) {
						daof.update(listado.get(i));
					}
				}
			}
		}

		else if (dbStatus == 1) {
			listado = daof.select(auxDate, city, country);
		} else {
		}

		return listado;
	}
}