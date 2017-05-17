package com.javieralvarez.clases;

import com.javieralvarez.consolereader.ConditionsReader;
import com.javieralvarez.dao.DaoConditions;
import com.javieralvarez.dao.DaoConditionsIMP;
import com.javieralvarez.dao.DaoForecast;
import com.javieralvarez.dao.DaoForecastIMP;

public class WeatherGlobant {

	public static void main(String[] args) {
		ConditionsReader cr = new ConditionsReader();
		cr.conditionsConsoleReader();
		Conditions.Builder conditionBuilder = new Conditions.Builder();
		Conditions cc = conditionBuilder.date(cr.getDate()).description(cr.getDescription()).temp(cr.getTemp())
				.st(cr.getChill()).windspeed(cr.getWindspeed()).sunrise(cr.getSunrise()).sunset(cr.getSunset())
				.humidity(cr.getHumidity()).pressure(cr.getPressure()).visibility(cr.getVisibility()).build();

		Forecast fc = new Forecast();

		fc.setForecastConditions(); // CARGA EL FORECAST.
		DaoConditions dao = new DaoConditionsIMP();
		DaoForecast daof = new DaoForecastIMP();

		if (dao.verifyConditions(cc) == 1) { /// VERIFICA SI EL DIA YA HA SIDO
												/// CARGADO, HACE UPDATE O
												/// INSERT SEGUN CORRESPONDA.
			dao.updateConditions(cc);
		} else {
			dao.insertConditions(cc);
		}

		if (daof.verifyForecast(fc) == 1) { /// VERIFICA SI YA SE HA CARGADO UN
											/// FORECAST PARA EL DIA, HACE
											/// UPDATE O INSERT SEGUN
											/// CORRESPONDA.
			daof.updateForecast(fc);
		} else {
			daof.insertForecast(fc);
		}

		dao.selectConditions(cc); /// MUESTRA POR PANTALLA LAS CONDICIONES
									/// ACTUALES.
		daof.selectForecast(fc); /// MUESTRA POR PANTALLA EL FORECAST PARA EL
									/// DIA.

	}

}
