package com.javieralvarez.clases;

import java.util.ArrayList;

import com.javieralvarez.consolereader.ConditionsReader;
import com.javieralvarez.consolereader.ForecastReader;
import com.javieralvarez.dao.DaoConditions;
import com.javieralvarez.dao.DaoConditionsIMP;
import com.javieralvarez.dao.DaoForecast;
import com.javieralvarez.dao.DaoForecastIMP;

public class WeatherGlobant {
	static ArrayList<Forecast> lista = new ArrayList<Forecast>();
	public static void main(String[] args) {
		
		ConditionsReader cr = new ConditionsReader();
		cr.conditionsConsoleReader();
		Conditions.Builder conditionBuilder = new Conditions.Builder();
		Conditions cc = conditionBuilder.date(cr.getDate()).description(cr.getDescription()).temp(cr.getTemp())
				.st(cr.getChill()).windspeed(cr.getWindspeed()).sunrise(cr.getSunrise()).sunset(cr.getSunset())
				.humidity(cr.getHumidity()).pressure(cr.getPressure()).visibility(cr.getVisibility()).build();

		
		
		ForecastReader fr = new ForecastReader();
		
		Forecast.Builder forecastBuilder = new Forecast.Builder();
		for(int i=0;i<5;i++){
		  fr.forecastConsoleReader();
		  lista.add(forecastBuilder.date(fr.getDate()).dayDescription(fr.getDayDescription())
					.low(fr.getLow()).high(fr.getHigh()).build());
		}

		
		
		
		
		DaoConditions dao = new DaoConditionsIMP();
		DaoForecast daof = new DaoForecastIMP();

		if (dao.verifyConditions(cc) == 1) { /// VERIFICA SI EL DIA YA HA SIDO
												/// CARGADO, HACE UPDATE O
												/// INSERT SEGUN CORRESPONDA.
			dao.updateConditions(cc);
		} else {
			dao.insertConditions(cc);
		}
		for(int i = 0; i<lista.size();i++){
		if (daof.verifyForecast(lista.get(i)) == 1) { /// VERIFICA SI YA SE HA CARGADO UN
											/// FORECAST PARA EL DIA, HACE
											/// UPDATE O INSERT SEGUN
											/// CORRESPONDA.
			daof.updateForecast(lista.get(i));
		}
			
		else {
			daof.insertForecast(lista.get(i));
		}
	}

		dao.selectConditions(cc); /// MUESTRA POR PANTALLA LAS CONDICIONES
									/// ACTUALES.
		daof.selectForecast(); /// MUESTRA POR PANTALLA EL FORECAST PARA EL
									/// DIA.

	}
	


}
