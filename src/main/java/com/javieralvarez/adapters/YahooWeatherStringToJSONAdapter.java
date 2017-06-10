package com.javieralvarez.adapters;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.javieralvarez.dao.DaoConditionsImpl;
import com.javieralvarez.dao.DaoForecastImpl;
import com.javieralvarez.entity.Conditions;
import com.javieralvarez.entity.Forecast;
import com.javieralvarez.proxy.Proxy;
import com.javieralvarez.transformers.Transformer;
import com.javieralvarez.validations.Validations;

@Component
public class YahooWeatherStringToJSONAdapter implements YahooWeather {
	@Autowired
	private Conditions condition;
	@Autowired
	private Forecast forecast;
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
	@Autowired
	Proxy proxy;
	@Autowired
	Validations validateStatus;

	Date today = new Date();

	SimpleDateFormat df = new SimpleDateFormat("dd MMM YYYY");

	String auxToday = (df.format(today));

	public Conditions getConditions(String city, String country) {
		int errorDB = 0;
		int conexion = validateStatus.checkConnection();
		int dbStatus = validateStatus.checkDBStatus();

		if (conexion == 1) { // VERIFICA HAYA CONEXION CON INTERNET.

			try {
				String query = "select * from weather.forecast where woeid in (select woeid from geo.places(1) where text=";

				String path = query + "\"" + city + "," + country + "\")";

				try {

					String result = proxy.getYahooWeather(path);
					System.out.println(Transformer.verifyJSON(result));
					if (Transformer.verifyJSON(result) == 0) {
						condition = Transformer.transformConditions(result);

						fahrenheit.setTemperature(condition.getTemp());
						condition.setTemp(adapterTemp.getTemperature());
						fahrenheit.setTemperature(condition.getChill());
						condition.setChill(adapterTemp.getTemperature());
						mph.setSpeed(condition.getWindSpeed());
						condition.setWindspeed(adapterSpeed.getSpeed());
						if (dbStatus == 1) { // VERIFICA HAYA CONEXION CON LA
												// BD.
							List<Conditions> con = daoc.select(condition.getDate(), condition.getCity(),
									condition.getCountry());

							if (con.size() == 0) {
								daoc.insert(condition);
							} else {
								daoc.update(condition);
							}
						}

					} else {
						condition.setDayDescription(null);

					}

				} catch (Exception e) {
					System.out.println("Error Conditions");
					System.out.println(e.getMessage());
				}

			} catch (Exception e) {
				// System.out.println("FRUTA");
				// System.out.println(e.getMessage());
				System.out.println(e.getMessage());
			}

		} else if (dbStatus == 1) {// NO HAY CONEXION CON EL ENDPOINT. VERIFICA
									// HAYA CONEXION CON DB LOCAL.

			try {

				condition = daoc.select(auxToday, city, country).get(0);
			} catch (Exception e) {
				System.out.println("ERROR");
				System.out.println(e.getMessage());
				errorDB = 1;
				return condition;
			}

		} else {

		}
		return condition;
	}

	public List<Forecast> getForecast(String city, String country) {
		// TODO Auto-generated method stub

		List<Forecast> listado = new ArrayList<Forecast>();
		int connectionStatus = validateStatus.checkConnection();
		int dbStatus = validateStatus.checkDBStatus();

		if (connectionStatus == 1) {
			try {
				String query = "select * from weather.forecast where woeid in (select woeid from geo.places(1) where text=";

				String path = query + "\"" + city + "," + country + "\")";

				String result = proxy.getYahooWeather(path);
				if (Transformer.verifyJSON(result) == 0) {
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

				} else {

					forecast.setDayDescription(null);

					listado.add(forecast);

				}

			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		} else if (dbStatus == 1) {

			for (int i = 1; i < 6; i++) {
				Date tomorrow = new Date(today.getTime() + (1000 * 60 * 60 * 24) * i);
				String auxTomorrow = (df.format(tomorrow));
				try {
					listado.add(daof.select(auxTomorrow, city, country).get(0));
				} catch (Exception e) {

				}
			}
		} else {
		}

		return listado;

	}

}
