package com.javieralvarez.clases;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.javieralvarez.consolereader.ConditionsReader;
import com.javieralvarez.consolereader.ForecastReader;
import com.javieralvarez.dao.DaoConditionsIMP;
import com.javieralvarez.dao.DaoForecastIMP;

public class WeatherGlobant {
	@Autowired
	public DaoConditionsIMP daoc;
	@Autowired
	private DaoForecastIMP daof;
	@Autowired
	private WeatherGlobant main;

	public DaoConditionsIMP getDaoc() {
		return daoc;
	}

	public void setDaoc(DaoConditionsIMP daoc) {
		this.daoc = daoc;
	}

	static ArrayList<Forecast> lista = new ArrayList<Forecast>();

	public static void main(String[] args) {
		ApplicationContext appContext = new ClassPathXmlApplicationContext("com/javieralvarez/clases/Beans.xml");
		WeatherGlobant main = appContext.getBean(WeatherGlobant.class);
		// ((ConfigurableApplicationContext)appContext).close();
		main.iniciar();

	}

	private void iniciar() {

		ConditionsReader cr = new ConditionsReader();
		cr.conditionsConsoleReader();
		Conditions.Builder conditionBuilder = new Conditions.Builder();
		Conditions cc = conditionBuilder.date(cr.getDate()).description(cr.getDescription()).temp(cr.getTemp())
				.st(cr.getChill()).windspeed(cr.getWindspeed()).sunrise(cr.getSunrise()).sunset(cr.getSunset())
				.humidity(cr.getHumidity()).pressure(cr.getPressure()).visibility(cr.getVisibility()).build();

		ForecastReader fr = new ForecastReader();

		Forecast.Builder forecastBuilder = new Forecast.Builder();
		for (int i = 0; i < 5; i++) {
			fr.forecastConsoleReader();
			lista.add(forecastBuilder.date(fr.getDate()).dayDescription(fr.getDayDescription()).low(fr.getLow())
					.high(fr.getHigh()).build());
		}

		if (daoc.verifyBD(cc) == 1) { /// VERIFICA SI EL DIA YA HA SIDO
			/// CARGADO, HACE UPDATE O
			/// INSERT SEGUN CORRESPONDA.
			daoc.update(cc);
		} else {
			daoc.insert(cc);
		}

		for (int i = 0; i < lista.size(); i++) {
			if (daof.verifyBD(lista.get(i)) == 1) { /// VERIFICA SI YA SE HA
													/// CARGADO UN
				/// FORECAST PARA EL DIA, HACE
				/// UPDATE O INSERT SEGUN
				/// CORRESPONDA.
				daof.update(lista.get(i));
			}

			else {
				daof.insert(lista.get(i));
			}
		}

		daoc.select(cc); // Muestra por pantalla condiciones actuales.
		daof.select(null); // muestra por pantalla el forecast.
	}

	public WeatherGlobant getMain() {
		return main;
	}

	public void setMain(WeatherGlobant main) {
		this.main = main;
	}
}
